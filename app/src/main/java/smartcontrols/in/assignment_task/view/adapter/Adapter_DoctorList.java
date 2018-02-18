package smartcontrols.in.assignment_task.view.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;

import smartcontrols.in.assignment_task.R;
import smartcontrols.in.assignment_task.model.DoctorDetailData;
import smartcontrols.in.assignment_task.model.DoctorResponseData;

public class Adapter_DoctorList extends BaseExpandableListAdapter {

    private Context _context;
    private List<DoctorDetailData> doctorList; //
    private HashMap<String, List<DoctorDetailData>> doctorsHashMap;//

    /*
    * Consructor for initilization of values
    * @param context calling from
    *  @param doctorList Doctor collection to add in headerview
    *   @param doctorsHashMap Doctor collection with doctors id key collection
    * */
    public Adapter_DoctorList(Context context, List<DoctorDetailData> doctorList, HashMap<String, List<DoctorDetailData>> doctorsHashMap) {
        this._context = context;
        this.doctorList = doctorList;
        this.doctorsHashMap = doctorsHashMap;
    }

    /*
    * Method for getting Child view Object
    * @param groupPostion position which child object to get
    * @param childPosition postion for child value
    *
    * */
    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.doctorsHashMap.get(this.doctorList.get(groupPosition).getUid()).get(childPosititon);
    }


    /*
    * Method for get child position
    * @param groupPostion position which child postion to get
    * @param childPosition postion for child value
    *
    * */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }


    /*
    * Method for Child view
    * @param groupPostion selected Group Position
    * @param childPosition selected Child Position
    * @param isLastChild return true if selection position is last
    * @param convertView view for inflating the another view.
     *@param parent calling view.
    * */
    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        final DoctorDetailData doctorDetail = (DoctorDetailData) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_expandview_doctorlist, null);
        }
        TextView tvProfileDescription = (TextView) convertView.findViewById(R.id.tvProfileDescription);
        if (doctorDetail != null) {
            if (doctorDetail.getProfile() != null) {
                if (doctorDetail.getProfile().getBio() != null)
                    tvProfileDescription.setText(doctorDetail.getProfile().getBio());
            }
        }

        return convertView;
    }


    /*
    * Method for getting Children view Count
    * @param groupPostion for counting the child postion on it.
    *
    * */
    @Override
    public int getChildrenCount(int groupPosition) {
        return this.doctorsHashMap.get(this.doctorList.get(groupPosition).getUid()).size();
    }


    /*
    * Method for getting Group view Object
    * @param groupPostion selected position for getting object.
    *
    * */
    @Override
    public Object getGroup(int groupPosition) {
        return this.doctorList.get(groupPosition);
    }


    /*
    * Method for coutning the group view
    *
    * */
    @Override
    public int getGroupCount() {
        return this.doctorList.size();
    }


    /*
    * Method for get Group position ID
    * @param groupPostion position which group postion id
    *
    * */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }


    /*
    * Method for Group view
    * @param groupPostion selected Group Position
    * @param isExpanded return true if group view is expand.
    * @param convertView view for inflating the another view.
     *@param parent calling view.
    * */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        DoctorDetailData doctorDetail = (DoctorDetailData) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.row_itemview_doctorlist, null);
        }

        TextView tvDoctorName = (TextView) convertView.findViewById(R.id.tvDoctorName);
        TextView tvSpecilization = (TextView) convertView.findViewById(R.id.tvSpecilization);
        TextView tvAddress = (TextView) convertView.findViewById(R.id.tvAddress);
        TextView tvMore = (TextView) convertView.findViewById(R.id.tvMore);
        ImageView imgviewProfile = (ImageView) convertView.findViewById(R.id.imgviewProfile);

        if (doctorDetail != null) {
            if (doctorDetail.getProfile() != null) {
                String fName = "", mName = "", lName = "";
                if (doctorDetail.getProfile().getFirstName() != null)
                    fName = doctorDetail.getProfile().getFirstName();
                if (doctorDetail.getProfile().getMiddleName() != null)
                    mName = doctorDetail.getProfile().getMiddleName();
                if (doctorDetail.getProfile().getLastName() != null)
                    lName = doctorDetail.getProfile().getLastName();

                tvDoctorName.setText(fName + " " + mName + " " + lName);

                Picasso.with(_context)
                        .load(doctorDetail.getProfile().getImageUrl())
                        .placeholder(R.drawable.ic_progress)   // optional
                        .error(R.drawable.ic_noimage).fit()
                        .into(imgviewProfile);

            }

            if (doctorDetail.getSpecialties() != null && doctorDetail.getSpecialties().size() > 0) {
                if (doctorDetail.getSpecialties().get(0).getActor() != null)
                    tvSpecilization.setText(doctorDetail.getSpecialties().get(0).getActor());
            }

            if (doctorDetail.getPractices() != null && doctorDetail.getPractices().size() > 0) {
                if (doctorDetail.getPractices().get(0).getName() != null)
                    tvAddress.setText(doctorDetail.getPractices().get(0).getName());
            }

        }


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
