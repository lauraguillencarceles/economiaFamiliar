package es.ecofam.economiafamiliar.control;

import es.ecofam.economiafamiliar.dto.LoginRequest;
import es.ecofam.economiafamiliar.dto.LoginResponse;
import es.ecofam.economiafamiliar.dto.UserRegisterDTO;
import es.ecofam.economiafamiliar.modelo.dao.IUsuarioDAO;
import es.ecofam.economiafamiliar.modelo.pojos.PlanEconomico;
import es.ecofam.economiafamiliar.modelo.pojos.Usuario;
import es.ecofam.economiafamiliar.seguridad.JwtTokenProvider;
import es.ecofam.economiafamiliar.servicio.UsuarioServicio;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ecoFam-apiRest/usuario")
public class ControlUsuario {
    @Autowired
    IUsuarioDAO usuarioDAO;
    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

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

    @GetMapping("/username/{username}")
    public ResponseEntity<?> buscarUsuarioPorId(@PathVariable(value="username") String username) {
        Optional<Usuario> usuario = usuarioDAO.findByUsername(username);
        if (usuario.isPresent()) {
            return ResponseEntity.ok().body(usuario.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/auth/register")
    public Usuario guardarUsuario(@Validated @RequestBody UserRegisterDTO userDTO, HttpServletResponse response) {
        try {
            response.setStatus(HttpServletResponse.SC_OK);
            return  this.usuarioServicio.save(userDTO);
        }catch (Exception e) {
            ResponseEntity.unprocessableEntity();
            System.err.println("Error guardando el usuario"+e);
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return null;
        }
    }

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody LoginRequest loginDTO){
        Authentication authDTO = new UsernamePasswordAuthenticationToken(loginDTO.username(), loginDTO.password());

        Authentication authentication = this.authManager.authenticate(authDTO);
        Usuario usuario = (Usuario) authentication.getPrincipal();

        String token = this.jwtTokenProvider.generateToken(authentication);

        return new LoginResponse(usuario.getId(), usuario.getUsername(), usuario.getNombreCompleto(),
                usuario.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList(),
                token);
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
