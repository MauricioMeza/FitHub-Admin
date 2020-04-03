package com.api.servicios;

import com.api.modelos.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanServicio {

    public void savePlan(Plan plan);
    public void deletePlanById(int idPlan);
    public List<Plan> findAllPlans();
    public Optional<Plan> findPlanById(int idPlan);

}
