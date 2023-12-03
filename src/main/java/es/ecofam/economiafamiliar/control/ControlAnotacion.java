package es.ecofam.economiafamiliar.control;

import es.ecofam.economiafamiliar.modelo.dao.IAnotacionDAO;
import es.ecofam.economiafamiliar.modelo.hibernate.ConsultasNombradas;
import es.ecofam.economiafamiliar.modelo.pojos.Anotacion;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ecoFam-apiRest/anotacion")
public class ControlAnotacion {
    @Autowired
    IAnotacionDAO anotacionDAO;


    @GetMapping
    public List<Anotacion> buscarAnotaciones() {
        return (List<Anotacion>) anotacionDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarAnotacionPorId(@PathVariable(value="id") int id) {
        Optional<Anotacion> anotacion = anotacionDAO.findById(id);
        if (anotacion.isPresent()) {
            return ResponseEntity.ok().body(anotacion.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/plan/{idPlan}/{mes}/{year}")
    public List<Anotacion> buscarAnotacionesPorPlanMes(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="mes") int mes, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getAnotacionesByMonth(idPlan,mes, year);
    }//buscarAnotacionesPorPlanMes

    @GetMapping("/plan/{idPlan}/{dia}/{mes}/{year}")
    public List<Anotacion> buscarAnotacionesPorPlanDia(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="dia") int dia, @PathVariable(value="mes") int mes, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getAnotacionesByDay(idPlan, dia, mes, year);
    }//buscarAnotacionesPorPlanDia

    @GetMapping("/plan/I/{idPlan}/{mes}/{year}")
    public List<Anotacion> buscarIngresosPorPlanMes(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="mes") int mes, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getIngresosByMonth(idPlan,mes, year);
    }//buscarAnotacionesPorPlanMes

    @GetMapping("/plan/I/{idPlan}/{dia}/{mes}/{year}")
    public List<Anotacion> buscarIngresosPorPlanDia(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="dia") int dia, @PathVariable(value="mes") int mes, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getIngresosByDay(idPlan, dia, mes, year);
    }//buscarAnotacionesPorPlanDia

    @GetMapping("/plan/G/{idPlan}/{mes}/{year}")
    public List<Anotacion> buscarGastosPorPlanMes(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="mes") int mes, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getGastosByMonth(idPlan,mes, year);
    }//buscarAnotacionesPorPlanMes

    @GetMapping("/plan/G/{idPlan}/{dia}/{mes}/{year}")
    public List<Anotacion> buscarGastosPorPlanDia(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="dia") int dia, @PathVariable(value="mes") int mes, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getGastosByDay(idPlan, dia, mes, year);
    }//buscarAnotacionesPorPlanDia

    @GetMapping("/plan/SI/{idPlan}/{year}")
    public Object buscarSumaIngresosPorPlanYear(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getSumaIngresosByYear(idPlan, year);
    }//buscarSumaIngresosPorPlanYear

    @GetMapping("/plan/SI/{idPlan}/{month}/{year}")
    public Object buscarSumaIngresosPorPlanMonth(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="month") int month, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getSumaIngresosByMonth(idPlan, month, year);
    }//buscarSumaIngresosPorPlanMonth

    @GetMapping("/plan/SI/{idPlan}/{day}/{month}/{year}")
    public Object buscarSumaIngresosPorPlanDay(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="day") int day, @PathVariable(value="month") int month, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getSumaIngresosByDay(idPlan, day, month, year);
    }//buscarSumaIngresosPorPlanDay

    @GetMapping("/plan/SG/{idPlan}/{year}")
    public Object buscarSumaGastosPorPlanYear(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getSumaGastosByYear(idPlan, year);
    }//buscarSumaGastosPorPlanYear
    @GetMapping("/plan/SG/{idPlan}/{month}/{year}")
    public Object buscarSumaGastosPorPlanMonth(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="month") int month, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getSumaGastosByMonth(idPlan, month, year);
    }//buscarSumaGastosPorPlanMonth
    @GetMapping("/plan/SG/{idPlan}/{day}/{month}/{year}")
    public Object buscarSumaGastosPorPlanDay(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="day") int day, @PathVariable(value="month") int month, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getSumaGastosByDay(idPlan, day, month, year);
    }//buscarSumaIngresosPorPlanMonth

    @GetMapping("/plan/SIC/{idPlan}/{year}")
    public List<Object[]>  buscarSumaIngresosPorPlanYearCategoria(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getSumaIngresosByYearCategoria(idPlan, year);
    }//buscarSumaIngresosPorPlanYear

    @GetMapping("/plan/SIC/{idPlan}/{month}/{year}")
    public List<Object[]>  buscarSumaIngresosPorPlanMonthCategoria(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="month") int month, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getSumaIngresosByMonthCategoria(idPlan, month, year);
    }//buscarSumaIngresosPorPlanMonth

    @GetMapping("/plan/SGC/{idPlan}/{year}")
    public List<Object[]>  buscarSumaGastosPorPlanYearCategoria(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getSumaGastosByYearCategoria(idPlan, year);
    }//buscarSumaGastosPorPlanYear
    @GetMapping("/plan/SGC/{idPlan}/{month}/{year}")
    public List<Object[]>  buscarSumaGastosPorPlanMonthCategoria(@PathVariable(value="idPlan") int idPlan, @PathVariable(value="month") int month, @PathVariable(value="year") int year) {
        return ConsultasNombradas.getSumaGastosByMonthCategoria(idPlan, month, year);
    }//buscarSumaGastosPorPlanMonth

    @PostMapping
    public Anotacion guardarAnotacion(@Validated @RequestBody Anotacion anotacion) {
        return anotacionDAO.save(anotacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarAnotacion(@PathVariable(value="id") int id) {
        Optional<Anotacion> anotacion = anotacionDAO.findById(id);
        if (anotacion.isPresent()) {
            anotacionDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
