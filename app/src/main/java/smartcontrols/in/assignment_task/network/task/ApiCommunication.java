package smartcontrols.in.assignment_task.network.task;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;


import smartcontrols.in.assignment_task.network.listeners.ApiServiceListener;
import smartcontrols.in.assignment_task.network.request.ApiRequest;
import smartcontrols.in.assignment_task.network.response.ApiResponse;
import smartcontrols.in.assignment_task.network.services.Encoding;
import smartcontrols.in.assignment_task.network.services.Modules;
import smartcontrols.in.assignment_task.network.services.RequestType;
import smartcontrols.in.assignment_task.network.services.Services;
import smartcontrols.in.assignment_task.util.Utils;

/**
 * Created by Prateek on 2/14/2018.
 */

public class ApiCommunication extends AsyncTask<Void, Void, ApiResponse> {

    final private Modules modules;
    final private Services services;
    final private RequestType requestType;
    final private Encoding encoding;
    final private ApiRequest apiRequest;
    private final ApiServiceListener apiServiceListener;

    /**
     * Construtor for initilizing the final values.
     *
     * @param modules             for the operation
     * @param services            for the operation
     * @param requestType         for the api
     * @param encoding            for the response type
     * @param apiRequest          for the request body for the api
     * @param apiServiceListener for handling network operations
     */

    public ApiCommunication(Modules modules,
                            Services services,
                            RequestType requestType,
                            Encoding encoding,
                            ApiRequest apiRequest,
                            ApiServiceListener apiServiceListener) {

        this.modules = modules;
        this.services = services;
        this.requestType = requestType;
        this.encoding = encoding;
        this.apiRequest = apiRequest;
        this.apiServiceListener = apiServiceListener;
    }

    /**
     * Overridden onPreExecute method
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        apiServiceListener.onPreExecute();
    }

    /*
    * Overridden onCancelled Method
    * */
    @Override
    protected void onCancelled() {
        super.onCancelled();
        apiServiceListener.onCancelled();
    }


    @Override
    protected ApiResponse doInBackground(Void... voids) {
        ApiResponse apiResponse;
        int responseCode = 0;
        String errorMessage = "";
        String responseMessage = "";

        String url = Utils.getUrl(modules, services, apiRequest.getRequestBody()); //Method to get the URL here based on the module and service
        String methodType = requestType.toString();
        if (methodType.equals("") || modules.equals("") || services.equals("")) {
            apiResponse = new ApiResponse(Utils.SERVICE_NOTFOUND, Utils.errorResponseFail, Utils.errorResponseEmptyModule, null);
            return apiResponse;
        } else {
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;
            try {
                Log.e("@Flow", "URL " + url);
                urlConnection = (HttpURLConnection) new URL(url).openConnection();
                urlConnection.setConnectTimeout(10000);
                urlConnection.setRequestMethod(methodType);
                if (methodType.equals(RequestType.POST.toString())) {
                    urlConnection.setRequestProperty("Content-Type", "application/json");
                    Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream(), "UTF-8"));
                    if (apiRequest.getRequestBody() == null) {
                        apiResponse = new ApiResponse(Utils.SERVICE_NOTFOUND, Utils.errorResponseFail, Utils.errorRequestBody, null);
                        return apiResponse;
                    } else {
                        writer.write(apiRequest.getRequestBody());
                    }
                    writer.close();
                } else {
                    urlConnection.setUseCaches(true);
                    urlConnection.connect();
                }
                try {
                    responseCode = urlConnection.getResponseCode();
                    if (responseCode == HttpsURLConnection.HTTP_OK) {
                        // Read the input stream into ic_category_leftview String
                        InputStream inputStream = urlConnection.getInputStream();
                        StringBuffer buffer = new StringBuffer();
                        if (inputStream == null) {
                            // Nothing to do.
                            responseMessage = Utils.serverResponseSuccess;
                        }
                        reader = new BufferedReader(new InputStreamReader(inputStream));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            // Since it's JSON, adding ic_category_leftview newline isn't necessary (it won't affect parsing)
                            // But it does make debugging ic_category_leftview *lot* easier if you print out the completed
                            // buffer for debugging.
                            buffer.append(line + "\n");
                        }
                        if (buffer.length() == 0) {
                            // Stream was empty.  No point in parsing.
                            responseMessage = Utils.serverResponseSuccess;
                        } else {
                            responseMessage = buffer.toString();
                            errorMessage = null;
                        }
                    } else {
                        responseMessage = Utils.errorResponseFail;
                        responseCode = urlConnection.getResponseCode();
                        errorMessage = Utils.getErrorResponse(urlConnection.getResponseCode());
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    responseMessage = Utils.errorResponseFail;
                    responseCode = urlConnection.getResponseCode();
                    errorMessage = Utils.getErrorResponse(urlConnection.getResponseCode());
                }

            } catch (Exception e) {
                responseMessage = Utils.errorResponseFail;
                errorMessage = Utils.getErrorResponse(responseCode);
            }
            // Get the data from the JSON create HiAccountResponse based on Status code, error message and API name etc
            apiResponse = new ApiResponse(responseCode, responseMessage, errorMessage, url);
            return apiResponse;
        }
    }

    /**
     * Overridden onPostExecute method
     */
    @Override
    protected void onPostExecute(ApiResponse apiResponse) {
        super.onPostExecute(apiResponse);
        apiServiceListener.onPostExecute(apiResponse);
    }
}
