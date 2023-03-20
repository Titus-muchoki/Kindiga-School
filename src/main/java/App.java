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
            List<Teacher> teachers = teacherDao.getAll();
            model.put("teachers", teachers);
            return new ModelAndView(model, "student-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/students", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            List<Teacher> allTeachers = teacherDao.getAll();
            model.put("teachers", allTeachers);
            String newName = req.queryParams("name");
            String newPhoneNumber = req.queryParams("phoneNumber");
            String newEmail = req.queryParams("email");
            int teacherId = Integer.parseInt(req.queryParams("teacherId"));
            Student newStudent = new Student(newName, newPhoneNumber, newEmail, teacherId);        //See what we did with the hard coded categoryId?
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
            model.put("students", studentDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "student-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

;
        get("/students/:student_id/units", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int studentId = Integer.parseInt(req.params("student_id"));
            Student studentToFind = studentDao.findById(studentId);
            model.put("student", studentToFind);
            List<Unit> allUnits = unitDao.getAllUnitsByStudents(Integer.parseInt(req.params("student_id")));
            unitDao.getAll();
            model.put("unit", allUnits);
            model.put("editUnit", true); //refresh list of links for navbar
            return new ModelAndView(model, "unit-form.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        post("/students/:studentId/units/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String math = req.queryParams("math");
            String english = req.queryParams("english");
            String kiswahili = req.queryParams("kiswahili");
            String science = req.queryParams("science");
            String socialStudy = req.queryParams("socialStudy");
            String cre = req.queryParams("cre");
            int studentId = Integer.parseInt(req.queryParams("studentId"));
            Unit newUnit = new Unit(math, english, kiswahili, science, socialStudy, cre, studentId);
            unitDao.add(newUnit);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        //get: show a form to update a student and units

        get("/units/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Unit> units = unitDao.getAllUnitsByStudents(Integer.parseInt(req.params("id")));
            unitDao.getAll();
            model.put("units", units);
            model.put("editUnit", true);
            return new ModelAndView(model, "unit-form.hbs");
        }, new HandlebarsTemplateEngine());


        //get: show a form to create a new student

        get("/units/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Unit> units = unitDao.getAll(); //refresh list of links for navbar
            model.put("units", units);
            return new ModelAndView(model, "unit-form.hbs"); //new layout
        }, new HandlebarsTemplateEngine());
        //post: process a form to create a new student

        post("/units", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
            String math = req.queryParams("math");
            String english = req.queryParams("english");
            String kiswahili = req.queryParams("kiswahili");
            String science = req.queryParams("science");
            String socialStudy = req.queryParams("socialStudy");
            String cre = req.queryParams("cre");
            int studentId = Integer.parseInt(req.queryParams("studentId"));
            Unit newUnit = new Unit(math, english, kiswahili, science, socialStudy, cre, studentId);
            unitDao.add(newUnit);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/units/:unit_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfUnitToFind = Integer.parseInt(req.params("unit_id")); //pull id - must match route segment
            List<Unit> foundUnit = unitDao.getAllUnitsByStudents(idOfUnitToFind);//use it to find task
            model.put("unit", foundUnit); //add it to model for template to display
            model.put("units", unitDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "unit-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());

        //get: show a form to update a student and units

        get("/units/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Unit> units = unitDao.getAllUnitsByStudents(Integer.parseInt(req.params("id")));
            unitDao.getAll();
            model.put("units", units);
            model.put("editUnit", true);
            return new ModelAndView(model, "unit-form.hbs");
        }, new HandlebarsTemplateEngine());

        get("/units", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Unit> units = unitDao.getAllUnitsByStudents(Integer.parseInt("id"));
            model.put("unit", units);
            model.put("editUnit", true);
            return new ModelAndView(model, "unit-form.hbs");
        }, new HandlebarsTemplateEngine());

        //        //get: show an individual unit that is nested in a student


        get("/teachers/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Teacher> teachers = teacherDao.getAll(); //refresh list of links for navbar
            model.put("teachers", teachers);
            return new ModelAndView(model, "teacher-form.hbs"); //new layout
        }, new HandlebarsTemplateEngine());
        //post: process a form to create a new teacher

        post("/teachers", (req, res) -> { //new
            Map<String, Object> model = new HashMap<>();
//            String name = req.queryParams("name");
            String comment = req.queryParams("comment");
//            int studentId = Integer.parseInt(req.queryParams("studentId"));
            Teacher newTeacher = new Teacher(comment);
            teacherDao.add(newTeacher);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());
        //        //get: show an individual unit that is nested in a student


        get("/teachers/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfTeacherToFind = Integer.parseInt(req.params("id")); //new
            Teacher foundTeacher = teacherDao.findById(idOfTeacherToFind);
            model.put("teacher", foundTeacher);
            List<Student> allStudentByTeachers = teacherDao.getAllStudentsByTeacher(idOfTeacherToFind);
            model.put("students", allStudentByTeachers);
            model.put("teachers", teacherDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "teacher-detail.hbs"); //new
        }, new HandlebarsTemplateEngine());
        //get: show a form to update a category

        get("/teachers/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("editTeacher", true);
            Teacher teacher = teacherDao.findById(Integer.parseInt(req.params("id")));
            model.put("teacher", teacher);
            model.put("teachers", teacherDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "teacher-form.hbs");
        }, new HandlebarsTemplateEngine());

        post("/teachers/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfOfficerToEdit = Integer.parseInt(req.params("id"));
            String newComment = req.queryParams("newTeacherComment");
            teacherDao.update(idOfOfficerToEdit, newComment);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/teachers/:teacher_id/students/:student_id/delete", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfStudentToDelete = Integer.parseInt(req.params("student_id"));
            studentDao.deleteById(idOfStudentToDelete);
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        get("/teachers/:teacher_id/students/:student_id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            int idOfStudentToFind = Integer.parseInt(req.params("student_id")); //pull id - must match route segment
            Student foundStudent = studentDao.findById(idOfStudentToFind); //use it to find task
            int idOfTacherToFind = Integer.parseInt(req.params("teacher_id"));
            Teacher foundTeacher = teacherDao.findById(idOfTacherToFind);
            model.put("teacher", foundTeacher);
            model.put("student", foundStudent); //add it to model for template to display
            model.put("teachers", teacherDao.getAll()); //refresh list of links for navbar
            return new ModelAndView(model, "student-detail.hbs"); //individual task page.
        }, new HandlebarsTemplateEngine());
        get("/students/:id/edit", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            List<Teacher> allTeachers = teacherDao.getAll();
            model.put("teachers", allTeachers);
            Student student = studentDao.findById(Integer.parseInt(req.params("id")));
            model.put("student", student);
            model.put("editStudent", true);
            return new ModelAndView(model, "student-form.hbs");
        }, new HandlebarsTemplateEngine());
        post("/students/:id", (req, res) -> { //URL to update task on POST route
            Map<String, Object> model = new HashMap<>();
            int studentToEditId = Integer.parseInt(req.params("id"));
            String newName = req.queryParams("name");
            String newPhoneNumber = req.queryParams("phoneNumber");
            String newEmail = req.queryParams("email");
            int teacherId = Integer.parseInt(req.queryParams("teacherId"));
            studentDao.update(studentToEditId, newName, newPhoneNumber, newEmail, teacherId);  // remember the hardcoded categoryId we placed? See what we've done to/with it?
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

    }
}