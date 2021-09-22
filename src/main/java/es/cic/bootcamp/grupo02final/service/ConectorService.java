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
import es.cic.bootcamp.grupo02final.repository.ConectorRepository;

@Service
public class ConectorService {
	
	@Autowired
	private ConectorRepository conectorRepository;
	
<<<<<<< HEAD
<<<<<<< HEAD
	public Long save(Conector conector) {
=======
	public Long create(Conector conector) {
>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
=======
	public Long create(Conector conector) {
>>>>>>> 476574c05a452fdd899b41ee3aa661fd6bce8899
		
		if(conector.getId() != null) {
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
	
	public List<Conector> findAll(){
		
		List<Conector> conectores = new ArrayList<>();
		conectorRepository.findAll()
			.forEach(conectores::add);
			
		return conectores;
	}
	
	public Conector update(Conector conector) {
		
		if(conector.getId() == null) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
		Optional<Conector> optional = conectorRepository.findById(conector.getId());
		if(optional.isEmpty()) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
		if(conector.getId() <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
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
