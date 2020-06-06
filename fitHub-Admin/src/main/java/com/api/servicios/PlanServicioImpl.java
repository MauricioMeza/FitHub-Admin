package com.api.servicios;

import com.api.dto.PlanDTO;
import com.api.modelos.Plan;
import com.api.modelos.Sesion;
import com.api.repositorios.PlanRepositorio;
import com.api.repositorios.TipoPlanRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlanServicioImpl implements PlanServicio {

    @Autowired
    PlanRepositorio repositorio;
    @Autowired
    TipoPlanRepositorio repositorioTipoPlan;
    
    
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
	public Plan addPlan(Plan plan) {
		repositorio.save(plan);
		return plan;
	}
	
}
