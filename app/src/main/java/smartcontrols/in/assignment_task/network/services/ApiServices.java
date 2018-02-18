package smartcontrols.in.assignment_task.network.services;

import smartcontrols.in.assignment_task.network.request.ApiRequest;

/**
 * Created by Prateek on 2/14/2018.
 */

public interface ApiServices {

    /**
     * @param module      for the operation
     * @param service     for the operation
     * @param requestType for the api
     * @param encoding    for the response type
     * @param apiRequest  for the request body for the api
     **/
    public void apiRequest(Modules module,
                           Services service,
                           RequestType requestType,
                           Encoding encoding,
                           ApiRequest apiRequest
    ) throws Exception;
}
