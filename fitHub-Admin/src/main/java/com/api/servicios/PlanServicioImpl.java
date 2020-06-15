package com.api.servicios;

import com.api.dto.PlanDTO;
import com.api.modelos.Plan;
import com.api.modelos.Sesion;
import com.api.modelos.TipoPlan;
import com.api.modelos.Usuario;
import com.api.repositorios.PlanRepositorio;
import com.api.repositorios.SesionRepositorio;
import com.api.repositorios.TipoPlanRepositorio;
import com.api.repositorios.UsuarioRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PlanServicioImpl implements PlanServicio {

    @Autowired
    PlanRepositorio repositorio;
    @Autowired
    TipoPlanRepositorio repositorioTipoPlan;
    @Autowired
    UsuarioRepositorio usuarioRepositorio;
    @Autowired
    SesionRepositorio sesionRepositorio;
    @Autowired
    PlanRepositorio planRepositorio;
    
    
	@Override
	public Plan getPlanById(String idPlan) {
		Plan plan = repositorio.findById(idPlan);
		return plan;
	}
	@Override
	public List<Plan> getAllPlans() {
		List<Plan> planes  = repositorio.findAll(); 
		return planes;
	}
	@Override
	public Usuario addNewPlan(TipoPlan tipoPlan, Usuario usuario){
		Plan plan = new Plan();
		Date fecha = new Date();

		plan.setClasesDisponibles(tipoPlan.getCantSesiones());
		plan.setFechaInicio(new Date());
		plan.setFechaFin(plan.SumarDias(fecha, tipoPlan.getCantDias()));
		plan.setSesionesAsistidas(new ArrayList<>());
		plan.setSesionesReservadas(new ArrayList<>());
		plan.setTipoPlan(tipoPlan);
		plan.setActivo(true);

		repositorio.save(plan);
		usuario.setPlan(plan);

		return usuario;
	}
	@Override
	public List<Usuario> usuariosInscritos(String idPlan) {
		List<Usuario> usuarios = usuarioRepositorio.findAll();
		List<Usuario> usuariosIns = new ArrayList<>();
		for (Usuario usuario: usuarios) {
			if(usuario.getPlan().getId() == idPlan)
				usuariosIns.add(usuario);
		}
		return usuariosIns;
	}
	@Override
	public void actuaizarListasSesiones(String idUsuario) {
		Usuario usuario = usuarioRepositorio.findByCedula(idUsuario);
		List<Sesion> sesionesReservadas = usuario.getPlan().getSesionesReservadas();
		List<Sesion> sesionesAsistidas = usuario.getPlan().getSesionesAsistidas();
		Date fecha_actual = new Date();
		for(Sesion sesion: sesionesReservadas) {
			Date fechaFinSesion = usuario.getPlan().SumarMinutos(sesion.getFecha_hora(), sesion.getTipo().getDuracion());
			if(fechaFinSesion.before(fecha_actual)) {
				sesionesAsistidas.add(sesion);
				sesionesReservadas.remove(sesion);
			}
		Plan plan = usuario.getPlan();
		plan.setSesionesAsistidas(sesionesAsistidas);
		plan.setSesionesReservadas(sesionesReservadas);
		usuario.setPlan(plan);
		usuarioRepositorio.save(usuario);
		planRepositorio.save(plan);
		}
		
		
	}
	@Override
	public void cancelarPlan(String idPlan) {
		Plan plan = repositorio.findById(idPlan);
		plan.setActivo(false);
	}
	@Override
	public List<Plan> getAllActivePlans() {
		List<Plan> planes  = repositorio.findAll(); 
		List<Plan> planesActivos  = new ArrayList<>();
		for(Plan plan: planes) {
			if(plan.isActivo())
				planesActivos.add(plan);
		}
		
		return planesActivos;
	}
	@Override
	public Plan addPlan(PlanDTO planDTO) {
		Plan plan = new Plan();
		plan.setActivo(true);
		plan.setClasesDisponibles(planDTO.getClasesDisponibles());
		plan.setFechaFin(planDTO.getFechaFin());
		plan.setFechaInicio(planDTO.getFechaInicio());
		plan.setId(planDTO.getId());
		plan.setSesionesAsistidas(planDTO.getSesionesAsistidas());
		plan.setSesionesReservadas(planDTO.getSesionesReservadas());
		TipoPlan  tipoPlan = repositorioTipoPlan.findTipoPlanByNombre(planDTO.getTipoPlan());
		plan.setTipoPlan(tipoPlan);
		repositorio.save(plan);
		return plan;
	}
	@Override
	public Plan addPlan(Plan plan) {
		repositorio.save(plan);
		return null;
	}
	@Override
	public void cambiarPlan(PlanDTO planDTO) {
		Plan plan = new Plan();
		plan.setActivo(true);
		plan.setClasesDisponibles(planDTO.getClasesDisponibles());
		plan.setFechaFin(planDTO.getFechaFin());
		plan.setFechaInicio(planDTO.getFechaInicio());
		plan.setId(planDTO.getId());
		plan.setSesionesAsistidas(planDTO.getSesionesAsistidas());
		plan.setSesionesReservadas(planDTO.getSesionesReservadas());
		TipoPlan  tipoPlan = repositorioTipoPlan.findTipoPlanByNombre(planDTO.getTipoPlan());
		plan.setTipoPlan(tipoPlan);
		repositorio.save(plan);
	}
	@Override
	public void deletePlan(Plan plan) {
		repositorio.delete(plan);
	}
}
