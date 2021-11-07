package ar.com.reba.testproject.dao;

import ar.com.reba.testproject.entity.Relacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelacionRepository extends CrudRepository<Relacion, Integer> {
}