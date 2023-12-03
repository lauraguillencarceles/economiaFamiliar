package es.ecofam.economiafamiliar.control;

import es.ecofam.economiafamiliar.modelo.dao.ICategoriaDAO;
import es.ecofam.economiafamiliar.modelo.pojos.Categoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ecoFam-apiRest/categoria")
public class ControlCategoria {
    @Autowired
    ICategoriaDAO categoriaDAO;

    @GetMapping
    public List<Categoria> buscarCategorias() {
        return (List<Categoria>) categoriaDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCategoriaPorId(@PathVariable(value="id") int id) {
        Optional<Categoria> categoria = categoriaDAO.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok().body(categoria.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Categoria guardarCategoria(@Validated @RequestBody Categoria categoria) {
        return categoriaDAO.save(categoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarCategoria(@PathVariable(value="id") int id) {
        Optional<Categoria> categoria = categoriaDAO.findById(id);
        if (categoria.isPresent()) {
            categoriaDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
