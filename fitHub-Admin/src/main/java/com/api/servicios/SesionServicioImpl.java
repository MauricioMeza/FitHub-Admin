package com.api.servicios;

import com.api.dto.SesionDTO;
import com.api.modelos.Instructor;
import com.api.modelos.Sesion;
import com.api.modelos.Usuario;
import com.api.repositorios.InstructorRepositorio;
import com.api.repositorios.SesionRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SesionServicioImpl implements SesionServicio {

    @Autowired
    SesionRepositorio repositorio;
    @Autowired
    InstructorRepositorio repositorioIns;

    @Override
    public Sesion addSesion(SesionDTO sesionDTO) {
        Sesion nuevaSesion = new Sesion();

        Instructor instructor = repositorioIns.findByNombre(sesionDTO.getInstructor());
        nuevaSesion.setInstructor(instructor);
        nuevaSesion.setTipo(sesionDTO.getSesion());
        nuevaSesion.setFecha_hora(sesionDTO.getFecha());

        return repositorio.save(nuevaSesion); 
    }

    @Override
    public List<Sesion> findAllSesionesByFecha() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "fecha_hora") );
    }

    @Override
    public Sesion getSesionById(String idSesion) {
        return repositorio.findById(idSesion);
    }

    @Override
    public void deleteSesion(Sesion sesion){
        repositorio.delete(sesion);
    }

    
    @Override
	public void cambiarSesion(Sesion sesion) 
	{
		repositorio.save(sesion);
	}

	@Override
	public boolean usuarioInscrito(Sesion sesion, Usuario usuario) {
		
		boolean inscrito = false;
		List<Usuario> usuariosInscritos = sesion.getAsistentes();
		for(int i = 0; i < usuariosInscritos.size(); i++)
		{
			String cedulaUsuarioLista = usuariosInscritos.get(i).getCedula();
			String cedulaUsuario = usuario.getCedula();
			if(usuariosInscritos.size()== 0) break;
			else if(cedulaUsuarioLista.equals(cedulaUsuario))
				inscrito = true; 
		}
		return inscrito;  
	}

	@Override
	public String cancelarCupo(Sesion sesion, Usuario usuario) {
		boolean inscrito = this.usuarioInscrito(sesion, usuario);
		if (!inscrito) 
			return "El usuario no está inscrito en la sesion";
		else {
			List<Usuario> inscritos = sesion.quitarAsistente(usuario);
			sesion.setAsistentes(inscritos);
			int cupos = sesion.getCupos();
			sesion.setCupos(cupos+ 1);
			this.cambiarSesion(sesion);
			return "El usuario ha cancelado su cupo en la sesion";
		}
	}

}
