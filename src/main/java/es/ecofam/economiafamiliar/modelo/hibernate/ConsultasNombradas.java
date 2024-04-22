package es.ecofam.economiafamiliar.modelo.hibernate;

import es.ecofam.economiafamiliar.modelo.pojos.Anotacion;
import es.ecofam.economiafamiliar.modelo.pojos.PlanEconomico;
import jakarta.persistence.Tuple;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ConsultasNombradas {
    private static final Session session;
    static {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        session = sessionFactory.openSession();
    }

    private static List<Anotacion> devuelveListaMonth(String consulta, int plan, int mes, int year) {
        Query query = session.createNamedQuery(consulta+".ByMonth", Anotacion.class);
        query.setParameter("planId", plan);
        query.setParameter("mes", mes);
        query.setParameter("year", year);
        return query.list();
    }
    private static List<Anotacion> devuelveListaDay(String consulta, int plan, int day, int mes, int year) {
        Query query = session.createNamedQuery(consulta+".ByDay", Anotacion.class);
        query.setParameter("planId", plan);
        query.setParameter("dia", day);
        query.setParameter("mes", mes);
        query.setParameter("year", year);
        return query.list();
    }
    public static List<Anotacion> getAnotacionesByMonth(int plan, int mes, int year) {
        return devuelveListaMonth("Anotaciones", plan, mes, year);
    }
    public static List<Anotacion> getIngresosByMonth(int plan, int mes, int year) {
        return devuelveListaMonth("Ingresos", plan, mes, year);
    }
    public static List<Anotacion> getGastosByMonth(int plan, int mes, int year) {
        return devuelveListaMonth("Gastos", plan, mes, year);
    }

    public static List<Anotacion> getAnotacionesByDay(int plan, int dia, int mes, int year) {
        return devuelveListaDay("Anotaciones", plan, dia, mes, year);
    }
    public static List<Anotacion> getIngresosByDay(int plan, int dia, int mes, int year) {
        return devuelveListaDay("Ingresos", plan, dia, mes, year);
    }

    public static List<Anotacion> getGastosByDay(int plan, int dia, int mes, int year) {
        return devuelveListaDay("Gastos", plan, dia, mes, year);
    }

    private static Object getSumaByYear(String consulta, int plan, int year) {
        Query query = session.createNamedQuery(consulta+".ByYear", Anotacion.class);
        query.setParameter("planId", plan);
        query.setParameter("year", year);
        return query.getSingleResult();
    }
    private static Object getSumaByMonth(String consulta, int plan, int mes, int year) {
        Query query = session.createNamedQuery(consulta+".ByMonth", Anotacion.class);
        query.setParameter("planId", plan);
        query.setParameter("mes", mes);
        query.setParameter("year", year);
        return query.getSingleResult();
    }
    private static Object getSumaByDay(String consulta, int plan, int dia, int mes, int year) {
        Query query = session.createNamedQuery(consulta+".ByDay", Anotacion.class);
        query.setParameter("planId", plan);
        query.setParameter("dia", dia);
        query.setParameter("mes", mes);
        query.setParameter("year", year);
        return query.getSingleResult();
    }

    public static Object getSumaIngresosByYear(int plan, int year) {
        return getSumaByYear("SumaIngresos", plan, year);
    }
    public static Object getSumaGastosByYear(int plan, int year) {
        return getSumaByYear("SumaGastos", plan, year);
    }

    public static Object getSumaIngresosByMonth(int plan, int mes, int year) {
        return getSumaByMonth("SumaIngresos", plan, mes, year);
    }
    public static Object getSumaGastosByMonth(int plan, int mes, int year) {
        return getSumaByMonth("SumaGastos", plan, mes, year);
    }

    public static Object getSumaIngresosByDay(int plan, int dia, int mes, int year) {
        return getSumaByDay("SumaIngresos", plan, dia, mes, year);
    }

    public static Object getSumaGastosByDay(int plan, int dia, int mes, int year) {
        return getSumaByDay("SumaGastos", plan, dia, mes, year);
    }

    public static List<Object[]> getSumaIngresosByYearCategoria(int plan, int year) {
        Query query = session.createNamedQuery("SumaIngresos.ByYearCategoria");
        query.setParameter("planId", plan);
        query.setParameter("year", year);
        List lista = query.list();
        return lista;    }
    public static List<Object[]> getSumaGastosByYearCategoria(int plan, int year) {
        Query query = session.createNamedQuery("SumaGastos.ByYearCategoria");
        query.setParameter("planId", plan);
        query.setParameter("year", year);
        List lista = query.list();
        return lista;    }
    public static List<Object[]> getSumaIngresosByMonthCategoria(int plan, int mes, int year) {
        Query query = session.createNamedQuery("SumaIngresos.ByMonthCategoria");
        query.setParameter("planId", plan);
        query.setParameter("mes", mes);
        query.setParameter("year", year);
        List lista = query.list();
        return lista;
    }
    public static List<Object[]> getSumaGastosByMonthCategoria(int plan, int mes, int year) {
        Query query = session.createNamedQuery("SumaGastos.ByMonthCategoria");
        query.setParameter("planId", plan);
        query.setParameter("mes", mes);
        query.setParameter("year", year);
        List lista = query.list();
        return lista;    }

    public static List<PlanEconomico> getPlanesByUser(String usuario) {
        Query query = session.createNamedQuery("UsuarioPlan.planesByUser");
        query.setParameter("userName", usuario);
        List<PlanEconomico> lista  = (List<PlanEconomico>) query.list();

        query = session.createNamedQuery("UsuarioPlan.planesByCreador");
        query.setParameter("userName", usuario);
        List<PlanEconomico> lista2  = (List<PlanEconomico>) query.list();

        lista.addAll(lista2);
        return lista;
    }
}
