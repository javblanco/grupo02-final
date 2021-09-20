package es.cic.bootcamp.grupo02final.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.cic.bootcamp.grupo02final.DTO.FlujoDTO;
import es.cic.bootcamp.grupo02final.model.Flujo;
import es.cic.bootcamp.grupo02final.service.FlujoService;

@RestController
@RequestMapping("/flujo")
public class FlujoController {
    
    @Autowired
    FlujoService flujoService;

    @PostMapping
    @ResponseBody
    public Long create(@RequestBody FlujoDTO dto){
        return flujoService.create(dto);
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
    public FlujoDTO update(@RequestBody FlujoDTO dto){
        return flujoService.update(dto);
    }
}
