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

import es.cic.bootcamp.grupo02final.model.Flujo;
import es.cic.bootcamp.grupo02final.service.FlujoService;

@RestController
@RequestMapping("/flujo")
public class FlujoController {
    
    @Autowired
    private FlujoService flujoService;
    
	public void setFlujoService(FlujoService flujoService) {
		this.flujoService = flujoService;
	}

    @GetMapping("/lista")
    @ResponseBody
    public List<Flujo> findAll(){
        return flujoService.findAll();
    }

    @GetMapping("/detalle/{id}")
    @ResponseBody
    public Flujo findById(@PathVariable(name = "id") Long id){
       return flujoService.findById(id);
    }

    @PostMapping("/detalle")
    public Long create(@Valid @RequestBody Flujo flujo){
        return flujoService.create(flujo);
    }

    @PutMapping("/detalle")
    @ResponseBody
    public Flujo update(@Valid @RequestBody Flujo flujo){
        return flujoService.update(flujo);
    }

}
