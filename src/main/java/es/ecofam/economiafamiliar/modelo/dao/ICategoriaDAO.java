package es.ecofam.economiafamiliar.modelo.dao;

import es.ecofam.economiafamiliar.modelo.pojos.Categoria;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriaDAO extends CrudRepository<Categoria, Integer> {
}
