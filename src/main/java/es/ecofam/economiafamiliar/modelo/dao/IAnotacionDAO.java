package es.ecofam.economiafamiliar.modelo.dao;

import es.ecofam.economiafamiliar.modelo.pojos.Anotacion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAnotacionDAO extends CrudRepository<Anotacion, Integer> {
}
