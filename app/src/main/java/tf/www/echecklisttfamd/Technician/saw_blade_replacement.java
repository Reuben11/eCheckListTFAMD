package tf.www.echecklisttfamd.Technician;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import tf.www.echecklisttfamd.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class saw_blade_replacement extends Fragment {
    private TextView tvEquipment, tvJR, tvDatetime, tvTech, tvRequestor, tvDaily, tvRequestorName, tvTechName;
    private String scode;
    private RadioGroup hubmountz1, hubmountz2, coolerbarz1, coolerbarz2, verifybladez1, verifybladez2, bladetypez1, bladetypez2, ncsprismz1, ncsprismz2, bladesetupz1, bladesetupz2, bladedressingz1, bladedressingz2, bladebbdsetupz1, bladebbdsetupz2, tapez1, tapez2, waferbackside;
    private EditText bladelifelinez1, bladelifelinez2, bladencsz1, bladencsz2, bbdopenz1, bbdopenz2, bbdclosez1, bbdclosez2, ncsvaluez1, ncsvaluez2, ncsexpoz1, ncsexpoz2, receipez1, receipez2, precutz1, precutz2, verifykerf, buyoffkerf;
    private CheckBox hairlinez1, hairlinez2, bladeexposurez1, bladeexposurez2, inspection;
    private Spinner magslot;
    private Button submit;


    public static saw_blade_replacement newInstance() {
        // Required empty public constructor
        saw_blade_replacement fragment = new saw_blade_replacement();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_saw_blade_replacement, container, false);
        tvEquipment = view.findViewById(R.id.equipmentname);
        tvJR = view.findViewById(R.id.jrnumber);
        tvDatetime = view.findViewById(R.id.nowdatetime);
        tvTech = view.findViewById(R.id.techemp);
        tvRequestor = view.findViewById(R.id.requestorid);
        tvDaily = view.findViewById(R.id.purposeoption);
        tvRequestorName = view.findViewById(R.id.requestorname);
        tvTechName = view.findViewById(R.id.techname);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss ,  dd MMM yy");
        String dateStr = df.format(c);
        tvDatetime.setText(dateStr);

        hubmountz1 = view.findViewById(R.id.z1hubmount);
        hubmountz2 = view.findViewById(R.id.z2hubmount);

        coolerbarz1 = view.findViewById(R.id.z1coolerbarmount);
        coolerbarz2 = view.findViewById(R.id.z2coolerbarmount);

        verifybladez1 = view.findViewById(R.id.z1verifymount);
        verifybladez2 = view.findViewById(R.id.z2verifymount);

        verifybladez1 = view.findViewById(R.id.z1verifymount);
        verifybladez2 = view.findViewById(R.id.z2verifymount);

        bladetypez1 = view.findViewById(R.id.z1bladetype);
        bladetypez2 = view.findViewById(R.id.z2bladetype);

        bladelifelinez1 = view.findViewById(R.id.lifespanz1);
        bladelifelinez2 = view.findViewById(R.id.lifespanz2);

        bbdopenz1 = view.findViewById(R.id.bbdopenz1);
        bbdopenz2 = view.findViewById(R.id.bbdopenz1);

        bbdclosez1 = view.findViewById(R.id.bbdclosez1);
        bbdclosez2 = view.findViewById(R.id.bbdclosez2);

        ncsvaluez1 = view.findViewById(R.id.ncsz1);
        ncsvaluez2 = view.findViewById(R.id.ncsz2);

        ncsprismz1 = view.findViewById(R.id.z1ncsprism);
        ncsprismz2 = view.findViewById(R.id.z2ncsprism);

        bladesetupz1 = view.findViewById(R.id.z1bladesetup);
        bladesetupz2 = view.findViewById(R.id.z2bladesetup);

        ncsexpoz1 = view.findViewById(R.id.bladeexposurez1);
        ncsexpoz2 = view.findViewById(R.id.bladeexposurez2);

        bladedressingz1 = view.findViewById(R.id.bladedressingz1);
        bladedressingz2 = view.findViewById(R.id.bladedressingz2);

        receipez1 = view.findViewById(R.id.receipez1);
        receipez2 = view.findViewById(R.id.receipez2);

        precutz1 = view.findViewById(R.id.precutz1);
        precutz2 = view.findViewById(R.id.precutz2);

        hairlinez1 = view.findViewById(R.id.z1hairalignmentyes);
        hairlinez2 = view.findViewById(R.id.z2hairalignmentyes);


        bladencsz1 = view.findViewById(R.id.kerfz1);
        bladencsz2 = view.findViewById(R.id.kerfz2);

        bladeexposurez1 = view.findViewById(R.id.z1checkncsyes);
        bladeexposurez2 = view.findViewById(R.id.z2checkncsyes);

        bladebbdsetupz1 = view.findViewById(R.id.z1bbdbladesetup);
        bladebbdsetupz2 = view.findViewById(R.id.z2bbdbladesetup);


        tapez1 = view.findViewById(R.id.z1tape);
        tapez2 = view.findViewById(R.id.z2tape);

        inspection = view.findViewById(R.id.inspection);

        magslot = view.findViewById(R.id.checkmagno);

        verifykerf = view.findViewById(R.id.hairlinecorrection);

        buyoffkerf = view.findViewById(R.id.buyoffkerf);

        waferbackside = view.findViewById(R.id.waferbackside);

        submit = view.findViewById(R.id.setupsubmit);

        getAll();
        return view;
    }

    protected void getAll(){
        SharedPreferences prefs = getContext().getSharedPreferences("Technician_Apps", MODE_PRIVATE);
        tvJR.setText(prefs.getString("jr","no data"));
        tvEquipment.setText(prefs.getString("equipmentname","no data"));
        tvRequestor.setText(prefs.getString("requestor","no data"));
        tvTech.setText(prefs.getString("techid","no data"));
        tvRequestorName.setText(prefs.getString("msname", "no data"));
        tvTechName.setText(prefs.getString("techname", "no data").toUpperCase());
        scode = prefs.getString("scode", "no data");
    }
}
