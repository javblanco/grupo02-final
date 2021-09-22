package es.cic.bootcamp.grupo02final.helper;

import org.springframework.stereotype.Component;

import es.cic.bootcamp.grupo02final.dto.ConectorDTO;
import es.cic.bootcamp.grupo02final.model.Conector;

@Component
public class ConectorHelper {
	
	public ConectorDTO entity2DTO(Conector conector) {
		
		ConectorDTO conectorDTO = new ConectorDTO();
		conectorDTO.setId(conector.getId());
		conectorDTO.setLenguaje(conector.getLenguaje());
		conectorDTO.setNombre(conector.getNombre());
		conectorDTO.setTipoServicio(conector.getTipoServicio());
		return conectorDTO;
	}
	
	public Conector DTO2Entity(ConectorDTO conectorDTO, Conector conector) {
		
		conector.setId(conectorDTO.getId());
		conector.setLenguaje(conectorDTO.getLenguaje());
		conector.setNombre(conectorDTO.getNombre());
		conector.setTipoServicio(conectorDTO.getTipoServicio());
		return conector;		
	}

}
