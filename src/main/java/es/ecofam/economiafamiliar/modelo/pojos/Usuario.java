package es.ecofam.economiafamiliar.modelo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;


@Entity
public class Usuario {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre_completo", nullable = true, length = 255)
    private String nombreCompleto;
    @Basic
    @Column(name = "nick", nullable = false, length = 16)
    private String nick;
    @Basic
    @Column(name = "contra", nullable = false, length = 255)
    private String contra;
    @OneToMany(mappedBy = "creador")
    @JsonIgnoreProperties("usuarios")
    private Collection<PlanEconomico> planesEconomicosCreados;
    @OneToMany(mappedBy = "usuario")
    @JsonIgnoreProperties("usuarios")
    private Collection<UsuarioPlan> planesEconomicosAcceso;

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

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return id == usuario.id && Objects.equals(nombreCompleto, usuario.nombreCompleto) && Objects.equals(nick, usuario.nick) && Objects.equals(contra, usuario.contra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombreCompleto, nick, contra);
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
                ", nick='" + nick + '\'' +
                //", contra='" + contra + '\'' +
                ", planesEconomicosCreados=" + planesEconomicosCreados +
                ", planesEconomicosAcceso=" + planesEconomicosAcceso +
                '}';
    }
}
