package es.ecofam.economiafamiliar.modelo.dao;

import es.ecofam.economiafamiliar.modelo.pojos.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IUsuarioDAO extends CrudRepository<Usuario, Integer>  {
    Optional<Usuario> findByUsername(String username);
}
