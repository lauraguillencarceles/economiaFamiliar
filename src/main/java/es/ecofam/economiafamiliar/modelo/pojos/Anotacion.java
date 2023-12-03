package es.ecofam.economiafamiliar.modelo.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
//import org.hibernate.annotations.NamedQuery;
//import org.hibernate.annotations.NamedQueries;


import java.sql.Date;
import java.util.Objects;

@Entity
@NamedQueries({
    @NamedQuery(name="Anotaciones.ByMonth", query="SELECT a FROM Anotacion a where a.planEconomico.id = :planId and month(a.fecha) = :mes and year(a.fecha) = :year"),
    @NamedQuery(name="Anotaciones.ByDay", query="SELECT a FROM Anotacion a where a.planEconomico.id = :planId and day(a.fecha) = :dia and month(a.fecha) = :mes and year(a.fecha) = :year"),
    @NamedQuery(name="Ingresos.ByMonth", query="SELECT a FROM Anotacion a where a.categoria.tipo='I' and a.planEconomico.id = :planId and month(a.fecha) = :mes and year(a.fecha) = :year"),
    @NamedQuery(name="Ingresos.ByDay", query="SELECT a FROM Anotacion a where a.categoria.tipo='I' and a.planEconomico.id = :planId and day(a.fecha) = :dia and month(a.fecha) = :mes and year(a.fecha) = :year"),
    @NamedQuery(name="Gastos.ByMonth", query="SELECT a FROM Anotacion a where a.categoria.tipo='G' and a.planEconomico.id = :planId and month(a.fecha) = :mes and year(a.fecha) = :year"),
    @NamedQuery(name="Gastos.ByDay", query="SELECT a FROM Anotacion a where a.categoria.tipo='G' and a.planEconomico.id = :planId and day(a.fecha) = :dia and month(a.fecha) = :mes and year(a.fecha) = :year"),

    @NamedQuery(name="SumaIngresos.ByYear", query="SELECT SUM(a.importe) FROM Anotacion a where a.categoria.tipo='I' and a.planEconomico.id = :planId and year(a.fecha) = :year"),
    @NamedQuery(name="SumaIngresos.ByMonth", query="SELECT SUM(a.importe)  FROM Anotacion a where a.categoria.tipo='I' and a.planEconomico.id = :planId and month(a.fecha) = :mes and year(a.fecha) = :year"),
    @NamedQuery(name="SumaIngresos.ByDay", query="SELECT SUM(a.importe)  FROM Anotacion a where a.categoria.tipo='I' and a.planEconomico.id = :planId and day(a.fecha) = :dia and month(a.fecha) = :mes and year(a.fecha) = :year"),

    @NamedQuery(name="SumaGastos.ByYear", query="SELECT SUM(a.importe) FROM Anotacion a where a.categoria.tipo='G' and a.planEconomico.id = :planId and year(a.fecha) = :year"),
    @NamedQuery(name="SumaGastos.ByMonth", query="SELECT SUM(a.importe) FROM Anotacion a where a.categoria.tipo='G' and a.planEconomico.id = :planId and month(a.fecha) = :mes and year(a.fecha) = :year"),
    @NamedQuery(name="SumaGastos.ByDay", query="SELECT SUM(a.importe) FROM Anotacion a where a.categoria.tipo='G' and a.planEconomico.id = :planId and day(a.fecha) = :dia and month(a.fecha) = :mes and year(a.fecha) = :year"),

    @NamedQuery(name="SumaIngresos.ByYearCategoria", query="SELECT SUM(a.importe), a.categoria FROM Anotacion a where a.categoria.tipo='I' and a.planEconomico.id = :planId and year(a.fecha) = :year group by a.categoria"),
    @NamedQuery(name="SumaIngresos.ByMonthCategoria", query="SELECT SUM(a.importe), a.categoria FROM Anotacion a where a.categoria.tipo='I' and a.planEconomico.id = :planId and month(a.fecha) = :mes and year(a.fecha) = :year group by a.categoria"),
    @NamedQuery(name="SumaGastos.ByYearCategoria", query="SELECT SUM(a.importe), a.categoria FROM Anotacion a where a.categoria.tipo='G' and a.planEconomico.id = :planId and year(a.fecha) = :year group by a.categoria"),
    @NamedQuery(name="SumaGastos.ByMonthCategoria", query="SELECT SUM(a.importe), a.categoria FROM Anotacion a where a.categoria.tipo='G' and a.planEconomico.id = :planId and month(a.fecha) = :mes and year(a.fecha) = :year group by a.categoria")
})

public class Anotacion {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "importe", nullable = false, precision = 0)
    private double importe;
    @Basic
    @Column(name = "fecha", nullable = false)
    private Date fecha;
    @Basic
    @Column(name = "descripcion", nullable = true, length = 255)
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "id_categoria", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("anotaciones")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "id_plan", referencedColumnName = "id", nullable = false)
    @JsonIgnoreProperties("anotaciones")
    private PlanEconomico planEconomico;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anotacion anotacion = (Anotacion) o;
        return id == anotacion.id && Double.compare(anotacion.importe, importe) == 0 && Objects.equals(fecha, anotacion.fecha) && Objects.equals(descripcion, anotacion.descripcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, importe, fecha, descripcion);
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public PlanEconomico getPlanEconomico() {
        return planEconomico;
    }

    public void setPlanEconomico(PlanEconomico planEconomico) {
        this.planEconomico = planEconomico;
    }

    @Override
    public String toString() {
        return "Anotacion{" +
                "id=" + id +
                ", importe=" + importe +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", categoria=" +(categoria.getTipo()=="I" ? "Ingreso":"Gasto")+ categoria.getNombre() +
                ", planEconomico=" + planEconomico.getNombre() +
                '}';
    }
}
