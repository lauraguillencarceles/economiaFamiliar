package es.ecofam.economiafamiliar.modelo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "plan_economico")
public class PlanEconomico {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 64)
    private String nombre;
    @OneToMany(mappedBy = "planEconomico")
    @JsonIgnore
    private Collection<Anotacion> anotaciones;
    @ManyToOne
    @JoinColumn(name = "id_creador", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties({"planesEconomicosCreados", "planesEconomicosAcceso"})
    private Usuario creador;
    @OneToMany(mappedBy = "planEconomico")
    @JsonIgnore
    private Collection<UsuarioPlan> usuarios;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanEconomico that = (PlanEconomico) o;
        return id == that.id && Objects.equals(nombre, that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }

    public Collection<Anotacion> getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(Collection<Anotacion> anotaciones) {
        this.anotaciones = anotaciones;
    }

    public Usuario getCreador() {
        return creador;
    }

    public void setCreador(Usuario creador) {
        this.creador = creador;
    }

    public Collection<UsuarioPlan> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Collection<UsuarioPlan> usuarios) {
        this.usuarios = usuarios;
    }

    @Override
    public String toString() {
        return "PlanEconomico{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                //", anotaciones=" + anotaciones +
                ", creador=" + creador.getNick() +
                ", usuarios=" + usuarios +
                '}';
    }
}
