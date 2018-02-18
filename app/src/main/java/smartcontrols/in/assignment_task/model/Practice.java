
package smartcontrols.in.assignment_task.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Practice implements Serializable {

    @SerializedName("location_slug")
    @Expose
    public String locationSlug;
    @SerializedName("within_search_area")
    @Expose
    public boolean withinSearchArea;
    @SerializedName("distance")
    @Expose
    public double distance;
    @SerializedName("lat")
    @Expose
    public double lat;
    @SerializedName("lon")
    @Expose
    public double lon;
    @SerializedName("uid")
    @Expose
    public String uid;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("website")
    @Expose
    public String website;
    @SerializedName("accepts_new_patients")
    @Expose
    public boolean acceptsNewPatients;
    @SerializedName("insurance_uids")
    @Expose
    public List<String> insuranceUids = null;
    @SerializedName("visit_address")
    @Expose
    public VisitAddress visitAddress;
    @SerializedName("office_hours")
    @Expose
    public List<Object> officeHours = null;
    @SerializedName("phones")
    @Expose
    public List<Phone> phones = null;
    @SerializedName("languages")
    @Expose
    public List<Language> languages = null;
    @SerializedName("media")
    @Expose
    public List<Medium> media = null;

    public String getLocationSlug() {
        return locationSlug;
    }

    public void setLocationSlug(String locationSlug) {
        this.locationSlug = locationSlug;
    }

    public boolean isWithinSearchArea() {
        return withinSearchArea;
    }

    public void setWithinSearchArea(boolean withinSearchArea) {
        this.withinSearchArea = withinSearchArea;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public boolean isAcceptsNewPatients() {
        return acceptsNewPatients;
    }

    public void setAcceptsNewPatients(boolean acceptsNewPatients) {
        this.acceptsNewPatients = acceptsNewPatients;
    }

    public List<String> getInsuranceUids() {
        return insuranceUids;
    }

    public void setInsuranceUids(List<String> insuranceUids) {
        this.insuranceUids = insuranceUids;
    }

    public VisitAddress getVisitAddress() {
        return visitAddress;
    }

    public void setVisitAddress(VisitAddress visitAddress) {
        this.visitAddress = visitAddress;
    }

    public List<Object> getOfficeHours() {
        return officeHours;
    }

    public void setOfficeHours(List<Object> officeHours) {
        this.officeHours = officeHours;
    }

    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Medium> getMedia() {
        return media;
    }

    public void setMedia(List<Medium> media) {
        this.media = media;
    }
}
