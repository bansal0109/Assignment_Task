package smartcontrols.in.assignment_task.network.request;

/**
 * Created by Prateek on 2/15/2018.
 */

public class DoctorRequestData {

    private String doctorLocation;
    private String userLocation;
    private String skip;
    private String limit;
    private String userKey;


    public DoctorRequestData(String doctorLocation, String userLocation, String skip, String limit, String userKey) {
        this.doctorLocation = doctorLocation;
        this.userLocation = userLocation;
        this.skip = skip;
        this.limit = limit;
        this.userKey = userKey;
    }

    public String getDoctorLocation() {
        return doctorLocation;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public String getSkip() {
        return skip;
    }

    public String getLimit() {
        return limit;
    }

    public String getUserKey() {
        return userKey;
    }
}
