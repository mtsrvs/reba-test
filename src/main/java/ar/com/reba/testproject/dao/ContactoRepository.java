package ar.com.reba.testproject.dao;

import ar.com.reba.testproject.entity.Contacto;
import org.springframework.data.repository.CrudRepository;

public interface ContactoRepository extends CrudRepository<Contacto, Integer> {
}
