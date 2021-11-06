package ar.com.reba.testproject.dao;

import ar.com.reba.testproject.entity.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer> {

    Persona findByDocumentoNumeroAndDocumentoTipoAndPais(String documentoNumero, String documentoTipo, String pais);
}
