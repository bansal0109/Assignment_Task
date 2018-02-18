package smartcontrols.in.assignment_task.network.response;

/**
 * Created by Prateek on 2/14/2018.
 */

public class ApiResponse {

    private final int status;
    private final String response;
    private final String errorMessage;
    private final String api;

    /*
    * @status=> response code from server
    * @response==> response message from server
    * @errorMessage==> error message from server
    * @api==> api name which called
    *
    * */
    public ApiResponse(int status, String response, String errorMessage, String api) {
        this.status = status;
        this.response = response;
        this.errorMessage = errorMessage;
        this.api = api;
    }

    public int getStatus() {
        return status;
    }

    public String getResponse() {
        return response;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getApi() {
        return api;
    }

}
