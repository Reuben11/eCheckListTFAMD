package tf.www.echecklisttfamd.Operator;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tf.www.echecklisttfamd.JobAvailableClass;
import tf.www.echecklisttfamd.R;

public class JobCancelAdapter extends BaseAdapter {
    private static ArrayList<JobAvailableClass> jobAvailableList;
    private LayoutInflater mInflater;
    Context context;

    public JobCancelAdapter(Context Job_Cancellation, ArrayList<JobAvailableClass> result) {
        jobAvailableList = result;
        this.context = Job_Cancellation;
        mInflater = LayoutInflater.from(Job_Cancellation);
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

    static class Viewholder{
        TextView jrNumber, requestor, jrDateTime, jobType, equipment, area, process;
        ImageView canceljob;
    }



    public View getView(int position, View convertView, ViewGroup parent){
        JobCancelAdapter.Viewholder holder = new JobCancelAdapter.Viewholder();

        convertView = mInflater.inflate(R.layout.jobcancellation_list, null);
        holder.jrNumber = convertView.findViewById(R.id.jrnumber);
        holder.area = convertView.findViewById(R.id.area);
        holder.process = convertView.findViewById(R.id.process);
        holder.equipment = convertView.findViewById(R.id.equipment);
        holder.requestor = convertView.findViewById(R.id.requestor);
        holder.jobType = convertView.findViewById(R.id.jobtype);
        holder.jrDateTime = convertView.findViewById(R.id.jrDateTime);
        holder.canceljob = convertView.findViewById(R.id.deletejob);
        convertView.setTag(holder);

        String[] info = jobAvailableList.get(position).getArea().split("-");


        holder.jrNumber.setText("JR " + jobAvailableList.get(position).getJr());
        holder.area.setText(info[0]);
        holder.process.setText(info[1]);
//        holder.area.setText(jobAvailableList.get(position).getArea());
        holder.equipment.setText("Equipment : " + jobAvailableList.get(position).getEquipment());
        holder.requestor.setText("Req. By : " + jobAvailableList.get(position).getRequestor());
        holder.jobType.setText(jobAvailableList.get(position).getChecklist());
        holder.jrDateTime.setText(jobAvailableList.get(position).getTime());

//        holder.canceljob.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Delete button Clicked", Toast.LENGTH_LONG).show();
//
//
//            }
//        });

        if (info[0].equals("Die Prep ")){
            holder.area.setTextColor(Color.parseColor("#4D50FF"));
        }


        if (info[1].equals(" Pick And Place")){
            holder.process.setTextColor(Color.parseColor("#C58900"));
        }
        else if(info[1].equals(" Saw")){
            holder.process.setTextColor(Color.parseColor("#1E8449"));
        }

        return convertView;

    }
}
