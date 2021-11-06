package ar.com.reba.testproject.controller;

import ar.com.reba.testproject.restservice.PersonaDTO;
import ar.com.reba.testproject.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping("/personas/{id1}/padre/{id2}")
    public Boolean esPadreDe(@PathVariable(name="id1") Integer id1,
                             @PathVariable(name="id2") Integer id2) {

        Boolean esPadre = personaService.esPadreDeRelacion(id1, id2);

        return esPadre;
    }

    @GetMapping("/relaciones/{id1}/{id2}")
    public String validarRelacion(@PathVariable(name="id1") Integer id1,
                                  @PathVariable(name="id2") Integer id2) {

        String tipoRelacion = personaService.tipoRelacion(id1, id2);

        return tipoRelacion;
    }

    @GetMapping("/personas")
    public List<PersonaDTO> getPersonas() {

        List<PersonaDTO> personas = personaService.getPersonas();

        return personas;
    }
}