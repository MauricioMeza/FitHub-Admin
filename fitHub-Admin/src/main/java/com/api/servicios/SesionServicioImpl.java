package com.api.servicios;

import com.api.dto.SesionDTO;
import com.api.modelos.Instructor;
import com.api.modelos.Plan;
import com.api.modelos.Sesion;
import com.api.modelos.TipoSesion;
import com.api.modelos.Usuario;
import com.api.repositorios.InstructorRepositorio;
import com.api.repositorios.PlanRepositorio;
import com.api.repositorios.SesionRepositorio;

import com.api.repositorios.TipoSesionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class SesionServicioImpl implements SesionServicio {

    @Autowired
    SesionRepositorio repositorio;
    @Autowired
    InstructorRepositorio repositorioIns;
	@Autowired
	TipoSesionRepositorio repositorioTses;
	@Autowired
	PlanRepositorio repositorioPlan;

    @Override
    public Sesion addSesion(SesionDTO sesionDTO) {
        Sesion nuevaSesion = new Sesion();
		Instructor instructor = repositorioIns.findByNombre(sesionDTO.getInstructor());
		TipoSesion tipo = repositorioTses.findByNombre(sesionDTO.getTipoSesion());
		sesionDTO.setTipo(tipo);

        long fechaSesionDTOComienzaMillis = sesionDTO.getFecha().getTime();
        long fechaSesionDTOTerminaMillis = sesionDTO.getFecha().getTime() + sesionDTO.getTipo().getDuracion() * 60 * 1000;
        Date fechaInicioSesionDTO = sesionDTO.getFecha();
        Date fechaFinSesionDTO = new Date(fechaSesionDTOTerminaMillis);

		List<Sesion> sesiones = repositorio.findAll();

		for(Sesion sesion : sesiones){
			long fechaSesionComienzaMillis = sesion.getFecha_hora().getTime();
			long fechaSesionTerminaMillis = sesion.getFecha_hora().getTime() + sesion.getTipo().getDuracion() * 60 * 1000;
			Date fechaInicioSesion = new Date(fechaSesionComienzaMillis);
			Date fechaFinSesion = new Date(fechaSesionTerminaMillis);
			if(sesion.getInstructor().equals(instructor)){
				if(sesion.getFecha_hora().equals(sesionDTO.getFecha())){
					return null;
				}else if(sesionDTO.getFecha().after(sesion.getFecha_hora()) && fechaSesionDTOComienzaMillis <= fechaSesionTerminaMillis){
					return null;
				}else if(fechaSesionDTOTerminaMillis >= fechaSesionComienzaMillis && fechaSesionDTOTerminaMillis <= fechaSesionTerminaMillis){
					return null;
				}else if(fechaSesionDTOComienzaMillis <= fechaSesionComienzaMillis && fechaSesionDTOTerminaMillis >= fechaSesionTerminaMillis){
					return null;
				}else if(fechaFinSesionDTO.after(fechaInicioSesion) && fechaFinSesionDTO.before(fechaFinSesion)) {
					return null;
				}else if(fechaInicioSesionDTO.after(fechaInicioSesion) && fechaInicioSesionDTO.before(fechaFinSesion)) {
					return null;
				}else if(fechaInicioSesionDTO.after(fechaInicioSesion) && fechaFinSesionDTO.before(fechaFinSesion)) {
					return null;
				}else if(fechaInicioSesion.after(fechaInicioSesionDTO) && fechaFinSesion.before(fechaFinSesionDTO)) {
					return null;
				}
			}
		}
        nuevaSesion.setInstructor(instructor);
        nuevaSesion.setTipo(tipo);
        nuevaSesion.setFecha_hora(sesionDTO.getFecha());
		nuevaSesion.setCupos(tipo.getCupos());

        return repositorio.save(nuevaSesion); 
    }

	@Override
	public Sesion cambiarSesion(SesionDTO sesionDTO) {
    	Sesion viejaSesion = repositorio.findById(sesionDTO.getId());
		Sesion nuevaSesion = new Sesion();

		Instructor instructor = repositorioIns.findByNombre(sesionDTO.getInstructor());
		TipoSesion tipo = repositorioTses.findByNombre(sesionDTO.getTipoSesion());
		TipoSesion tipoAnt = viejaSesion.getTipo();
		nuevaSesion.setAsistentes(viejaSesion.getAsistentes());
		nuevaSesion.setCupos(tipo.getCupos() - (tipoAnt.getCupos() - sesionDTO.getCupos()));
		nuevaSesion.setInstructor(instructor);
		nuevaSesion.setTipo(tipo);
		nuevaSesion.setFecha_hora(sesionDTO.getFecha());
		nuevaSesion.setId(sesionDTO.getId());

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
			List<Sesion> sesionesReservadas = usuario.getPlan().getSesionesReservadas();
			Plan plan = usuario.getPlan();
			for(int i = 0; i < sesionesReservadas.size(); i++) {
				if(sesionesReservadas.get(i).getId().contentEquals(sesion.getId())) {
					sesionesReservadas.remove(i);
					plan.setSesionesReservadas(sesionesReservadas);
					plan.setClasesDisponibles(plan.getClasesDisponibles()+1);
					usuario.setPlan(plan);
				}
			}			
			sesion.setAsistentes(inscritos);
			int cupos = sesion.getCupos();
			sesion.setCupos(cupos+ 1);
			this.cambiarSesion(sesion);
			repositorioPlan.save(plan);
			return "El usuario ha cancelado su cupo en la sesion";
		}
	}

}
