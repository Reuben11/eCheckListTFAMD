package tf.www.echecklisttfamd;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Url;
import tf.www.echecklisttfamd.Operator.Device_Change_Setup_CheckList;
import tf.www.echecklisttfamd.Operator.OperatorEquipmentScanner;
import tf.www.echecklisttfamd.Technician.JobAvailable;
import tf.www.echecklisttfamd.Technician.MachineSetup;

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

    public interface GetSetup{
        @GET
        Call<String> getSetup(@Url String url);

    }
}
