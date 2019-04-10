package tf.www.echecklisttfamd.Operator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tf.www.echecklisttfamd.R;

public class BuyOffRequestListAdapter extends BaseAdapter {
    private static ArrayList<OperatorJobRequestNumberList> listBuyOffList;
    private LayoutInflater mInflater;

    public BuyOffRequestListAdapter(Context OperatorBuyOffList, ArrayList<OperatorJobRequestNumberList> result) {
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
        TextView jRNumber, equipmentName, nowDateTime;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        BuyOffRequestListAdapter.Viewholder holder;

        convertView = mInflater.inflate(R.layout.buyoffrequest_list, null);
        holder = new BuyOffRequestListAdapter.Viewholder();

        holder = new BuyOffRequestListAdapter.Viewholder();
        holder.jRNumber = convertView.findViewById(R.id.jrnumber);
        holder.equipmentName = convertView.findViewById(R.id.equipmentname);
        holder.nowDateTime = convertView.findViewById(R.id.buyoffdatetime);
        convertView.setTag(holder);

        holder.jRNumber.setText("JR " +listBuyOffList.get(position).getjRNumber());
        holder.equipmentName.setText("Equipment : " + listBuyOffList.get(position).getEquipmentName());
        holder.nowDateTime.setText(listBuyOffList.get(position).getNowDateTime());


        return convertView;

    }
}
