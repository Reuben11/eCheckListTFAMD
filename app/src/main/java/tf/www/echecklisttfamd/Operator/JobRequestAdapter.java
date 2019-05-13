package tf.www.echecklisttfamd.Operator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import tf.www.echecklisttfamd.R;

public class JobRequestAdapter extends BaseAdapter {
    private static ArrayList<jobrequestequipmentlist> listjobrequest;
    private LayoutInflater mInflater;

    public JobRequestAdapter(Context OperatorRequestList, ArrayList<jobrequestequipmentlist> result){
        listjobrequest = result;
        mInflater = LayoutInflater.from(OperatorRequestList);
    }

    @Override
    public  int getCount(){
        return listjobrequest.size();
    }

    @Override
    public Object getItem(int arg0){
        return listjobrequest.get(arg0);
    }

    @Override
    public long getItemId(int arg0){
        return arg0;
    }

    static class Viewholder{
        TextView process, equipment, revision;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Viewholder holder;

        convertView = mInflater.inflate(R.layout.jobrequest_list, null);
      /*  holder = new Viewholder();
*/
        holder = new Viewholder();
        holder.process = convertView.findViewById(R.id.process);
        holder.equipment = convertView.findViewById(R.id.equipment);
        holder.revision = convertView.findViewById(R.id.revision);
        convertView.setTag(holder);

        holder.process.setText(listjobrequest.get(position).getProcess());
        holder.equipment.setText(listjobrequest.get(position).getEquipment());
        holder.revision.setText("Revision : " + listjobrequest.get(position).getRevision());

        return convertView;

    }
}
