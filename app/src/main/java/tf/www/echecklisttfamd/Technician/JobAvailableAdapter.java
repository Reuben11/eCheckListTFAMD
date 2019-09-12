package tf.www.echecklisttfamd.Technician;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tf.www.echecklisttfamd.JobAvailableClass;
import tf.www.echecklisttfamd.R;

public class JobAvailableAdapter extends BaseAdapter {
    private static ArrayList<JobAvailableClass> jobAvailableList;
    private LayoutInflater mInflater;

    public JobAvailableAdapter(Context JobAvailable, ArrayList<JobAvailableClass> result) {
        jobAvailableList = result;
        mInflater = LayoutInflater.from(JobAvailable);
    }

    @Override
    public  int getCount(){
        return jobAvailableList.size();
    }

    @Override
    public Object getItem(int position){
        return jobAvailableList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    public static class Viewholder{
        TextView jrNumber, requestor, jrDateTime, jobType, equipment, area;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        JobAvailableAdapter.Viewholder holder;

        convertView = mInflater.inflate(R.layout.jobavailable_list, null);
        holder = new JobAvailableAdapter.Viewholder();

        holder = new JobAvailableAdapter.Viewholder();
        holder.jrNumber = convertView.findViewById(R.id.jrnumber);
        holder.area = convertView.findViewById(R.id.area);
        holder.equipment = convertView.findViewById(R.id.equipment);
        holder.requestor = convertView.findViewById(R.id.requestor);
        holder.jobType = convertView.findViewById(R.id.jobtype);
        holder.jrDateTime = convertView.findViewById(R.id.jrDateTime);
        convertView.setTag(holder);

        holder.jrNumber.setText("JR " + jobAvailableList.get(position).getJr());
        holder.area.setText(jobAvailableList.get(position).getArea());
        holder.equipment.setText("Equipment : " + jobAvailableList.get(position).getEquipment());
        holder.requestor.setText("Req. By : " + jobAvailableList.get(position).getRequestor());
        holder.jobType.setText(jobAvailableList.get(position).getChecklist());
        holder.jrDateTime.setText(jobAvailableList.get(position).getTime());

        return convertView;

    }
}
