package es.ecofam.economiafamiliar.modelo.dao;

import es.ecofam.economiafamiliar.modelo.pojos.UsuarioPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUsuarioPlanDAO extends CrudRepository<UsuarioPlan, Integer> {
}
