package es.ecofam.economiafamiliar.seguridad;

import es.ecofam.economiafamiliar.modelo.dao.IUsuarioDAO;
import org.springframework.security.core.userdetails.UserDetailsService;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;

/*
Permite que Spring Security sepa cómo extraer el usuario de base de datos
para realizar la autenticación
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUsuarioDAO userService;

    public UserDetailsServiceImpl(IUsuarioDAO userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userService.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException(username + " no encontrado")
        );
    }
}