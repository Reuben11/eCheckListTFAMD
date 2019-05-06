package tf.www.echecklisttfamd.Operator;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;
import tf.www.echecklisttfamd.Technician.JobAvailable;

public class allclass {

    public interface CheckJR{
        @GET
        Call<OperatorEquipmentScanner.GetExist> getJRCheckData(@Url String url);
    }

    public  interface CreateJR{
        @GET
        Call<Device_Change_Setup_CheckList.jR> getCreateJR (@Url String url);
    }

    public interface GetJobRequest{
        @GET
        Call<JobAvailable.JsonResponse> getJson(@Url String url);
    }
}
