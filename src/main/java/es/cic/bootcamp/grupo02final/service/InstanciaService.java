package es.cic.bootcamp.grupo02final.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.bootcamp.grupo02final.exception.IdNoValidoException;
import es.cic.bootcamp.grupo02final.exception.RegistroNoExisteException;
import es.cic.bootcamp.grupo02final.exception.RegistroYaCreadoException;
import es.cic.bootcamp.grupo02final.model.Conector;
import es.cic.bootcamp.grupo02final.model.Instancia;
import es.cic.bootcamp.grupo02final.repository.ConectorRepository;
import es.cic.bootcamp.grupo02final.repository.InstanciaRepository;

@Service
public class InstanciaService {

	@Autowired
	private InstanciaRepository instanciaRepository;
	
	public Long save(Instancia instancia) {
		
		if(instancia.getId() != null) {
			throw new RegistroYaCreadoException("El registro introducido ya existe");
		}
		
		return instanciaRepository.save(instancia).getId();
	}
	
	public Instancia findById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Instancia> optional = instanciaRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new RegistroNoExisteException("El registro con el id introducido no existe");
		}
	}
	
	public List<Instancia> findAll(){
		
		List<Instancia> instancias = new ArrayList<>();
		instanciaRepository.findAll()
			.forEach(instancias::add);
			
		return instancias;
	}
	
	public Instancia update(Instancia instancia) {
		
		if(instancia.getId() == null) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
		Optional<Instancia> optional = instanciaRepository.findById(instancia.getId());
		if(optional.isEmpty()) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
		if(instancia.getId() <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		
		return instanciaRepository.save(instancia);
		
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
