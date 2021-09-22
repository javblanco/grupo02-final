package es.cic.bootcamp.grupo02final.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import es.cic.bootcamp.grupo02final.dto.FlujoDTO;
import es.cic.bootcamp.grupo02final.service.FlujoService;

@RestController
@RequestMapping("/flujo")
public class FlujoController {
    
    @Autowired
    private FlujoService flujoService;
    
	public void setFlujoService(FlujoService flujoService) {
		this.flujoService = flujoService;
	}

    @PostMapping
    public Long create(@Valid @RequestBody FlujoDTO flujoDTO){
        return flujoService.create(flujoDTO);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public FlujoDTO findById(@PathVariable(name = "id") Long id){
       return flujoService.findById(id);
    }

    @GetMapping
    @ResponseBody
    public List<FlujoDTO> findAll(){
        return flujoService.findAll();
    }

    @PutMapping
    @ResponseBody
    public FlujoDTO update(@Valid @RequestBody FlujoDTO flujoDTO){
        return flujoService.update(flujoDTO);
    }
}
