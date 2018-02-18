package smartcontrols.in.assignment_task.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Prateek on 2/18/2018.
 */

public class Education implements Serializable {
    @SerializedName("school")
    @Expose
    public String school;

    @SerializedName("graduation_year")
    @Expose
    public String graduationYear;

    @SerializedName("degree")
    @Expose
    public String degree;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(String graduationYear) {
        this.graduationYear = graduationYear;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
