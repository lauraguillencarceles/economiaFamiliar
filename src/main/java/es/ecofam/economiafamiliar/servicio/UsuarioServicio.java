package es.ecofam.economiafamiliar.servicio;

import es.ecofam.economiafamiliar.dto.UserRegisterDTO;
import es.ecofam.economiafamiliar.modelo.UserAuthority;
import es.ecofam.economiafamiliar.modelo.dao.IUsuarioDAO;
import es.ecofam.economiafamiliar.modelo.pojos.Usuario;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicio {

    private final PasswordEncoder passwordEncoder;
    private final IUsuarioDAO usuarioDAO;

    public UsuarioServicio(PasswordEncoder passwordEncoder, IUsuarioDAO usuarioDAO) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioDAO = usuarioDAO;
    }

    public Optional<Usuario> findByUsername(String username) {
        return this.usuarioDAO.findByUsername(username);
    }

    public Usuario save(UserRegisterDTO userDTO) {

        if (userDTO.password().equals(userDTO.password2())) {
            Usuario usuario = new Usuario(
                    userDTO.nombreCompleto(), userDTO.username(),
                    passwordEncoder.encode(userDTO.password()),
                    List.of(UserAuthority.READ, UserAuthority.WRITE)
            );
            return usuarioDAO.save(usuario);
        }
        return null;
    }

}
