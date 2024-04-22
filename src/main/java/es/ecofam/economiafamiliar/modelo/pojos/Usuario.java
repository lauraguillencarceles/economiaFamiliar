package es.ecofam.economiafamiliar.modelo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import es.ecofam.economiafamiliar.modelo.UserAuthority;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;



@Entity
public class Usuario implements UserDetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre_completo", nullable = true, length = 255)
    private String nombreCompleto;
    @Basic
    @Column(name = "username", nullable = false, length = 16)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = 255)
    private String password;
    @OneToMany(mappedBy = "creador")
    @JsonIgnoreProperties("usuarios")
    private Collection<PlanEconomico> planesEconomicosCreados;
    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuarios")
    private Collection<UsuarioPlan> planesEconomicosAcceso;

    //Para que no se mapee a base de datos
    @Transient
    private List<UserAuthority> authorities ;

    public Usuario(String nombreCompleto, String username, String password
                   ,List<UserAuthority> authorities) {
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    public Usuario(int id, String nombreCompleto, String username, String password
            ,List<UserAuthority> authorities) {
        this.id = id;
        this.nombreCompleto = nombreCompleto;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }
    public Usuario() {
        this.authorities = List.of(UserAuthority.READ, UserAuthority.WRITE);
    }
    public Usuario(int id) {
        this.id = id;
        this.authorities = List.of(UserAuthority.READ, UserAuthority.WRITE);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String contra) {
        this.password = contra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && Objects.equals(nombreCompleto, usuario.nombreCompleto) && Objects.equals(username, usuario.username) && Objects.equals(password, usuario.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreCompleto, username, password);
    }

    public Collection<PlanEconomico> getPlanesEconomicosCreados() {
        return planesEconomicosCreados;
    }

    public void setPlanesEconomicosCreados(Collection<PlanEconomico> planesEconomicosCreados) {
        this.planesEconomicosCreados = planesEconomicosCreados;
    }

    public Collection<UsuarioPlan> getPlanesEconomicosAcceso() {
        return planesEconomicosAcceso;
    }

    public void setPlanesEconomicosAcceso(Collection<UsuarioPlan> planesEconomicosAcceso) {
        this.planesEconomicosAcceso = planesEconomicosAcceso;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", nick='" + username + '\'' +
                //", contra='" + contra + '\'' +
                ", planesEconomicosCreados=" + planesEconomicosCreados +
                ", planesEconomicosAcceso=" + planesEconomicosAcceso +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.toString()))
                .toList();
    }



    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
