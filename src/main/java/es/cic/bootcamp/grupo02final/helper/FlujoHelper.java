package es.cic.bootcamp.grupo02final.helper;

import es.cic.bootcamp.grupo02final.DTO.FlujoDTO;
import es.cic.bootcamp.grupo02final.model.Flujo;

public class FlujoHelper {
    
    public Flujo dtoToEntity(FlujoDTO dto){
        Flujo flujo = new Flujo();
        if(dto.getId()!=null){
            flujo.setId(dto.getId());
        }
        flujo.setNombre(dto.getNombre());
        flujo.setTiempoInicio(dto.getTiempoInicio());
        flujo.setTiempoFin(dto.getTiempoFin());
        flujo.setEstado(dto.isEstado());
        return flujo;
    }

    public FlujoDTO entityToDto(Flujo flujo){
        FlujoDTO dto = new FlujoDTO();
        dto.setId(flujo.getId());
        dto.setNombre(flujo.getNombre());
        dto.setTiempoInicio(flujo.getTiempoInicio());
        dto.setTiempoFin(flujo.getTiempoFin());
        dto.setEstado(flujo.isEstado());
        return dto;
    }
}
