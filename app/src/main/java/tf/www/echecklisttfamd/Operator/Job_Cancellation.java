package tf.www.echecklisttfamd.Operator;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Toast;

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
import tf.www.echecklisttfamd.JobAvailableClass;
import tf.www.echecklisttfamd.R;
import tf.www.echecklisttfamd.allclass;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class Job_Cancellation extends Fragment {
    private ArrayList<JobAvailableClass> data;
    private String empid, scode;
    private ListView lv;
    private boolean done;
    private  Timer timer;
    private Boolean datamsg;

    View view;

    public static Job_Cancellation newInstance() {
        // Required empty public constructor
        Job_Cancellation fragment = new Job_Cancellation();
        return  fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_job__cancellation, container, false);
        datamsg = false;
        GetSharePreference();
        GetReqJobs();

      timer = new Timer();
      timer.scheduleAtFixedRate(new TimerTask() {
          @Override
          public void run() {
              GetReqJobs();
          }
      },0, 10000);

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        timer.cancel();
    }

    private void GetReqJobs(){

        String requestapilink = "/api/eCheckList?requestlist=ok";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Call<Job_Cancellation.JsonResponse> call = retrofit.create(allclass.GetJobCancellation.class).getJson(requestapilink);
        call.enqueue(new Callback<Job_Cancellation.JsonResponse>() {
            @Override
            public void onResponse(Call<Job_Cancellation.JsonResponse> call, Response<Job_Cancellation.JsonResponse> response) {

                if (response.isSuccessful()) {
                    Job_Cancellation.JsonResponse jsonResponse = response.body();
                    data = new ArrayList<>(Arrays.asList((jsonResponse.getJoblists())));
                    lv = view.findViewById(R.id.jobcancellist);
                    lv.setAdapter(new JobCancelAdapter(getActivity(), data));
                    lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                            ShowAlertSubmit(data.get(position).getJr(), data.get(position).getEquipment());
                            data.remove(position);
                            lv.setAdapter(new JobCancelAdapter(getActivity(), data));
                        }
                    });

                } else {
                    if(datamsg==false){
                        ShowAlert("Informations", "No Job Request!");
                        datamsg=true;
                    }
                }
            }

            @Override
            public void onFailure(Call<Job_Cancellation.JsonResponse> call, Throwable t) {
                if (t instanceof IOException) {
                    ShowAlert("Error!", t.getMessage());
                } else {

                    // todo log to some central bug tracking service
                }

            }
        });
    }

    private Boolean SubmitCancellation(String JR){
         done = false;


    try{


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://pngjvfa01")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        String url = "api/eCheckList?JR={\"jr\":\""
                        + JR.replace("-","") + "\",\"empid\":\""
                        + empid + "\",\"scode\":\""
                        + scode + "\"}";

        Call<Success> call = retrofit.create(allclass.CancelJR.class).CancelJob(url);
        call.enqueue(new Callback<Success>(){
            @Override
            public void onResponse(Call<Success> call, Response<Success> response) {
                if(response.isSuccessful()){
                    done = true;
                }else{
                    ShowAlert("Connection Error!", "Please check the wireless connection. if problem persist, please contact IT");
                }

            }

            @Override
            public void onFailure(Call<Success> call, Throwable t) {

            }
        });
        done = true;

        }catch (Exception e){

        }
        return done;

    }

    public class Success{
        private String done;

        public String getDone() {
            return done;
        }

        public void setDone(String done) {
            this.done = done;
        }
    }

    public class JsonResponse{
        private JobAvailableClass[] joblists;

        public JobAvailableClass[] getJoblists(){
            return joblists;
        }
    }

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

    private void ShowAlertSubmit(final String title, String Equipment) {
        final Boolean[] ok = {false};
        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
        builder1.setTitle("Cancellation JR " + title);
        builder1.setMessage("Once Job Request cancel on " + Equipment + ", can not be revert!");


        builder1.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(SubmitCancellation(title)==true){
                    ShowAlert("Job Request " + title, "JR " + title + " Successful Cancel!");
                    GetReqJobs();
                }
            }
        });

        builder1.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                GetReqJobs();
            }
        });

        AlertDialog  alert1 = builder1.create();
        alert1.show();

    }

    protected void GetSharePreference(){
        SharedPreferences prefs = getContext().getSharedPreferences("Operator_Apps", MODE_PRIVATE);
        empid = prefs.getString("empid","no data");
        scode = prefs.getString("scode", "no data");

    }


}
