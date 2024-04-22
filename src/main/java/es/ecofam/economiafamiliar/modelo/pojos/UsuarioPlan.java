package es.ecofam.economiafamiliar.modelo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Objects;
//"select distinct p from PlanEconomico p left join UsuarioPlan up on up.planEconomico.id = p.id where p.creador.username = :userName or up.usuario.username = :userName"
@NamedQueries({
        @NamedQuery(name = "UsuarioPlan.planesByUser", query = "select p from UsuarioPlan up, PlanEconomico p where up.planEconomico.id = p.id and up.usuario.username = :userName"),
        @NamedQuery(name = "UsuarioPlan.planesByCreador", query = "select p from PlanEconomico p where p.creador.username = :userName")
})
@Entity
@Table(name="usuario_plan")
public class UsuarioPlan {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("planesEconomicosAcceso")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_plan", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("usuarios")
    private PlanEconomico planEconomico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioPlan that = (UsuarioPlan) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PlanEconomico getPlanEconomico() {
        return planEconomico;
    }

    public void setPlanEconomico(PlanEconomico planEconomico) {
        this.planEconomico = planEconomico;
    }

    @Override
    public String toString() {
        return "UsuarioPlan{" +
                "id=" + id +
                ", usuario=" + usuario.getUsername() +
                ", planEconomico=" + planEconomico.getNombre() +
                '}';
    }
}
