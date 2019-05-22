package tf.www.echecklisttfamd;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Url;
import tf.www.echecklisttfamd.Operator.Device_Change_Setup_CheckList;
import tf.www.echecklisttfamd.Operator.OperatorScanner;
import tf.www.echecklisttfamd.Technician.JobAvailable;
import tf.www.echecklisttfamd.Technician.TechnicianScanner;

public class allclass {

    public interface CheckJR{
        @GET
        Call<OperatorScanner.GetExist> getJRCheckData(@Url String url);
    }

    public  interface CreateJR{
        @GET
        Call<Device_Change_Setup_CheckList.jR> getCreateJR (@Url String url);
    }

    public interface GetJobRequest{
        @GET
        Call<JobAvailable.JsonResponse> getJson(@Url String url);
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

 /*   public interface GetValidEmp{
        @GET
        Call<OperatorScanner.GetCert> getValid(@Url String url);
    }*/
}
