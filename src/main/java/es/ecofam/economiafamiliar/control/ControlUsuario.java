package es.ecofam.economiafamiliar.control;

import es.ecofam.economiafamiliar.modelo.dao.IUsuarioDAO;
import es.ecofam.economiafamiliar.modelo.pojos.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ecoFam-apiRest/usuario")
public class ControlUsuario {
    @Autowired
    IUsuarioDAO usuarioDAO;

    @GetMapping
    public List<Usuario> buscarUsuarios() {
        return (List<Usuario>) usuarioDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable(value="id") int id) {
        Optional<Usuario> usuario = usuarioDAO.findById(id);
        if (usuario.isPresent()) {
            return ResponseEntity.ok().body(usuario.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Usuario guardarUsuario(@Validated @RequestBody Usuario usuario) {
        return usuarioDAO.save(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarUsuario(@PathVariable(value="id") int id) {
        Optional<Usuario> usuario = usuarioDAO.findById(id);
        if (usuario.isPresent()) {
            usuarioDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
