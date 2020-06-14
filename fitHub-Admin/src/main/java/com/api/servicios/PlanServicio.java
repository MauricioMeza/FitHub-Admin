package com.api.servicios;

import com.api.dto.PlanDTO;
import com.api.modelos.Plan;
import com.api.modelos.Sesion;
import com.api.modelos.TipoPlan;
import com.api.modelos.Usuario;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface PlanServicio {

	Plan getPlanById(String idPlan);
    List<Plan> getAllPlans();
    Usuario addNewPlan(TipoPlan tipoPlan, Usuario usuario);
    Plan addPlan(Plan plan);
    List<Usuario> usuariosInscritos(String idPlan);
    void actuaizarListasSesiones(String idUsuario);

}
