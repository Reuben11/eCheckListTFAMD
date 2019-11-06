package tf.www.echecklisttfamd.Technician;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import tf.www.echecklisttfamd.Operator.saw_blade_table;
import tf.www.echecklisttfamd.R;

public class Fragment_Setup_Info extends Fragment {

    private String apilink;
    private TextView EmpID, EmpName, DateTime, BladeChange, BladeGroup, BladeCondition, NewBladeCondition, BladeLife;
    private ImageView infobtn;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_setup_info, container, false);

        infobtn = view.findViewById(R.id.infobladegroup_img);

        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saw_blade_table dialog = new saw_blade_table ();
                dialog .show(getFragmentManager(), "Infomations");
            }
        });
        return view;
    }
}
