package ar.com.reba.testproject.service;

import ar.com.reba.testproject.dao.PersonaRepository;
import ar.com.reba.testproject.entity.Persona;
import ar.com.reba.testproject.entity.Relacion;
import ar.com.reba.testproject.restservice.DocumentoDTO;
import ar.com.reba.testproject.restservice.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<PersonaDTO> getPersonas() {
        Iterable<Persona> personas = personaRepository.findAll();

        List<PersonaDTO> result = new ArrayList<>();
        personas.forEach( p -> {
            PersonaDTO personaDTO = new PersonaDTO();
            personaDTO.setId(p.getPersonaId());
            personaDTO.setNombre(p.getNombre());
            personaDTO.setApellido(p.getApellido());
            personaDTO.setPais(p.getPais());
            personaDTO.setDocumento(new DocumentoDTO());
            personaDTO.getDocumento().setNumero(p.getDocumentoTipo());
            personaDTO.getDocumento().setTipo(p.getDocumentoTipo());

            result.add(personaDTO);
        });

        return result;
    }

    public Persona findByPersonaId(Integer personaId) {
        Optional<Persona> persona = personaRepository.findById(personaId);

        return persona.get();
    }

    public String tipoRelacion(Integer id1, Integer id2) {
        Optional<Persona> p1 = personaRepository.findById(id1);
        Optional<Persona> p2 = personaRepository.findById(id2);
        String tipoDeRelacion = null;

        if(p1.isPresent() && p2.isPresent()) {
            tipoDeRelacion = RelacionEntrePersonas.relacion(p1.get(), p2.get());
        }

        return tipoDeRelacion;
    }

    public Boolean esPadreDeRelacion(Integer id1, Integer id2) {
        Optional<Persona> p1 = personaRepository.findById(id1);
        Optional<Persona> p2 = personaRepository.findById(id2);

        if(p1.isPresent() && p2.isPresent()) {
            return RelacionEntrePersonas.esPadreDe(p1.get(), p2.get());
        }

        //TODO: podria lanzar una exception
        return false;
    }
}