package es.cic.bootcamp.grupo02final.helper;

import org.springframework.stereotype.Component;
import es.cic.bootcamp.grupo02final.dto.InstanciaDTO;
import es.cic.bootcamp.grupo02final.model.Instancia;

@Component
public class InstanciaHelper {
	
	public InstanciaDTO entity2DTO(Instancia instancia) {
		
		InstanciaDTO instanciaDTO = new InstanciaDTO();
		instanciaDTO.setId(instancia.getId());
		// instanciaDTO.setFlujos(instancia.getFlujos());
		instanciaDTO.setNombre(instancia.getNombre());
		return instanciaDTO;
	}
	
	public Instancia DTO2Entity(InstanciaDTO instanciaDTO, Instancia instancia) {
		
		instancia.setId(instanciaDTO.getId());
		instancia.setFlujos(instanciaDTO.getFlujos());
		instancia.setNombre(instanciaDTO.getNombre());
		return instancia;		
	}

}
