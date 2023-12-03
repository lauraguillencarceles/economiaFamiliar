package es.ecofam.economiafamiliar.control;

import es.ecofam.economiafamiliar.modelo.dao.IPlanEconomicoDAO;
import es.ecofam.economiafamiliar.modelo.pojos.Anotacion;
import es.ecofam.economiafamiliar.modelo.pojos.PlanEconomico;
import es.ecofam.economiafamiliar.modelo.pojos.Usuario;
import es.ecofam.economiafamiliar.modelo.pojos.UsuarioPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ecoFam-apiRest/plan")
public class ControlPlanEconomico {
    @Autowired
    IPlanEconomicoDAO planEconomicoDAO;

    @GetMapping
    public List<PlanEconomico> buscarPlanEconomicos() {
        return (List<PlanEconomico>) planEconomicoDAO.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPlanEconomicoPorId(@PathVariable(value="id") int id) {
        Optional<PlanEconomico> planEconomico = planEconomicoDAO.findById(id);
        if (planEconomico.isPresent()) {
            return ResponseEntity.ok().body(planEconomico.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("anotaPlan/{id}")
    public Collection<Anotacion> getAnotacionesByPlan(@PathVariable(value="id") int id) {
        Optional<PlanEconomico> planEconomico = planEconomicoDAO.findById(id);
        if (planEconomico.isPresent()) {
            return planEconomico.get().getAnotaciones();
        }
        return new LinkedList<>();
    }

    @GetMapping("usuPlan/{id}")
    public Collection<Usuario> getusuariosByPlan(@PathVariable(value="id") int id) {
        Optional<PlanEconomico> planEconomico = planEconomicoDAO.findById(id);
        Set<Usuario> usuarios = new HashSet<Usuario>();
        if (planEconomico.isPresent()) {
            for (UsuarioPlan usuarioPlan : planEconomico.get().getUsuarios()) {
                usuarios.add(usuarioPlan.getUsuario());
            }
        }
        return usuarios;
    }


    @PostMapping
    public PlanEconomico guardarPlanEconomico(@Validated @RequestBody PlanEconomico planEconomico) {
        return planEconomicoDAO.save(planEconomico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrarPlanEconomico(@PathVariable(value="id") int id) {
        Optional<PlanEconomico> planEconomico = planEconomicoDAO.findById(id);
        if (planEconomico.isPresent()) {
            planEconomicoDAO.deleteById(id);
            return ResponseEntity.ok().body("Borrado");
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
}
