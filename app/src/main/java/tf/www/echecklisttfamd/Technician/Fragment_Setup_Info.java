package tf.www.echecklisttfamd.Technician;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import tf.www.echecklisttfamd.Operator.saw_blade_table;
import tf.www.echecklisttfamd.R;
import tf.www.echecklisttfamd.allclass;

public class Fragment_Setup_Info extends Fragment {

    private String apilink;
    private String jobRequestID;
    private TextView EmpID, EmpName, DateTime, BladeChange, BladeGroup, BladeConditionZ1, BladeConditionZ2, NewBladeConditionZ1, NewBladeConditionZ2, BladeLifeZ1, BladeLifeZ2;
    private ImageView infobtn;


    SharedPreferences prf;

    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_setup_info, container, false);
        apilink = getString(R.string.api);

        infobtn = view.findViewById(R.id.infobladegroup_img);
        EmpID = view.findViewById(R.id.reqID_value);
        EmpName = view.findViewById(R.id.reqName_value);
        DateTime = view.findViewById(R.id.infodatetime_value);
        BladeChange = view.findViewById(R.id.bladechange_value);
        BladeGroup = view.findViewById(R.id.bladegroup_value);
        BladeConditionZ1 = view.findViewById(R.id.bladeconditionZ1_value);
        BladeConditionZ2 = view.findViewById(R.id.bladeconditionZ2_value);
        NewBladeConditionZ1 = view.findViewById(R.id.NbladeconditionZ1_value);
        NewBladeConditionZ2 = view.findViewById(R.id.NbladeconditionZ2_value);
        BladeLifeZ1 = view.findViewById(R.id.bladelifeZ1_value);
        BladeLifeZ2 = view.findViewById(R.id.bladelifeZ2_value);

        GetRequestorInfo();
        GetsetupInfo();

        infobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saw_blade_table dialog = new saw_blade_table ();
                dialog .show(getFragmentManager(), "Infomations");
            }
        });
        return view;
    }

    private void GetRequestorInfo() {
        SharedPreferences prefs = getContext().getSharedPreferences("Technician_Apps", Context.MODE_PRIVATE);
        EmpID.setText(prefs.getString("requestor", "no data"));
        EmpName.setText(prefs.getString("msname", "no data"));
    }

    private void GetsetupInfo() {
        String criteria;

        prf = getContext().getSharedPreferences("jr_details", Context.MODE_PRIVATE);
        jobRequestID = prf.getString("jrid", null);

        criteria = apilink + "InfoSetup={\"jrid\":\"" + jobRequestID  + "\"}";

        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        Call<String> call = retrofit.create(allclass.GetBSUInfo.class).getBladeSetupInfo(criteria);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("ResponseString", response.body());

                if(response.isSuccessful()){
                    if(response.body()!=null)
                    {
                        Toast.makeText(getContext(), "detail retrive success", Toast.LENGTH_SHORT).show();
                        Log.i("onSuccess", response.body());

                        String jsonresponse = response.body();
                        PopulateData(jsonresponse);
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(getContext(), "detail retrieve fail", Toast.LENGTH_SHORT).show();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void PopulateData(String jsonresponse) {
        Toast.makeText(getContext(), "PopulateData function", Toast.LENGTH_SHORT).show();

        try{
            JSONObject object = new JSONObject(jsonresponse);

//            ArrayList<BladeChangeSetupInfoModel>  bladeChangeSetupInfoArrayList = new ArrayList<>();
            BladeChangeSetupInfoModel BCModel = new BladeChangeSetupInfoModel();
            JSONArray dataArray =   object.getJSONArray("info");


            //Data fetching from JSON
            for (int i=0; i < dataArray.length(); i++)
            {
//                BladeChangeSetupInfoModel BCModel = new BladeChangeSetupInfoModel();
                JSONObject dataobj = dataArray.getJSONObject(i);

                if (i==0) {
                    BCModel.setEmpId(dataobj.getString("answer0"));
                } else if (i==1){
                    BCModel.setDateTime(dataobj.getString("answer1"));
                } else if (i==2){
                    BCModel.setBladeChangeZ1(dataobj.getString("answer2"));
                } else if (i==3){
                    BCModel.setBladeChangeZ2(dataobj.getString("answer3"));
                } else if (i==4){
                    BCModel.setBladeGroup(dataobj.getString("answer4"));
                } else if (i==5){
                    BCModel.setUsedBladeConditionZ1(dataobj.getString("answer5"));
                } else if (i==6){
                    BCModel.setUsedBladeConditionZ2(dataobj.getString("answer6"));
                } else if (i==7){
                    BCModel.setNewBladeConditionZ1(dataobj.getString("answer7"));
                } else if (i==8){
                    BCModel.setNewBladeConditionZ2(dataobj.getString("answer8"));
                } else if (i==9){
                    BCModel.setBladeLifeZ1(dataobj.getString("answer9"));
                } else if (i==10){
                    BCModel.setBladeLifeZ2(dataobj.getString("answer10"));
                }

            }
//            bladeChangeSetupInfoArrayList.add(new BladeChangeSetupInfoModel(BCModel.getEmpId(), BCModel.getDateTime(), BCModel.getBladeChangeZ1()
//                        ,BCModel.getBladeChangeZ2(), BCModel.getBladeGroup(), BCModel.getUsedBladeConditionZ1(), BCModel.getUsedBladeConditionZ2()
//                        ,BCModel.getNewBladeConditionZ1(), BCModel.getNewBladeConditionZ2(),BCModel.getBladeLifeZ1(), BCModel.getBladeLifeZ2()));

            //Data Population on TextView
//            for (int j=0; j<bladeChangeSetupInfoArrayList.size();j++)
//            {
            String bladechangez1, bladechangez2, bladegroup, Ubladeconditionz1, Ubladeconditionz2, Nbladeconditionz1, Nbladeconditionz2,bladelifez1, bladelifez2;

            //Set Date Time
            DateTime.setText(BCModel.getDateTime());

            //Format json data into String format
            bladechangez1 = BCModel.getBladeChangeZ1();
            bladechangez2 = BCModel.getBladeChangeZ2();

            bladegroup = BCModel.getBladeGroup();

            Ubladeconditionz1 = BCModel.getUsedBladeConditionZ1();
            Ubladeconditionz2 = BCModel.getUsedBladeConditionZ2();

            Nbladeconditionz1 = BCModel.getNewBladeConditionZ1();
            Nbladeconditionz2 = BCModel.getNewBladeConditionZ2();

            bladelifez1 = BCModel.getBladeLifeZ1();
            bladelifez2 = BCModel.getBladeLifeZ2();

            //BladeChange data format
            if (bladechangez1.equals("true") && bladechangez2.equals("true")) {
                    BladeChange.setText("Z1 , Z2");
                } else if(bladechangez1.equals("true") && bladechangez2.equals("false")){
                    BladeChange.setText("Z1");
                } else if(bladechangez1.equals("false") && bladechangez2.equals("true")){
                    BladeChange.setText("Z2");
                }

            //BladeGroup data format
            if(bladegroup.equals("0")) {
                    BladeGroup.setText("A");
                } else if (bladegroup.equals("1")){
                    BladeGroup.setText("B");
                } else if(bladegroup.equals("2")){
                    BladeGroup.setText("C");
                } else if(bladegroup.equals("3")){
                    BladeGroup.setText("D");
                }

            //UsedBladeCondition data format
            if (bladechangez1.equals("true") && bladechangez2.equals("true")){
                    //Z1
                    if (Ubladeconditionz1.equals("0")){
                        BladeConditionZ1.setText("Z1: Good");
                    } else if(Ubladeconditionz1.equals("1")){
                        BladeConditionZ1.setText("Z1: Abnormal");
                    } else if (Ubladeconditionz1.equals("-1")){
                        BladeConditionZ1.setText("");
                    }
                    //Z2
                    if (Ubladeconditionz2.equals("0")){
                        BladeConditionZ2.setText("Z2: Good");
                    }else if (Ubladeconditionz2.equals("1")){
                        BladeConditionZ2.setText("Z2: Abnormal");
                    }else if (Ubladeconditionz2.equals("-1")){
                        BladeConditionZ2.setText("");
                    }
                } else if (bladechangez1.equals("true") && bladechangez2.equals("false")) {
                    //Z1
                    if (Ubladeconditionz1.equals("0")){
                        BladeConditionZ1.setText("Z1: Good");
                    } else if(Ubladeconditionz1.equals("1")){
                        BladeConditionZ1.setText("Z1: Abnormal");
                    } else if (Ubladeconditionz1.equals("-1")){
                        BladeConditionZ1.setText("");
                    }
                    //Z2
                    BladeConditionZ2.setText("");
                } else if (bladechangez1.equals("false") && bladechangez2.equals("true")){
                    //Z1
                    BladeConditionZ1.setText("");
                    //Z2
                    if (Ubladeconditionz2.equals("0")){
                        BladeConditionZ2.setText("Z2: Good");
                    }else if (Ubladeconditionz2.equals("1")){
                        BladeConditionZ2.setText("Z2: Abnormal");
                    }else if (Ubladeconditionz2.equals("-1")){
                        BladeConditionZ2.setText("");
                    }
                }

            //NewBladeCondition data format
            if(bladechangez1.equals("true") && bladechangez2.equals("true")) {
                    //Z1
                    if (Nbladeconditionz1.equals("true")){
                        NewBladeConditionZ1.setText("Z1: Good");
                    } else if(Nbladeconditionz1.equals("false")){
                        NewBladeConditionZ1.setText("Z1: Abnormal");
                    }
                    //Z2
                    if (Nbladeconditionz2.equals("true")){
                        NewBladeConditionZ2.setText("Z2: Good");
                    }else if (Nbladeconditionz2.equals("false")){
                        NewBladeConditionZ2.setText("Z2: Abnormal");
                    }
                } else if (bladechangez1.equals("true") && bladechangez2.equals("false")) {
                    //Z1
                    if (Nbladeconditionz1.equals("true")){
                        NewBladeConditionZ1.setText("Z1: Good");
                    } else if (Nbladeconditionz1.equals("false")){
                        NewBladeConditionZ1.setText("Z1: Abnormal");
                    }
                    //Z2
                    NewBladeConditionZ2.setText("");
                } else if (bladechangez1.equals("false") && bladechangez2.equals("true")){
                    //Z1
                    NewBladeConditionZ1.setText("");
                    //Z2
                    if (Nbladeconditionz2.equals("true")){
                        NewBladeConditionZ2.setText("Z2: Good");
                    }else if (Nbladeconditionz2.equals("false")){
                        NewBladeConditionZ2.setText("Z2: Abnormal");
                    }
                }


            //Bladelife data format
            if (bladechangez1.equals("true") && bladechangez2.equals("true")) {
                //Z1
                if (bladelifez1.equals("0")) {
                    BladeLifeZ1.setText("Z1: ✔");
                } else if (bladelifez1.equals("1")) {
                    BladeLifeZ1.setText("Z1: NA");
                } else if (bladechangez1.equals("-1")) {
                    BladeLifeZ1.setText("");
                }
                //Z2
                if (bladelifez2.equals("0")) {
                    BladeLifeZ2.setText("Z2: ✔");
                } else if (bladelifez2.equals("1")) {
                    BladeLifeZ2.setText("Z2: NA");
                } else if (bladelifez2.equals("-1")) {
                    BladeLifeZ2.setText("");
                }
            } else if (bladechangez1.equals("true") && bladechangez2.equals("false")) {
                //Z1
                if (bladelifez1.equals("0")) {
                    BladeLifeZ1.setText("Z1: ✔");
                } else if (bladelifez1.equals("1")) {
                    BladeLifeZ1.setText("Z1: NA");
                } else if (bladechangez1.equals("-1")) {
                    BladeLifeZ1.setText("");
                }
                //Z2
                BladeLifeZ2.setText("");
            } else if (bladechangez1.equals("false") && bladechangez2.equals("true")){
                //Z1
                BladeLifeZ1.setText("");
                //Z2
                if (bladelifez2.equals("0")) {
                    BladeLifeZ2.setText("Z2: ✔");
                } else if (bladelifez2.equals("1")) {
                    BladeLifeZ2.setText("Z2: NA");
                } else if (bladelifez2.equals("-1")) {
                    BladeLifeZ2.setText("");
                }
            }
//           }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
