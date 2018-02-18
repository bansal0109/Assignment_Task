package smartcontrols.in.assignment_task.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import smartcontrols.in.assignment_task.R;
import smartcontrols.in.assignment_task.model.DoctorDetailData;
import smartcontrols.in.assignment_task.model.DoctorResponseData;
import smartcontrols.in.assignment_task.network.listeners.ApiServiceListener;
import smartcontrols.in.assignment_task.network.request.ApiRequest;
import smartcontrols.in.assignment_task.network.request.DoctorRequestData;
import smartcontrols.in.assignment_task.network.response.ApiResponse;
import smartcontrols.in.assignment_task.network.services.ApiServices;
import smartcontrols.in.assignment_task.network.services.ApiServicesImpl;
import smartcontrols.in.assignment_task.network.services.Encoding;
import smartcontrols.in.assignment_task.network.services.Modules;
import smartcontrols.in.assignment_task.network.services.RequestType;
import smartcontrols.in.assignment_task.network.services.Services;
import smartcontrols.in.assignment_task.util.Utils;
import smartcontrols.in.assignment_task.view.adapter.Adapter_DoctorList;

public class Activity_DoctorsList extends AppCompatActivity implements ApiServiceListener {

    /*
    * DOCTOR_LOCATION constant for api request
    * USER_LOCATION constant for api request
    * SKIP constant for api request
    * LIMIT constant for api request
    * USER_KEY constant for api request
    * */
    public static final String DOCTOR_LOCATION = "37.773,-122.413,100";
    public static final String USER_LOCATION = "37.773,-122.413";
    public static final String SKIP = "0";
    public static final String LIMIT = "10";
    public static final String USER_KEY = "dc3dc63f4e5ccb9f9714be78d0e7216b";
    private ExpandableListView lvDoctorList;
    private TextView tvStatus;
    private ProgressBar progressBar;
    private FrameLayout frameLayout;
    private Toolbar toolbar;
    private List<DoctorDetailData> doctorsList = null;
    private HashMap<String, List<DoctorDetailData>> doctorListExpandview = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.left_slide_in, R.anim.left_slide_out);
        setContentView(R.layout.activity_doctorlist);
        initComponentView();

    }

    /*
    * Method for initilization of components for activity
    * */
    private void initComponentView() {
        lvDoctorList = (ExpandableListView) findViewById(R.id.lvDoctorList);
        tvStatus = (TextView) findViewById(R.id.tvStatus);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_arrow));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getResources().getString(R.string.doctorList));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        lvDoctorList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long l) {
                expandableListView.collapseGroup(groupPosition);

                /*
                * Call the profile detail activity
                * */
                Intent intent = new Intent(Activity_DoctorsList.this, Activity_ProfileDetail.class);
                intent.putExtra(Utils.keyDoctroProfile, new Gson().toJson(doctorsList.get(groupPosition)));
                startActivity(intent);

                return false;
            }
        });

        /*
        * Check the Network Connection for Api Call.
        * */
        if (Utils.isConnectingToInternet(this)) {
            checkApiCall();
        } else {
            tvStatus.setText(getResources().getString(R.string.no_internet));
            hideVisibility();
        }


    }

    /*
    * Callback method on failure in network operation
    **/
    @Override
    public void onCancelled() {
        tvStatus.setText(getResources().getString(R.string.serverCallCancelled));
        hideVisibility();
    }

    /*
    *  Callback method before the netwrok operation begins.
   **/
    @Override
    public void onPreExecute() {
        frameLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
    }


    /**
     * Callback method on completion of operation
     *
     * @param apiResponse of the completed operation
     */
    @Override
    public void onPostExecute(ApiResponse apiResponse) {
        Gson gson = new Gson();
        if (apiResponse.getStatus() == Utils.CODE_SUCCESS) {
            showVisibility();
            DoctorResponseData data = gson.fromJson(apiResponse.getResponse(), DoctorResponseData.class);
            // Assign Value in doctorList from the api response
            doctorsList = data.getData();
            doctorListExpandview = new HashMap<String, List<DoctorDetailData>>();
            List<DoctorDetailData> doctorDetailExpandviewList;
            for (int i = 0; i < doctorsList.size(); i++) {
                doctorDetailExpandviewList = new ArrayList<DoctorDetailData>();
                doctorDetailExpandviewList.add(doctorsList.get(i));
                doctorListExpandview.put(doctorsList.get(i).getUid(), doctorDetailExpandviewList);
            }
            Adapter_DoctorList listAdapter = new Adapter_DoctorList(this, doctorsList, doctorListExpandview);
            // setting list adapter
            lvDoctorList.setAdapter(listAdapter);
        } else {
            tvStatus.setText(apiResponse.getResponse());
            hideVisibility();
        }
    }

    public void checkApiCall() {

        //Creating the request from the client/setting the header
        ApiRequest request = new ApiRequest();

        //Creating the instance of the hinext service obj to call the api
        ApiServices apiServices = new ApiServicesImpl(Activity_DoctorsList.this);

        /*
        * Request for Doctor Search
        *
         * */
        DoctorRequestData doctorRequestData = new DoctorRequestData(DOCTOR_LOCATION, USER_LOCATION, SKIP, LIMIT, USER_KEY);
        request.setDoctorRequestData(doctorRequestData);
        try {
            apiServices.apiRequest(Modules.DOCTOR, Services.DOCTOR_SEARCH, RequestType.GET, Encoding.JSON, request);
        } catch (Exception me) {
            tvStatus.setText(me.getMessage());
            hideVisibility();
        }
    }

    /*
    * Method for Set the visibility of the components view
    * */
    public void showVisibility() {
        frameLayout.setVisibility(View.VISIBLE);
        lvDoctorList.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        tvStatus.setVisibility(View.GONE);
    }

    /*
    * Method for Set the visibility of the components view
    * */
    public void hideVisibility() {
        frameLayout.setVisibility(View.GONE);
        tvStatus.setVisibility(View.VISIBLE);

    }


}
