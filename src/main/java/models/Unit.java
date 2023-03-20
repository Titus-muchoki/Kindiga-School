package models;

import java.util.Objects;

public class Unit {
    private String math;
    private String english;
    private String kiswahili;
    private String science;
    private String socialStudy;
    private String cre;
    private  int id;
    private int studentId;

    public Unit(String math, String english, String kiswahili, String science, String socialStudy, String cre, int studentId) {
        this.math = math;
        this.english = english;
        this.kiswahili = kiswahili;
        this.science = science;
        this.socialStudy = socialStudy;
        this.cre = cre;
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Unit)) return false;
        Unit unit = (Unit) o;
        return id == unit.id && studentId == unit.studentId && Objects.equals(math, unit.math) && Objects.equals(english, unit.english) && Objects.equals(kiswahili, unit.kiswahili) && Objects.equals(science, unit.science) && Objects.equals(socialStudy, unit.socialStudy) && Objects.equals(cre, unit.cre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(math, english, kiswahili, science, socialStudy, cre, id, studentId);
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getKiswahili() {
        return kiswahili;
    }

    public void setKiswahili(String kiswahili) {
        this.kiswahili = kiswahili;
    }

    public String getScience() {
        return science;
    }

    public void setScience(String science) {
        this.science = science;
    }

    public String getSocialStudy() {
        return socialStudy;
    }

    public void setSocialStudy(String socialStudy) {
        this.socialStudy = socialStudy;
    }

    public String getCre() {
        return cre;
    }

    public void setCre(String cre) {
        this.cre = cre;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
