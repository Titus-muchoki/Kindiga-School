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

import static spark.Spark.*;

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
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());
        //get: show a form to create a new student

        get("/students/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Student> students = studentDao.getAll(); //refresh list of links for navbar
            model.put("students", students);
            return new ModelAndView(model, "student-form.hbs"); //new layout
        }, new HandlebarsTemplateEngine());
        //post: process a form to create a new student

        post("/students", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String phoneNumber = req.queryParams("phoneNumber");
            String email = req.queryParams("email");
            Student  newStudent = new Student(name,phoneNumber,email);
            studentDao.add(newStudent);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //        //get: show an individual booking that is nested in a category

        get("/students/:student_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfStudentToFind = Integer.parseInt(req.params("student_id")); //pull id - must match route segment
            Student foundStudent = studentDao.findById(idOfStudentToFind); //use it to find task
            model.put("student", foundStudent); //add it to model for template to display
//            model.put("students", studentDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "student-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

    }
}
