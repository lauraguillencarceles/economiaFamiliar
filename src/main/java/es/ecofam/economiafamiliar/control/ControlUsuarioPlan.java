package es.ecofam.economiafamiliar.control;

import es.ecofam.economiafamiliar.modelo.dao.IUsuarioPlanDAO;
import es.ecofam.economiafamiliar.modelo.pojos.UsuarioPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ecoFam-apiRest/usuarioplan")
public class ControlUsuarioPlan {
    @Autowired
    IUsuarioPlanDAO usuarioPlanDAO;

    @GetMapping
    public List<UsuarioPlan> buscarUsuarioPlanes() {
        return (List<UsuarioPlan>) usuarioPlanDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPlanPorId(@PathVariable(value="id") int id) {
        Optional<UsuarioPlan> usuarioPlan = usuarioPlanDAO.findById(id);
        if (usuarioPlan.isPresent()) {
            return ResponseEntity.ok().body(usuarioPlan.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public UsuarioPlan guardarUsuarioPlan(@Validated @RequestBody UsuarioPlan usuarioPlan) {
        return usuarioPlanDAO.save(usuarioPlan);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuarioPlan(@PathVariable(value="id") int id) {
        Optional<UsuarioPlan> usuarioPlan = usuarioPlanDAO.findById(id);
        if (usuarioPlan.isPresent()) {
            usuarioPlanDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
