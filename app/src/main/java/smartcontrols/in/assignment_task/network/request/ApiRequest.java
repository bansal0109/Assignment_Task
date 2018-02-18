package smartcontrols.in.assignment_task.network.request;

/**
 * Created by Prateek on 2/14/2018.
 */

public class ApiRequest {

    private String requestBody;

    private DoctorRequestData doctorRequestData;

    public DoctorRequestData getDoctorRequestData() {
        return doctorRequestData;
    }

    public void setDoctorRequestData(DoctorRequestData doctorRequestData) {
        this.doctorRequestData = doctorRequestData;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
