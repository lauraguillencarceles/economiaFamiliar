package es.ecofam.economiafamiliar.modelo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Objects;

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
                ", usuario=" + usuario.getNick() +
                ", planEconomico=" + planEconomico.getNombre() +
                '}';
    }
}
