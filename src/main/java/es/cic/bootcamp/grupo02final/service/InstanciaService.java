package es.cic.bootcamp.grupo02final.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
<<<<<<< HEAD
<<<<<<< HEAD

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.cic.bootcamp.grupo02final.exception.IdNoValidoException;
import es.cic.bootcamp.grupo02final.exception.RegistroNoExisteException;
import es.cic.bootcamp.grupo02final.exception.RegistroYaCreadoException;
import es.cic.bootcamp.grupo02final.model.Conector;
import es.cic.bootcamp.grupo02final.model.Instancia;
import es.cic.bootcamp.grupo02final.repository.ConectorRepository;
=======
=======
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cic.bootcamp.grupo02final.exception.IdNoValidoException;
import es.cic.bootcamp.grupo02final.exception.RegistroNoExisteException;
import es.cic.bootcamp.grupo02final.exception.RegistroYaCreadoException;
import es.cic.bootcamp.grupo02final.model.Instancia;
<<<<<<< HEAD
>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
=======
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899
import es.cic.bootcamp.grupo02final.repository.InstanciaRepository;

@Service
public class InstanciaService {

	@Autowired
	private InstanciaRepository instanciaRepository;
	
<<<<<<< HEAD
<<<<<<< HEAD
	public Long save(Instancia instancia) {
=======
	public Long create(Instancia instancia) {
>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
=======
	public Long create(Instancia instancia) {
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899
		
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
		
<<<<<<< HEAD
<<<<<<< HEAD
		if(instancia.getId() <= 0) {
=======
		if(instancia.getId() == null || instancia.getId() <= 0) {
>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
=======
		if(instancia.getId() == null || instancia.getId() <= 0) {
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899
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
