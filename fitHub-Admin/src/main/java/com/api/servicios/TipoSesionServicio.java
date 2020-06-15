package com.api.servicios;

import com.api.dto.TipoSesionDTO;
import com.api.modelos.TipoSesion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TipoSesionServicio {

    TipoSesion addTipoSesion(TipoSesionDTO tipoSesionDTO);
    List<TipoSesion> findAllTipos();
    TipoSesion getTipoSesionByNombre(String nombre);
    TipoSesion getTipoSesionById(String id);
    void deleteTipoSesion(TipoSesion tipoSesion);
    void cambiarTipoSesion(TipoSesionDTO tipoSesionDTO);
}
