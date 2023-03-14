package models;

import java.util.Objects;

public class Unit {
    private String math;
    private String english;
    private String kiswahili;
    private String science;
    private String socialStudy;
    private String cre;
    private int studentId;
    private  int id;

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
        return studentId == unit.studentId && id == unit.id && Objects.equals(math, unit.math) && Objects.equals(english, unit.english) && Objects.equals(kiswahili, unit.kiswahili) && Objects.equals(science, unit.science) && Objects.equals(socialStudy, unit.socialStudy) && Objects.equals(cre, unit.cre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(math, english, kiswahili, science, socialStudy, cre, studentId, id);
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }
}
