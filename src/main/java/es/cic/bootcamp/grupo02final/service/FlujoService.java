package es.cic.bootcamp.grupo02final.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import es.cic.bootcamp.grupo02final.dto.FlujoDTO;
import es.cic.bootcamp.grupo02final.exception.IdNoValidoException;
import es.cic.bootcamp.grupo02final.exception.RegistroNoExisteException;
import es.cic.bootcamp.grupo02final.exception.RegistroYaCreadoException;
import es.cic.bootcamp.grupo02final.helper.FlujoHelper;
import es.cic.bootcamp.grupo02final.model.Flujo;
import es.cic.bootcamp.grupo02final.repository.FlujoRepository;

@Service
public class FlujoService {

    @Autowired
    private FlujoRepository repository;
    
	@Autowired
	private FlujoHelper flujoHelper;

    public long create(FlujoDTO flujoDTO){
    	
		if(flujoDTO.getId() != null) {
			throw new RegistroYaCreadoException("El registro introducido ya existe");
		}
		
		Flujo flujo = new Flujo();
		
		flujoDTO.setTiempoFin(LocalDate.now());
		
        return flujoHelper.entity2DTO(repository.save(flujoHelper.DTO2Entity(flujoDTO, flujo))).getId();
    }

    public FlujoDTO findById(Long id){
        if(id <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Flujo> optional = repository.findById(id);
		if(optional.isPresent()) {
			return flujoHelper.entity2DTO(optional.get());
		}else {
			throw new RegistroNoExisteException("El registro con el id introducido no existe");
		}
    }

    public List<FlujoDTO> findAll(){
        List<Flujo> flujos = new ArrayList<>();
		repository.findAll()
			.forEach(flujos::add);
		
		List<FlujoDTO> flujosDTO = new ArrayList<>();
		
		for(int i = 0; i < flujos.size(); i++) {
			flujosDTO.add(flujoHelper.entity2DTO(flujos.get(i)));
		}
			
		return flujosDTO;
    }

    public FlujoDTO update(FlujoDTO flujoDTO){
    	
		if(flujoDTO.getId() == null) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
    	
        if(flujoDTO.getId() <= 0) {
			throw new IdNoValidoException("El id introducido no es válido");
		}
		
		Optional<Flujo> optional = repository.findById(flujoDTO.getId());
		if(optional.isEmpty()) {
			throw new RegistroNoExisteException("El registro introducido no existe");
		}
		
		Flujo flujo = new Flujo();

		return flujoHelper.entity2DTO(repository.save(flujoHelper.DTO2Entity(flujoDTO, flujo)));
    }
}
