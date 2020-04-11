package com.api.controladores;

import java.util.List;
import java.util.Optional;

import com.api.servicios.PlanServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.modelos.Plan;

@RestController
public class PlanControlador {
	
	@Autowired
	private PlanServicio servicio;
	
	@PostMapping("/agregarPlan")
	public String GuardarPlan(@RequestBody Plan plan) {
		servicio.savePlan(plan);
		return "Plan añadido con id: "+ plan.getIdPlan();
	}
	
	@GetMapping("/encontrarTodosLosPlanes")
	public List<Plan> getPlan(){
		return servicio.findAllPlans();
	}
	
	@GetMapping("/encontrarPlan/{idPlan}")
	public Optional<Plan> getPlan(@PathVariable int idPlan){
		return servicio.findPlanById(idPlan);
	}
	
	@DeleteMapping("/borrarPlan/{idPlan}")
	public String borrarPlan(@PathVariable int idPlan){
		servicio.deletePlanById(idPlan);
		return "El plan con el Id: "+ idPlan+" ha sido borrado";
	}
	
	
	
}
