package tf.www.echecklisttfamd;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import tf.www.echecklisttfamd.Operator.Device_Change_Setup_CheckList;
import tf.www.echecklisttfamd.Operator.Job_Cancellation;
import tf.www.echecklisttfamd.Operator.OperatorScanner;
import tf.www.echecklisttfamd.Technician.JobAvailable;
import tf.www.echecklisttfamd.Technician.TechnicianScanner;

public class allclass {

    public interface CheckJR{
        @GET
        Call<OperatorScanner.GetExist> getJRCheckData(@Url String url);
    }

    public interface CheckJRT{
        @GET
        Call<String> getCheckData(@Url String url);
    }

    public interface GetCleaning{
        @GET
        Call<String> getData(@Url String url);
    }

    public interface CreateCleaning{
        @GET
        Call<String>CreateData(@Url String url);
    }

    public  interface CreateJR{
        @GET
        Call<Device_Change_Setup_CheckList.jR> getCreateJR (@Url String url);
    }

    public interface CancelJR{
        @GET
        Call<Job_Cancellation.Success> CancelJob(@Url String url);
    }

    public interface GetJobRequest{
        @GET
        Call<JobAvailable.JsonResponse> getJson(@Url String url);
    }

    public interface GetJobCancellation{
        @GET
        Call<Job_Cancellation.JsonResponse> getJson(@Url String url);
    }

    public interface GetSetup{
        @GET
        Call<String> getSetup(@Url String url);

    }

    public interface GetBuyOffList{
        @GET
        Call<JobAvailable.JsonResponse> getJsonBuyOff(@Url String url);
    }

    public  interface GetBuyOffData{
        @GET
        Call<String> getBuyOffInfo(@Url String url);
    }

    public interface GetEmpInfo{
        @GET
        Call<LoginActivity.EmpInfo> getEmpData(@Url String url);
    }

    public interface GetST{
        @GET
        Call<TechnicianScanner.resultApi> getSTDone(@Url String url);
    }

    public interface GetTMS{
        @GET
        Call<String> getTMSData(@Url String url);
    }

    public interface GetBlade{
        @GET
        Call<String> getBladeSelection(@Url String url);
    }

 /*   public interface GetValidEmp{
        @GET
        Call<OperatorScanner.GetCert> getValid(@Url String url);
    }*/
}
