package es.ecofam.economiafamiliar.modelo.dao;

import es.ecofam.economiafamiliar.modelo.pojos.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioDAO extends CrudRepository<Usuario, Integer> {
}
