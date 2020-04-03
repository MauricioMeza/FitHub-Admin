package com.api.servicios;

import com.api.modelos.Plan;
import com.api.repositorios.PlanRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanServicioImpl implements PlanServicio {

    @Autowired
    PlanRepositorio repositorio;

    @Override
    public void savePlan(Plan plan) {
        repositorio.save(plan);
    }

    @Override
    public void deletePlanById(int idPlan) {
        repositorio.deleteById(idPlan);
    }

    @Override
    public List<Plan> findAllPlans() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Plan> findPlanById(int idPlan) {
        return repositorio.findById(idPlan);
    }

}
