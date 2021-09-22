package es.cic.bootcamp.grupo02final.helper;

import org.springframework.stereotype.Component;
import es.cic.bootcamp.grupo02final.dto.FlujoDTO;
import es.cic.bootcamp.grupo02final.model.Flujo;

@Component
public class FlujoHelper {
	
	public FlujoDTO entity2DTO(Flujo flujo) {
		
		FlujoDTO flujoDTO = new FlujoDTO();
		flujoDTO.setId(flujo.getId());
		flujoDTO.setInstancia(flujo.getInstancia());
		flujoDTO.setNombre(flujo.getNombre());
		flujoDTO.setTiempoFin(flujo.getTiempoFin());
		flujoDTO.setTiempoInicio(flujo.getTiempoInicio());
		flujoDTO.setEstado(flujo.isEstado());
		return flujoDTO;
	}
	
	public Flujo DTO2Entity(FlujoDTO flujoDTO, Flujo flujo) {
		
		flujo.setId(flujoDTO.getId());
		flujo.setInstancia(flujoDTO.getInstancia());
		flujo.setNombre(flujoDTO.getNombre());
		flujo.setTiempoFin(flujoDTO.getTiempoFin());
		flujo.setTiempoInicio(flujoDTO.getTiempoInicio());
		flujo.setEstado(flujoDTO.isEstado());
		return flujo;		
	}

}
