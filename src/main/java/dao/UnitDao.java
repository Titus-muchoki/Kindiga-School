package dao;

import models.Unit;

import java.util.List;

public interface UnitDao {
    //CREATE
    void add(Unit unit);
    //READ
    List<Unit> getAll();
    List<Unit> getAllUnitsByStudentId(int studentId);
//    Unit findById(int id);
    //UPDATE
    //DELETE
    void deleteById(int id);
     void clearAll();
}
