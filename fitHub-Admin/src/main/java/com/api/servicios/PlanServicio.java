package com.api.servicios;

import com.api.dto.PlanDTO;
import com.api.modelos.Plan;
import com.api.modelos.TipoPlan;
import com.api.modelos.Usuario;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PlanServicio {

	Plan getPlanById(String idPlan);
    List<Plan> getAllPlans();
    List<Plan> getAllActivePlans();
    Usuario addNewPlan(TipoPlan tipoPlan, Usuario usuario);
    Plan addPlan(PlanDTO planDTO);
    Plan addPlan(Plan plan);
    List<Usuario> usuariosInscritos(String idPlan);
    void actuaizarListasSesiones(String idUsuario);
    void cancelarPlan(String idPlan);
    void deletePlan(Plan plan);
    void cambiarPlan(PlanDTO planDTO);

}
