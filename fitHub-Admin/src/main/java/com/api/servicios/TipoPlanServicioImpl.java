package com.api.servicios;

import com.api.dto.TipoPlanDTO;
import com.api.modelos.TipoPlan;
import com.api.repositorios.TipoPlanRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TipoPlanServicioImpl implements TipoPlanServicio {

    @Autowired
    TipoPlanRepositorio repositorio; 

	@Override
	public TipoPlan getTipoPlanById(String id) {
		return repositorio.findTipoPlanById(id);
	}

	@Override
	public List<TipoPlan> getAllTypePlans() {
		return repositorio.findAll();
	}

	@Override
	public TipoPlan addTipoPlan(TipoPlanDTO tipoPlanDTO) {
		TipoPlan tipoPlan = new TipoPlan();
		tipoPlan.setId(tipoPlanDTO.getId());
		tipoPlan.setCantDias(tipoPlanDTO.getCantDias());
		tipoPlan.setNombre(tipoPlanDTO.getNombre());
		tipoPlan.setPrecio(tipoPlanDTO.getPrecio());
		tipoPlan.setCantSesiones(tipoPlanDTO.getCantSesiones());
		repositorio.save(tipoPlan);
		return tipoPlan;
	}

	@Override
	public TipoPlan getTipoPlanByName(String nombre) {
		return repositorio.findTipoPlanByNombre(nombre);
	}

	@Override
    public void deleteTipoPlan(TipoPlan tipoPlan) {
        repositorio.delete(tipoPlan);
    }

	/*
	 * @Override public void cambiarTipoPlan(TipoPlanDTO tipoPlanDTO) { TipoPlan
	 * nuevoTipoPlan = new TipoPlan();
	 * 
	 * nuevoTipoPlan.setId(tipoPlanDTO.getId());
	 * nuevoTipoPlan.setNombre(tipoPlanDTO.getNombre());
	 * nuevoTipoPlan.setCantDias(tipoPlanDTO.getCantDias());
	 * nuevoTipoPlan.setPrecio(tipoPlanDTO.getPrecio());
	 * nuevoTipoPlan.setCantSesiones(tipoPlanDTO.getCantSesiones());
	 * repositorio.save(nuevoTipoPlan); }
	 */
}
