package dao;

import models.Student;
import models.Unit;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.List;

public class Sql2oUnitDao implements UnitDao {
    private final Sql2o sql2o;

    public Sql2oUnitDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Unit unit) {
    String sql = " INSERT INTO units(math, english, kiswahili, science, socialstudy, cre, studentid)VALUES(:math, :english, :kiswahili, :science, :socialStudy, :cre, :studentId)";
        try(Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql, true)
                    .bind(unit)
                    .executeUpdate()
                    .getKey();
            unit.setId(id);
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Unit> getAll() {
        try(Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM units")
                    .executeAndFetch(Unit.class);
        }
    }

    @Override
    public List<Unit> getAllUnitsByStudents(int studentId) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM units WHERE studentId = :studentId")
                    .addParameter("studentId", studentId)
                    .executeAndFetch(Unit.class);
        }
    }
    @Override
    public void deleteById(int id) {
        String sql = "DELETE from units WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from units";
        try(Connection con = sql2o.open()){
            con.createQuery(sql, true)
                    .executeUpdate();
        }catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
