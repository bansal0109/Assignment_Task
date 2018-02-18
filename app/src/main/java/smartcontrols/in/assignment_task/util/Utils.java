package smartcontrols.in.assignment_task.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import smartcontrols.in.assignment_task.network.request.DoctorRequestData;
import smartcontrols.in.assignment_task.network.services.Modules;
import smartcontrols.in.assignment_task.network.services.Services;

/**
 * Created by prateek on 2/14/18.
 */

public class Utils {

    // url links for service call
    public static final String BASE_URL = "https://api.betterdoctor.com";

    // method for getting server url on the basis od module and services selection by client
    public static String getUrl(Modules modules, Services services, String requestBody) {
        String modulePath = "";
        String servicePath = "";
        Gson gson = new Gson();
        switch (modules) {
            case DOCTOR:
                modulePath = "/2016-03-01";
                break;
        }
        switch (services) {
            case DOCTOR_SEARCH:
                DoctorRequestData requestData = gson.fromJson(requestBody, DoctorRequestData.class);
                try {
                    servicePath = "/doctors?location=" + URLEncoder.encode(requestData.getDoctorLocation(), "UTF-8") + "&user_location=" + URLEncoder.encode(requestData.getUserLocation(), "UTF-8") + "&skip=" + URLEncoder.encode(requestData.getSkip(), "UTF-8") + "&limit=" + URLEncoder.encode(requestData.getLimit(), "UTF-8") + "&user_key=" + URLEncoder.encode(requestData.getUserKey(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
        }
        String finalUrl = BASE_URL + modulePath + servicePath;
        return finalUrl;
    }

    // method for getting error response message from calling request
    public static String getErrorResponse(int responseCode) {
        String errorMessage = "";

        switch (responseCode) {
            case 0:
                errorMessage = "You are making wrong request. Please check it once again.";
                break;
            case 400:
                errorMessage = "Bad Request.";
                break;

            case 401:
                errorMessage = "Unauthorized";
                break;

            case 404:
                errorMessage = "Request Not Found";
                break;

            case 405:
                errorMessage = "Method Not Allowed.";
                break;

            case 406:
                errorMessage = "Header    Not Acceptable";
                break;


            case 408:
                errorMessage = "Request Timeout";
                break;


            case 415:
                errorMessage = "Unsupported Media Type";
                break;

            case 500:
                errorMessage = "Internal Server Error";
                break;

            case 501:
                errorMessage = "Not Implemented";
                break;

            case 502:
                errorMessage = "Bad Gateway";
                break;

            case 503:
                errorMessage = "Service Unavailable";
                break;

            default:
                errorMessage = "Some thing went wrong. Please try again.";
                break;

        }

        return errorMessage;
    }


    // Response Code from server
    public static final int CODE_SUCCESS = 200;
    public static final int SERVICE_NOTFOUND = 400;

    public static final String keyDoctroProfile = "doctorProfile";

    public static final String serverResponseSuccess = "Success";
    public static final String errorResponseFail = "fail";
    public static final String errorRequestBody = "Request body can not be empty. Please check the documetation for further refernce.";
    public static final String missingRequestMethod = "Method type not supported.";
    public static final String errorResponseEmptyModule = "Please enter valid Module";
    public static final String errorResponseEmptyServices = "Please enter valid Module Services";


    // method for check network status
    public static boolean isConnectingToInternet(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                return true;
            }

        }
        return false;
    }


}
