package ar.com.reba.testproject.service;

import ar.com.reba.testproject.dao.ContactoRepository;
import ar.com.reba.testproject.dao.PersonaRepository;
import ar.com.reba.testproject.dto.NuevaPersonaDTO;
import ar.com.reba.testproject.entity.Contacto;
import ar.com.reba.testproject.entity.Persona;
import ar.com.reba.testproject.dto.DocumentoDTO;
import ar.com.reba.testproject.dto.PersonaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private ContactoRepository contactoRepository;

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
            personaDTO.getDocumento().setNumero(p.getDocumentoNumero());
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

    public void addPersona(NuevaPersonaDTO body) {

        Persona existe = personaRepository.findByDocumentoNumeroAndDocumentoTipoAndPais(
                body.getDocumento().getNumero(),
                body.getDocumento().getTipo(),
                body.getPais());

        if(existe == null) {
            Persona persona = new Persona();

            persona.setEdad(body.getEdad());
            persona.setNombre(body.getNombre());
            persona.setApellido(body.getApellido());
            persona.setPais(body.getPais());
            persona.setDocumentoNumero(body.getDocumento().getNumero());
            persona.setDocumentoTipo(body.getDocumento().getTipo());

            Contacto contacto = new Contacto();
            contacto.setTipo(body.getContacto().getTipo());
            contacto.setDescripcion(body.getContacto().getDescripcion());
            contacto.setPersonaByPersonaId(persona);

            persona.setContactosByPersonaId(new ArrayList<>());
            persona.getContactosByPersonaId().add(contacto);

            Persona personaRegistro = personaRepository.save(persona);

        } else {
            //TODO: mensaje que ya existe!
        }

        return;
    }
}
