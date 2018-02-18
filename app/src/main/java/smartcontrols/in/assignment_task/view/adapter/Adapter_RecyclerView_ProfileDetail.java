package smartcontrols.in.assignment_task.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import smartcontrols.in.assignment_task.R;
import smartcontrols.in.assignment_task.model.Education;
import smartcontrols.in.assignment_task.model.Practice;
import smartcontrols.in.assignment_task.model.Specialty;

/**
 * Created by Prateek on 2/18/2018.
 */

public class Adapter_RecyclerView_ProfileDetail extends RecyclerView.Adapter<Adapter_RecyclerView_ProfileDetail.MyViewHolder> {

    private List<Object> objectList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView1, textView2, textView3;

        public MyViewHolder(View view) {
            super(view);
            textView1 = (TextView) view.findViewById(R.id.textView1);
            textView2 = (TextView) view.findViewById(R.id.textView2);
            textView3 = (TextView) view.findViewById(R.id.textView3);
        }
    }


    public Adapter_RecyclerView_ProfileDetail(List<Object> objectList) {
        this.objectList = objectList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_profiledetail, parent, false);
        return new MyViewHolder(itemView);
    }

    /*
    *   Method for Binding the holder to view
     *  @param holder Holderview object which is bind with the row..
    *   @param positon of the view
    * */
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Object object = objectList.get(position);
        if (object instanceof Education) {
            Education educationDetail = (Education) object;
            if (educationDetail != null) {
                if (educationDetail.getSchool() != null)
                    holder.textView1.setText(educationDetail.getSchool());
                else holder.textView1.setVisibility(View.GONE);

                if (educationDetail.getDegree() != null)
                    holder.textView2.setText(educationDetail.getDegree());
                else holder.textView2.setVisibility(View.GONE);

                if (educationDetail.getGraduationYear() != null)
                    holder.textView3.setText(educationDetail.getGraduationYear());
                else holder.textView3.setVisibility(View.GONE);
            }


        }
        if (object instanceof Practice) {
            Practice practiceDetail = (Practice) object;
            if (practiceDetail.getName() != null)
                holder.textView1.setText(practiceDetail.getName());
            else holder.textView1.setVisibility(View.GONE);

            if (practiceDetail.getPhones() != null && practiceDetail.getPhones().size() > 0) {
                if (practiceDetail.getPhones().get(0).getNumber() != null)
                    holder.textView2.setText(practiceDetail.getPhones().get(0).getNumber());
                else holder.textView2.setVisibility(View.GONE);
            } else {
                holder.textView2.setVisibility(View.GONE);
            }

            if (practiceDetail.getVisitAddress() != null) {
                String street = "", state = "", city = "";
                if (practiceDetail.getVisitAddress().getCity() != null)
                    city = practiceDetail.getVisitAddress().getCity();
                if (practiceDetail.getVisitAddress().getStreet() != null)
                    street = practiceDetail.getVisitAddress().getStreet();
                if (practiceDetail.getVisitAddress().getState() != null)
                    state = practiceDetail.getVisitAddress().getState();

                holder.textView3.setText(street + " " + city + " " + state);
            } else {
                holder.textView3.setVisibility(View.GONE);
            }
        }
        if (object instanceof Specialty) {
            Specialty specialty = (Specialty) object;

            if (specialty.getName() != null) holder.textView1.setText(specialty.getName());
            if (specialty.getActor() != null) holder.textView2.setText(specialty.getActor());

            holder.textView3.setVisibility(View.GONE);

        }
    }

    /*
    *   Method for size of list.
    **/
    @Override
    public int getItemCount() {
        return objectList.size();
    }
}
