package es.cic.bootcamp.grupo02final.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cic.bootcamp.grupo02final.exception.IdNoValidoException;
import es.cic.bootcamp.grupo02final.exception.RegistroNoExisteException;
import es.cic.bootcamp.grupo02final.exception.RegistroYaCreadoException;
import es.cic.bootcamp.grupo02final.model.Conector;
import es.cic.bootcamp.grupo02final.repository.ConectorRepository;

@Service
public class ConectorService {
	
	@Autowired
	private ConectorRepository conectorRepository;
	
	public Long save(Conector conector) {
		
		Optional<Conector> optional = conectorRepository.findById(conector.getId());
		if(optional.isPresent()) {
			throw new RegistroYaCreadoException("El registro introducido ya existe");
		}
		
		return conectorRepository.save(conector).getId();
	}
	
	public Conector findById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Conector> optional = conectorRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new RegistroNoExisteException("El registro con el id introducido no existe");
		}
	}
	
	public Conector update(Conector conector) {
		
		if(conector.getId() <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Conector> optional = conectorRepository.findById(conector.getId());
		if(optional.isEmpty()) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
		return conectorRepository.save(conector);
		
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
