import dao.Sql2oStudentDao;
import dao.Sql2oTeacherDao;
import dao.Sql2oUnitDao;
import models.Student;
import models.Teacher;
import models.Unit;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {

        staticFileLocation("/public");
        String connectionString = "jdbc:postgresql://localhost:5432/kindiga";

        Sql2o sql2o = new Sql2o(connectionString, "kajela", "8444");
        Sql2oStudentDao studentDao = new Sql2oStudentDao(sql2o);
        Sql2oUnitDao unitDao = new Sql2oUnitDao(sql2o);
        Sql2oTeacherDao teacherDao = new Sql2oTeacherDao(sql2o);

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Unit> allUnities = unitDao.getAll();
            model.put("unities", allUnities);
            model = new HashMap<>();
            List<Teacher> teachers = teacherDao.getAll();
            model.put("teachers", teachers);
            List<Student> students = studentDao.getAll();
            model.put("students", students);
            return new ModelAndView(model, "layout.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
