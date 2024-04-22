package es.ecofam.economiafamiliar.modelo.dao;

import es.ecofam.economiafamiliar.modelo.pojos.Usuario;
import es.ecofam.economiafamiliar.modelo.pojos.UsuarioPlan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioPlanDAO extends CrudRepository<UsuarioPlan, Integer> {
    Optional<UsuarioPlan> findUsuarioPlansByUsuarioIdAndPlanEconomicoId(int user, int plan);

}
