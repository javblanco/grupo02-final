package es.cic.bootcamp.grupo02final.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cic.bootcamp.grupo02final.exception.IdNoValidoException;
import es.cic.bootcamp.grupo02final.exception.RegistroNoExisteException;
import es.cic.bootcamp.grupo02final.exception.RegistroYaCreadoException;
import es.cic.bootcamp.grupo02final.model.Instancia;
import es.cic.bootcamp.grupo02final.repository.InstanciaRepository;

@Service
public class InstanciaService {
	
	private static final String ID_NO_VALIDO = "El id introducido no es válido";
	
	private static final String REGISTRO_NO_EXISTE = "El registro introducido no existe";
	
	@Autowired
	private InstanciaRepository instanciaRepository;
	
	public Long create(Instancia instancia) {
		
		if(instancia.getId() != null) {
			throw new RegistroYaCreadoException("El registro introducido ya existe");
		}
		
		return instanciaRepository.save(instancia).getId();
	}
	
	public Instancia findById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException(ID_NO_VALIDO);
		}
		
		Optional<Instancia> optional = instanciaRepository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
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
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
		}
		
		if(instancia.getId() <= 0) {
			throw new IdNoValidoException(ID_NO_VALIDO);
		}
		
		Optional<Instancia> optional = instanciaRepository.findById(instancia.getId());
		if(optional.isEmpty()) {
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
		}
		
		return instanciaRepository.save(instancia);
		
	}
	
	public void deleteById(Long id) {
		
		if(id <= 0) {
			throw new IdNoValidoException(ID_NO_VALIDO);
		}
		
		Optional<Instancia> optional = instanciaRepository.findById(id);
		if(optional.isPresent()) {
			instanciaRepository.deleteById(id);
		}else {
			throw new RegistroNoExisteException(REGISTRO_NO_EXISTE);
		}
		
	}

}
