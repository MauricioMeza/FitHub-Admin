package com.api.servicios;

import com.api.dto.PlanDTO;
import com.api.modelos.Plan;
import com.api.modelos.Sesion;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PlanServicio {

	Plan getPlanById(String idPlan);
    List<Plan> getAllPlans();
    Plan addPlan(Plan plan);

}
