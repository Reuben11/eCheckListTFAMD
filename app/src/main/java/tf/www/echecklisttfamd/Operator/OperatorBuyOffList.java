package tf.www.echecklisttfamd.Operator;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tf.www.echecklisttfamd.R;
import tf.www.echecklisttfamd.Technician.JobAvailable;
import tf.www.echecklisttfamd.JobAvailableClass;
import tf.www.echecklisttfamd.allclass;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class OperatorBuyOffList extends Fragment {
    private String apilink;
    private ArrayList<JobAvailableClass> data;
    private ListView lv;
    private Timer timer;
    private Boolean datamsg;
    View view;


    public static OperatorBuyOffList newInstance() {
        // Required empty public constructor
        OperatorBuyOffList fragment = new OperatorBuyOffList();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_operator_buy_off_list, container, false);
        apilink = getString(R.string.api);
        datamsg = false;
        GetBuyOffJobs();

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                GetBuyOffJobs();
            }
        },0, 10000);

        return view;

    }

    private void GetBuyOffJobs(){
        String requestapilink =  apilink + "OperatorBuyOff=";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<JobAvailable.JsonResponse> call = retrofit.create(allclass.GetBuyOffList.class).getJsonBuyOff(requestapilink);
        call.enqueue(new Callback<JobAvailable.JsonResponse>() {
            @Override
            public void onResponse(Call<JobAvailable.JsonResponse> call, Response<JobAvailable.JsonResponse> response) {

                if (response.isSuccessful()) {
                    JobAvailable.JsonResponse jsonResponse = response.body();
                    data = new ArrayList<>(Arrays.asList((jsonResponse.getJoblists())));


                    lv = view.findViewById(R.id.buyofflist);
                    lv.setAdapter(new BuyOffRequestListAdapter(getActivity(), data));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                            // Do the onItemClick action

                            SetTechID(data.get(position).getTechid());
                            SetJRName(data.get(position).getJr());
                            SetEquipmentName(data.get(position).getEquipment());
                            SetTimeName(data.get(position).getTime());
                            SetTechScode(data.get(position).getScode());
                            SetChecklist(data.get(position).getChecklist());



                            if(data.get(position).getChecklist().equals("Device Change Setup Checklist")) {
                                Fragment newFragment = new OperatorScanner();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                                transaction.replace(R.id.master_container, newFragment);
                                transaction.commit();
                            }else if(data.get(position).getChecklist().equals("Blade Replacement")){
                                Fragment newFragment = new OperatorScanner();
                                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out, android.R.anim.fade_in, android.R.anim.fade_out);
                                transaction.replace(R.id.master_container, newFragment);
                                transaction.commit();
                            }
                        }
                    });

                } else {
                    if(datamsg==false){
                        ShowAlert("Informations", "No Buy Off Job Available!");
                        datamsg=true;
                    }

                }
            }

            @Override
            public void onFailure(Call<JobAvailable.JsonResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    ShowAlert("Error!", t.getMessage());
                } else {

                    // todo log to some central bug tracking service
                }

            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }

   /* public class JsonResponse{
        private JobAvailableClass[] buyOfflists;

        public JobAvailableClass[] getBuyOfflists(){
            return buyOfflists;
        }
    }*/

    private void ShowAlert(String title, String msg){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setTitle(title);
        builder1.setMessage(msg);

        builder1.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog  alert1 = builder1.create();
        alert1.show();
    }

    protected void SetTechScode(String techscode) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("techscode", techscode);
        editor.commit();
    }

    protected void SetTechID(String techid) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("techid", techid);
        editor.commit();
    }

    protected void SetJRName(String jr) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("jr", jr);
        editor.commit();
    }
    protected void SetEquipmentName(String name) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("equipmentname", name);
        editor.commit();
    }

    protected void SetChecklist(String name) {
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("checklistname", name);
        editor.commit();
    }

    protected void SetTimeName(String name) {
        /* SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);*/
        SharedPreferences.Editor editor = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE).edit();
        editor.putString("time", name);
        editor.commit();
    }


}
