package es.cic.bootcamp.grupo02final.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.bootcamp.grupo02final.exception.IdNoValidoException;
import es.cic.bootcamp.grupo02final.exception.RegistroNoExisteException;
import es.cic.bootcamp.grupo02final.exception.RegistroYaCreadoException;
import es.cic.bootcamp.grupo02final.model.Flujo;
import es.cic.bootcamp.grupo02final.repository.FlujoRepository;

@Service
public class FlujoService {
	
	private static final String ID_NO_VALIDO = "El id introducido no es válido";
	
	private static final String REGISTRO_NO_EXISTE = "El registro introducido no existe";
	
	@Autowired
	private FlujoRepository flujoRepository;
	
	public Long create(Flujo flujo) {
		
		if(flujo.getId() != null) {
			throw new RegistroYaCreadoException("El registro introducido ya existe");
		}
		
		return flujoRepository.save(flujo).getId();
	}
	
	public Flujo findById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException(ID_NO_VALIDO);
		}
		
		Optional<Flujo> optional = flujoRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
		}
	}
	
	public List<Flujo> findAll(){
		
		List<Flujo> flujos = new ArrayList<>();
		flujoRepository.findAll()
			.forEach(flujos::add);
			
		return flujos;
	}
	
	public Flujo update(Flujo flujo) {
		
		if(flujo.getId() == null) {
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
		}
		
		if(flujo.getId() <= 0) {
			throw new IdNoValidoException(ID_NO_VALIDO);
		}
		
		Optional<Flujo> optional = flujoRepository.findById(flujo.getId());
		if(optional.isEmpty()) {
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
		}
		
		return flujoRepository.save(flujo);
		
	}

}

