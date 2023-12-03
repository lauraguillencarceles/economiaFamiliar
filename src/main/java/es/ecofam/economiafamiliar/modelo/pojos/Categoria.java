package es.ecofam.economiafamiliar.modelo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;

@Entity
public class Categoria {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "nombre", nullable = false, length = 64)
    private String nombre;
    @Basic
    @Column(name = "descripcion", nullable = true, length = 255)
    private String descripcion;
    @Basic
    @Column(name = "tipo", nullable = false, length = 1)
    private String tipo;
    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private Collection<Anotacion> anotaciones;

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id && Objects.equals(nombre, categoria.nombre) && Objects.equals(descripcion, categoria.descripcion) && Objects.equals(tipo, categoria.tipo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, tipo);
    }

    public Collection<Anotacion> getAnotaciones() {
        return anotaciones;
    }

    public void setAnotaciones(Collection<Anotacion> anotaciones) {
        this.anotaciones = anotaciones;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", nombre='" + nombre +
                ", descripcion='" + descripcion +
                ", tipo='" + ("I".equals(tipo) ? "Ingreso":"Gasto") +
                //", anotaciones=" + anotaciones +
                "}";
    }
}
