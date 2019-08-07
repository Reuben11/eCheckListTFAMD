package tf.www.echecklisttfamd.Operator;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tf.www.echecklisttfamd.R;
import tf.www.echecklisttfamd.JobAvailableClass;

public class BuyOffRequestListAdapter extends BaseAdapter {
    private static ArrayList<JobAvailableClass> listBuyOffList;
    private LayoutInflater mInflater;

    public BuyOffRequestListAdapter(Context OperatorBuyOffList, ArrayList<JobAvailableClass> result) {
        listBuyOffList = result;
        mInflater = LayoutInflater.from(OperatorBuyOffList);
    }

    @Override
    public  int getCount(){
        return listBuyOffList.size();
    }

    @Override
    public Object getItem(int position){
        return listBuyOffList.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    static class Viewholder{
        TextView jRNumber, process, area, checklist, equipmentName, nowDateTime;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Viewholder holder;

        convertView = mInflater.inflate(R.layout.buyoffrequest_list, null);

        holder = new Viewholder();
        holder.jRNumber = convertView.findViewById(R.id.jrnumber);
        holder.process = convertView.findViewById(R.id.buyoffprocess);
        holder.area = convertView.findViewById(R.id.buyoffarea);
        holder.checklist = convertView.findViewById(R.id.checklistname);
        holder.equipmentName = convertView.findViewById(R.id.equipmentname);
        holder.nowDateTime = convertView.findViewById(R.id.buyoffdatetime);
        convertView.setTag(holder);

        holder.jRNumber.setText("JR " +listBuyOffList.get(position).getJr());
        holder.process.setText(listBuyOffList.get(position).getProcess());
        holder.area.setText(listBuyOffList.get(position).getArea());
        holder.checklist.setText(listBuyOffList.get(position).getChecklist());
        holder.equipmentName.setText("Equipment : " + listBuyOffList.get(position).getEquipment());
        holder.nowDateTime.setText(listBuyOffList.get(position).getTime());


        if (holder.process.getText().toString().equals("Pick And Place")){
            holder.process.setTextColor(Color.parseColor("#4D50FF"));
        }


        if (holder.process.getText().toString().equals("Pick And Place")){
            holder.process.setTextColor(Color.parseColor("#C58900"));
        }
        else if(holder.process.getText().toString().equals("Saw")){
            holder.process.setTextColor(Color.parseColor("#59DD91"));
        }

        return convertView;

    }
}
