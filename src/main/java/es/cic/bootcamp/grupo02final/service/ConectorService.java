package es.cic.bootcamp.grupo02final.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.bootcamp.grupo02final.dto.ConectorDTO;
import es.cic.bootcamp.grupo02final.exception.IdNoValidoException;
import es.cic.bootcamp.grupo02final.exception.RegistroNoExisteException;
import es.cic.bootcamp.grupo02final.exception.RegistroYaCreadoException;
import es.cic.bootcamp.grupo02final.helper.ConectorHelper;
import es.cic.bootcamp.grupo02final.model.Conector;
import es.cic.bootcamp.grupo02final.repository.ConectorRepository;

@Service
public class ConectorService {
	
	@Autowired
	private ConectorHelper conectorHelper;
	
	@Autowired
	private ConectorRepository conectorRepository;
	
	public Long create(ConectorDTO conectorDTO) {
		
		if(conectorDTO.getId() != null) {
			throw new RegistroYaCreadoException("El registro introducido ya existe");
		}
		
		Conector conector = new Conector();
		conectorHelper.DTO2Entity(conectorDTO, conector);
		
		ConectorDTO conectorDTOGuardado = conectorHelper.entity2DTO(conectorRepository.save(conector));
		
		return conectorDTOGuardado.getId();
	}
	
	public ConectorDTO findById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Conector> optional = conectorRepository.findById(id);
		if(optional.isPresent()) {
			return conectorHelper.entity2DTO(optional.get());
		}else {
			throw new RegistroNoExisteException("El registro con el id introducido no existe");
		}
	}
	
	public List<ConectorDTO> findAll(){
		
		List<Conector> conectores = new ArrayList<>();
		conectorRepository.findAll()
			.forEach(conectores::add);
		
		List<ConectorDTO> conectoresDTO = new ArrayList<>();
		
		for(int i = 0; i < conectores.size(); i++) {
			conectoresDTO.add(conectorHelper.entity2DTO(conectores.get(i)));
		}
			
		return conectoresDTO;
	}
	
	public ConectorDTO update(ConectorDTO conectorDTO) {
		
		if(conectorDTO.getId() == null) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
		Optional<Conector> optional = conectorRepository.findById(conectorDTO.getId());
		if(optional.isEmpty()) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
		if(conectorDTO.getId() <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Conector conector = new Conector();
		
		return conectorHelper.entity2DTO(conectorRepository.save(conectorHelper.DTO2Entity(conectorDTO, conector)));
		
	}
	
	public void deleteById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Conector> optional = conectorRepository.findById(id);
		if(optional.isPresent()) {
			conectorRepository.deleteById(id);
		}else {
			throw new RegistroNoExisteException("El registro con el id introducido no existe");
		}
		
	}

}
