package smartcontrols.in.assignment_task.network.doctor_api;

import com.google.gson.Gson;

import smartcontrols.in.assignment_task.network.listeners.ApiServiceListener;
import smartcontrols.in.assignment_task.network.request.ApiRequest;
import smartcontrols.in.assignment_task.network.request.DoctorRequestData;
import smartcontrols.in.assignment_task.network.response.ApiResponse;
import smartcontrols.in.assignment_task.network.services.Encoding;
import smartcontrols.in.assignment_task.network.services.Modules;
import smartcontrols.in.assignment_task.network.services.RequestType;
import smartcontrols.in.assignment_task.network.services.Services;
import smartcontrols.in.assignment_task.network.task.ApiCommunication;
import smartcontrols.in.assignment_task.util.Utils;

/**
 * Created by Prateek on 2/14/2018.
 */

public class DoctorApiImpl implements DoctorApi, ApiServiceListener {


    final private Modules modules;
    final private Services services;
    final private RequestType requestType;
    final private Encoding encoding;
    final private ApiRequest apiRequest;
    private final ApiServiceListener apiServicesListener;


    /**
     * Construtor for initilizing the final values.
     *
     * @param modules             for the operation
     * @param services            for the operation
     * @param requestType         for the api
     * @param encoding            for the response type
     * @param apiRequest          for the request body for the api
     * @param apiServicesListener for handling network operations
     */
    public DoctorApiImpl(Modules modules,
                         Services services,
                         RequestType requestType,
                         Encoding encoding,
                         ApiRequest apiRequest,
                         ApiServiceListener apiServicesListener) {

        this.modules = modules;
        this.services = services;
        this.requestType = requestType;
        this.encoding = encoding;
        this.apiRequest = apiRequest;
        this.apiServicesListener = apiServicesListener;
    }


    // Oerride this method for the implementation and throws exception to client.
    @Override
    public void getDoctors() throws Exception {
        if (requestType.equals(RequestType.GET)) {
            DoctorRequestData doctorRequestData = apiRequest.getDoctorRequestData();
            if (doctorRequestData != null) {
                Gson gson = new Gson();
                apiRequest.setRequestBody(gson.toJson(doctorRequestData));
                // call service for execution of api.
                ApiCommunication apiCommunication = new ApiCommunication(modules, services, requestType, encoding, apiRequest, this);
                apiCommunication.execute();
            } else {
                // throw exception for missing request paramter
                throw new Exception(Utils.errorRequestBody);
            }
        } else {
            // throw exception for missing request body
            throw new Exception(Utils.missingRequestMethod);
        }
    }


    /*
    * Callback method implementation to the client when api called on failure in network operation
    **/
    @Override
    public void onCancelled() {
        apiServicesListener.onCancelled();
    }

    /*
    *  Callback method implementation to the client when api called before the netwrok operation begins.
   **/
    @Override
    public void onPreExecute() {
        apiServicesListener.onPreExecute();
    }


    /**
     * Callback method implementation to the client when api called on completion of operation
     *
     * @param apiResponse of the completed operation
     */
    @Override
    public void onPostExecute(ApiResponse apiResponse) {
        apiServicesListener.onPostExecute(apiResponse);
    }
}
