package ar.com.reba.testproject.controller;

import ar.com.reba.testproject.dto.NuevaPersonaDTO;
import ar.com.reba.testproject.dto.PersonaDTO;
import ar.com.reba.testproject.service.PersonaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ApiOperation(value = "Verifica que el primero sea padre del segundo.")
    public ResponseEntity esPadreDe(@PathVariable(name="id1") Integer id1,
                                    @PathVariable(name="id2") Integer id2) {

        Boolean esPadre = personaService.esPadreDeRelacion(id1, id2);

        return ResponseBuilder.create(esPadre, HttpStatus.OK);
    }

    @GetMapping("/relaciones/{id1}/{id2}")
    @ApiOperation(value = "Verifica que tipo de relacion tienen el primero y segundo.")
    public ResponseEntity validarRelacion(@PathVariable(name="id1") Integer id1,
                                          @PathVariable(name="id2") Integer id2) {

        String tipoRelacion = personaService.tipoRelacion(id1, id2);

        return ResponseBuilder.create(tipoRelacion, HttpStatus.OK);
    }

    @GetMapping("/personas")
    @ApiOperation(value = "Obtiene la lista de personas registradas.")
    public ResponseEntity getPersonas() {

        List<PersonaDTO> personas = personaService.getPersonas();

        return ResponseBuilder.create(personas, HttpStatus.OK);
    }

    @PostMapping("/personas/crear")
    @ApiOperation(value = "Crea persona.")
    public ResponseEntity createPersonas(NuevaPersonaDTO body) {

        String errorMensaje = datosPersonaValidar(body);

//        if(datosPersonaValidar(body)) {
        if(errorMensaje == null) {
            personaService.addPersona(body);
            return ResponseBuilder.create(HttpStatus.CREATED);
        }

        return ResponseBuilder.create(errorMensaje, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/personas/crear/{id1}/padre/{id2}")
    @ApiOperation(value = "Crea relacion con el primero siendo padre del segundo.")
    public ResponseEntity createRelacion(@PathVariable(name="id1") Integer id1,
                                         @PathVariable(name="id2") Integer id2) {

        personaService.addRelacion(id1, id2);
        return ResponseBuilder.create(HttpStatus.ACCEPTED);
    }

    private static String datosPersonaValidar(NuevaPersonaDTO body) {
        Integer EDAD_MINIMA = 18;

        if(body.getNombre().isEmpty() || body.getApellido().isEmpty()) {
            return "Los campos nombre y apellido no pueden estar vacios.";
        }

        if(body.getPais().isEmpty() || body.getDocumento().getNumero().isEmpty() || body.getDocumento().getTipo().isEmpty()) {
            return "El pais y los datos de documento no pueden ser vacios.";
        }

        if(body.getEdad() <= EDAD_MINIMA) {
            return "La persona tiene que ser mayor de 18 años.";
        }

        return null;
    }
}