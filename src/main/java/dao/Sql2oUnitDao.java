package dao;

import models.Unit;
import org.sql2o.Sql2o;

import java.util.List;

public class Sql2oUnitDao implements UnitDao {
    private final Sql2o sql2o;

    public Sql2oUnitDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Unit unit) {

    }

    @Override
    public List<Unit> getAllUnitsByStudentId(int studentId) {
        return null;
    }

    @Override
    public Unit findById(int id) {
        return null;
    }

    @Override
    public void deleteById() {

    }

    @Override
    public void clearAll() {

    }
}
