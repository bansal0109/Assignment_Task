package smartcontrols.in.assignment_task.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import smartcontrols.in.assignment_task.R;
import smartcontrols.in.assignment_task.model.DoctorDetailData;
import smartcontrols.in.assignment_task.model.Education;
import smartcontrols.in.assignment_task.model.Practice;
import smartcontrols.in.assignment_task.model.Specialty;
import smartcontrols.in.assignment_task.util.Utils;
import smartcontrols.in.assignment_task.view.adapter.Adapter_RecyclerView_ProfileDetail;

/**
 * Created by Prateek on 2/18/2018.
 */

public class Activity_ProfileDetail extends AppCompatActivity {

    private Toolbar toolbar;
    private ImageView imageViewProfile;
    private TextView tvName, tvGender, tvLanguage;
    private RecyclerView recyclerViewEducation, recyclerViewSpecilization, recyclerViewPractices;
    private LinearLayout llName, llGender, llLanguage;
    private CardView cvEducaation, cvSpecilization, cvPractices;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.left_slide_in, R.anim.left_slide_out);
        setContentView(R.layout.activity_profiledetail);
        initComponentView();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);
    }

    /*
        * Method for initilization of components for activity
        * */
    private void initComponentView() {

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_back_arrow));
        toolbar.setTitleTextColor(getResources().getColor(R.color.colorWhite));
        toolbar.setTitle(getResources().getString(R.string.profileDetail));
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.right_slide_in, R.anim.right_slide_out);

            }
        });


        imageViewProfile = (ImageView) findViewById(R.id.imgviewProfile);
        tvName = (TextView) findViewById(R.id.tvName);
        tvGender = (TextView) findViewById(R.id.tvGender);
        tvLanguage = (TextView) findViewById(R.id.tvLanguage);
        recyclerViewEducation = (RecyclerView) findViewById(R.id.recyclerViewEducation);
        recyclerViewSpecilization = (RecyclerView) findViewById(R.id.recyclerViewSpecilization);
        recyclerViewPractices = (RecyclerView) findViewById(R.id.recyclerViewPractices);
        llName = (LinearLayout) findViewById(R.id.llName);
        llGender = (LinearLayout) findViewById(R.id.llGender);
        llLanguage = (LinearLayout) findViewById(R.id.llLanguage);
        cvEducaation = (CardView) findViewById(R.id.cvEducation);
        cvSpecilization = (CardView) findViewById(R.id.cvSpecilization);
        cvPractices = (CardView) findViewById(R.id.cvPractices);


        Intent intent = getIntent();
        if (intent != null) {
            String doctorData = intent.getStringExtra(Utils.keyDoctroProfile);
            if (doctorData != null) {
                Gson gson = new Gson();
                DoctorDetailData doctorDetail = gson.fromJson(doctorData, DoctorDetailData.class);
                if (doctorDetail != null) {
                    if (doctorDetail.getProfile() != null) {
                        Picasso.with(this)
                                .load(doctorDetail.getProfile().getImageUrl())
                                .placeholder(R.drawable.ic_progress)   // optional
                                .error(R.drawable.ic_noimage).fit()
                                .into(imageViewProfile);

                        String fName = "", mName = "", lName = "";
                        if (doctorDetail.getProfile().getFirstName() != null)
                            fName = doctorDetail.getProfile().getFirstName();
                        if (doctorDetail.getProfile().getMiddleName() != null)
                            mName = doctorDetail.getProfile().getMiddleName();
                        if (doctorDetail.getProfile().getLastName() != null)
                            lName = doctorDetail.getProfile().getLastName();
                        tvName.setText(fName + " " + mName + " " + lName);

                        if (doctorDetail.getProfile().getGender() != null)
                            tvGender.setText(doctorDetail.getProfile().getGender());
                        else llGender.setVisibility(View.GONE);

                        if (doctorDetail.getProfile().getLanguages() != null
                                && doctorDetail.getProfile().getLanguages().size() > 0) {
                            if (doctorDetail.getProfile().getLanguages().get(0).getName() != null)
                                tvLanguage.setText(doctorDetail.getProfile().getLanguages().get(0).getName());
                            else llLanguage.setVisibility(View.GONE);

                        } else llLanguage.setVisibility(View.GONE);
                    }


                    if (doctorDetail.getEducations() != null && doctorDetail.getEducations().size() > 0) {
                        List<Object> objectList = new ArrayList<Object>();
                        objectList.addAll(doctorDetail.getEducations());
                        Adapter_RecyclerView_ProfileDetail mAdapter = new Adapter_RecyclerView_ProfileDetail(objectList);
                        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
                        recyclerViewEducation.setLayoutManager(mLayoutManager);
                        recyclerViewEducation.setItemAnimator(new DefaultItemAnimator());
                        recyclerViewEducation.setAdapter(mAdapter);
                    } else {
                        cvEducaation.setVisibility(View.GONE);
                    }

                    if (doctorDetail.getSpecialties() != null && doctorDetail.getSpecialties().size() > 0) {
                        List<Object> objectList = new ArrayList<Object>();
                        objectList.addAll(doctorDetail.getSpecialties());
                        Adapter_RecyclerView_ProfileDetail mAdapter = new Adapter_RecyclerView_ProfileDetail(objectList);
                        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
                        recyclerViewSpecilization.setLayoutManager(mLayoutManager);
                        recyclerViewSpecilization.setItemAnimator(new DefaultItemAnimator());
                        recyclerViewSpecilization.setAdapter(mAdapter);
                    } else {
                        cvSpecilization.setVisibility(View.GONE);
                    }

                    if (doctorDetail.getPractices() != null && doctorDetail.getPractices().size() > 0) {
                        List<Object> objectList = new ArrayList<Object>();
                        objectList.addAll(doctorDetail.getPractices());
                        Adapter_RecyclerView_ProfileDetail mAdapter = new Adapter_RecyclerView_ProfileDetail(objectList);
                        GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
                        recyclerViewPractices.setLayoutManager(mLayoutManager);
                        recyclerViewPractices.setItemAnimator(new DefaultItemAnimator());
                        recyclerViewPractices.setAdapter(mAdapter);
                    } else {
                        cvPractices.setVisibility(View.GONE);
                    }
                }

            }
        }
    }
}
