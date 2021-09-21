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

    @Autowired
    private FlujoRepository repository;

    public long create(Flujo flujo){
    	
		if(flujo.getId() != null) {
			throw new RegistroYaCreadoException("El registro introducido ya existe");
		}
        return repository.save(flujo).getId();
    }

    public Flujo findById(Long id){
        if(id <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Flujo> optional = repository.findById(id);
		if(optional.isPresent()) {
			return optional.get();
		}else {
			throw new RegistroNoExisteException("El registro con el id introducido no existe");
		}
    }

    public List<Flujo> findAll(){
        List<Flujo> flujos = new ArrayList<>();
		repository.findAll()
			.forEach(flujos::add);
			
		return flujos;
    }

    public Flujo update(Flujo flujo){
    	
		if(flujo.getId() == null) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
    	
        if(flujo.getId() <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Flujo> optional = repository.findById(flujo.getId());
		if(optional.isEmpty()) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
        flujo =repository.save(flujo);

		return flujo;
    }
}
