package es.cic.bootcamp.grupo02final.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cic.bootcamp.grupo02final.dto.InstanciaDTO;
import es.cic.bootcamp.grupo02final.exception.IdNoValidoException;
import es.cic.bootcamp.grupo02final.exception.RegistroNoExisteException;
import es.cic.bootcamp.grupo02final.exception.RegistroYaCreadoException;
import es.cic.bootcamp.grupo02final.helper.InstanciaHelper;
import es.cic.bootcamp.grupo02final.model.Instancia;
import es.cic.bootcamp.grupo02final.repository.InstanciaRepository;

@Service
public class InstanciaService {

	@Autowired
	private InstanciaRepository instanciaRepository;
	
	@Autowired
	private InstanciaHelper instanciaHelper;
	
	public Long create(InstanciaDTO instanciaDTO) {
		
		if(instanciaDTO.getId() != null) {
			throw new RegistroYaCreadoException("El registro introducido ya existe");
		}
		
		Instancia instancia = new Instancia();
		
		return instanciaHelper.entity2DTO(instanciaRepository.save(instanciaHelper.DTO2Entity(instanciaDTO, instancia))).getId();
	}
	
	public InstanciaDTO findById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Instancia> optional = instanciaRepository.findById(id);
		if(optional.isPresent()) {
			return instanciaHelper.entity2DTO(optional.get());
		}else {
			throw new RegistroNoExisteException("El registro con el id introducido no existe");
		}
	}
	
	public List<InstanciaDTO> findAll(){
		
		List<Instancia> instancias = new ArrayList<>();
		instanciaRepository.findAll()
			.forEach(instancias::add);
		
		List<InstanciaDTO> instanciasDTO = new ArrayList<>();
		
		for(int i = 0; i < instancias.size(); i++) {
			instanciasDTO.add(instanciaHelper.entity2DTO(instancias.get(i)));
		}
			
		return instanciasDTO;
	}
	
	public InstanciaDTO update(InstanciaDTO instanciaDTO) {
		
		if(instanciaDTO.getId() == null) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
		Optional<Instancia> optional = instanciaRepository.findById(instanciaDTO.getId());
		if(optional.isEmpty()) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
		if(instanciaDTO.getId() == null || instanciaDTO.getId() <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Instancia instancia = new Instancia();
		
		return instanciaHelper.entity2DTO(instanciaRepository.save(instanciaHelper.DTO2Entity(instanciaDTO, instancia)));
		
	}
	
	public void deleteById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Instancia> optional = instanciaRepository.findById(id);
		if(optional.isPresent()) {
			instanciaRepository.deleteById(id);
		}else {
			throw new RegistroNoExisteException("El registro con el id introducido no existe");
		}
		
	}
}
