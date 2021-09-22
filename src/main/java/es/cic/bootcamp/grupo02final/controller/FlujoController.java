package es.cic.bootcamp.grupo02final.controller;

import java.util.List;

<<<<<<< HEAD
=======
import javax.validation.Valid;

>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
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
<<<<<<< HEAD
    FlujoService flujoService;

    @PostMapping
    @ResponseBody
    public Long create(@RequestBody Flujo dto){
        return flujoService.create(dto);
=======
    private FlujoService flujoService;
    
	public void setFlujoService(FlujoService flujoService) {
		this.flujoService = flujoService;
	}

    @PostMapping
    public Long create(@Valid @RequestBody Flujo flujo){
        return flujoService.create(flujo);
>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Flujo findById(@PathVariable(name = "id") Long id){
       return flujoService.findById(id);
    }

    @GetMapping
    @ResponseBody
    public List<Flujo> findAll(){
        return flujoService.findAll();
    }

    @PutMapping
    @ResponseBody
<<<<<<< HEAD
    public Flujo update(@RequestBody Flujo dto){
        return flujoService.update(dto);
=======
    public Flujo update(@Valid @RequestBody Flujo flujo){
        return flujoService.update(flujo);
>>>>>>> 8b60d04f282c14916663aeed094eb681b31444fb
    }
}
