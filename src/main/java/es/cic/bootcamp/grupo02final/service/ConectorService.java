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
	
	private static final String ID_NO_VALIDO = "El id introducido no es válido";
	
	private static final String REGISTRO_NO_EXISTE = "El registro introducido no existe";
	
	@Autowired
	private ConectorRepository conectorRepository;
	
	public Long create(Conector conector) {
		
		if(conector.getId() != null) {
			throw new RegistroYaCreadoException("El registro introducido ya existe");
		}
		
		return conectorRepository.save(conector).getId();
	}
	
	public Conector findById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException(ID_NO_VALIDO);
		}
		
		Optional<Conector> optional = conectorRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
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
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
		}
		
		if(conector.getId() <= 0) {
			throw new IdNoValidoException(ID_NO_VALIDO);
		}
		
		Optional<Conector> optional = conectorRepository.findById(conector.getId());
		if(optional.isEmpty()) {
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
		}		
		
		return conectorRepository.save(conector);
		
	}
	
	public void deleteById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException(ID_NO_VALIDO);
		}
		
		Optional<Conector> optional = conectorRepository.findById(id);
		if(optional.isPresent()) {
			conectorRepository.deleteById(id);
		}else {
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
		}
		
	}

}
