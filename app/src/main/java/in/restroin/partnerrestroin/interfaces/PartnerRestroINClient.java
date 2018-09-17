package in.restroin.partnerrestroin.interfaces;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import in.restroin.partnerrestroin.models.MessageModel;
import in.restroin.partnerrestroin.models.ProfileModel;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PartnerRestroINClient {
    @FormUrlEncoded
    @POST("authorization/partner")
    Call<MessageModel> updateUid(
            @Field("access_key") String access_key,
            @Field("device_uid") String device_uid
    );

    @FormUrlEncoded
    @POST("authorization/partnerProfile")
    Call<ProfileModel> getProfile(
      @Field("access_key") String access_key,
      @Field("partner_id") String partner_id
    );

}
