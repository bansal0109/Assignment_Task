package smartcontrols.in.assignment_task.network.services;

import smartcontrols.in.assignment_task.network.doctor_api.DoctorApi;
import smartcontrols.in.assignment_task.network.doctor_api.DoctorApiImpl;
import smartcontrols.in.assignment_task.network.exception.ModuleException;
import smartcontrols.in.assignment_task.network.exception.ServiceException;
import smartcontrols.in.assignment_task.network.listeners.APIListener;
import smartcontrols.in.assignment_task.network.listeners.ApiServiceListener;
import smartcontrols.in.assignment_task.network.request.ApiRequest;
import smartcontrols.in.assignment_task.network.response.ApiResponse;
import smartcontrols.in.assignment_task.util.Utils;

/**
 * Created by Prateek on 2/14/2018.
 */

public class ApiServicesImpl implements ApiServices, ApiServiceListener {

    private final ApiServiceListener apiServiceListener;

    public ApiServicesImpl(ApiServiceListener apiServiceListener) {
        this.apiServiceListener = apiServiceListener;
    }

    /**
     * @param module      for the operation
     * @param service     for the operation
     * @param requestType for the api
     * @param encoding    for the response type
     * @param apiRequest  for the request body for the api
     **/
    @Override
    public void apiRequest(Modules module,
                           Services service,
                           RequestType requestType,
                           Encoding encoding,
                           ApiRequest apiRequest) throws Exception {
        {
            switch (module) {
                case DOCTOR: {
                    DoctorApi doctorsApi = new DoctorApiImpl(module, service, requestType, encoding, apiRequest, this);
                    //based on the service different services from the DoctoApi needs to get called
                    switch (service) {
                        case DOCTOR_SEARCH:
                            doctorsApi.getDoctors();
                            break;

                        default:
                            throw new ServiceException(Utils.errorResponseEmptyServices);
                    }
                }
                break;
                default:
                    throw new ModuleException(Utils.errorResponseEmptyModule);
            }
        }
    }

    @Override
    public void onCancelled() {
        apiServiceListener.onCancelled();
    }

    @Override
    public void onPreExecute() {
        apiServiceListener.onPreExecute();
    }

    @Override
    public void onPostExecute(ApiResponse apiResponse) {
        apiServiceListener.onPostExecute(apiResponse);
    }
}
