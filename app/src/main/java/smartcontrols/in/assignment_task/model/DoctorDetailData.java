
package smartcontrols.in.assignment_task.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DoctorDetailData implements Serializable {

    @SerializedName("practices")
    @Expose
    public List<Practice> practices = null;
    @SerializedName("educations")
    @Expose
    public List<Education> educations = null;
    @SerializedName("profile")
    @Expose
    public Profile profile;
    @SerializedName("ratings")
    @Expose
    public List<Object> ratings = null;
    @SerializedName("insurances")
    @Expose
    public List<Insurance> insurances = null;
    @SerializedName("specialties")
    @Expose
    public List<Specialty> specialties = null;
    @SerializedName("licenses")
    @Expose
    public List<License> licenses = null;
    @SerializedName("uid")
    @Expose
    public String uid;
    @SerializedName("npi")
    @Expose
    public String npi;

    public List<Practice> getPractices() {
        return practices;
    }

    public void setPractices(List<Practice> practices) {
        this.practices = practices;
    }

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Object> getRatings() {
        return ratings;
    }

    public void setRatings(List<Object> ratings) {
        this.ratings = ratings;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public List<License> getLicenses() {
        return licenses;
    }

    public void setLicenses(List<License> licenses) {
        this.licenses = licenses;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getNpi() {
        return npi;
    }

    public void setNpi(String npi) {
        this.npi = npi;
    }

    @Override
    public String toString() {
        return profile.getFirstName();
    }
}
