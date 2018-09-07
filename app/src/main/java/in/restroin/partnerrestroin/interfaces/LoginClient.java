/*
 * Copyright (c) 2018. An change in this code must be done under my supervision and any misuse my lead to legal actions
 */

package in.restroin.partnerrestroin.interfaces;

import in.restroin.partnerrestroin.models.LoginModel;
import in.restroin.partnerrestroin.models.ProfileModel;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginClient {

    @POST("api/authorization/client")
    Call<ProfileModel> getDetails(

    );
}
