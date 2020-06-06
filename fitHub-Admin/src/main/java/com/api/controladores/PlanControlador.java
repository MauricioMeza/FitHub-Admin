package com.api.controladores;

import com.api.modelos.Plan;
import com.api.servicios.PlanServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class PlanControlador {
	
	@Autowired
	private PlanServicio servicio;
	
	@GetMapping("/encontrarTodosLosPlanes")
	public List<Plan> getPlanes(){
		return servicio.getAllPlans();
	}
	
	@GetMapping("/encontrarPlan/{id}")
	public Plan getSesion(@PathVariable String id){
		return servicio.getPlanById(id);
	}
	
}
