package tf.www.echecklisttfamd.Operator;

import retrofit2.Call;
import retrofit2.http.GET;

public class allclass {

    public interface CheckJR{
        @GET("/api/eChecklist?name=ok")
        Call<OperatorEquipmentScanner.GetExist> getJRCheckData();
    }
}
