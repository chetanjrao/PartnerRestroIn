package in.restroin.partnerrestroin.interfaces;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

import java.util.List;

import in.restroin.partnerrestroin.models.BookingDataModel;
import in.restroin.partnerrestroin.models.BookingModel;
import in.restroin.partnerrestroin.models.CouponsModel;
import in.restroin.partnerrestroin.models.MessageModel;
import in.restroin.partnerrestroin.models.ProfileModel;
import in.restroin.partnerrestroin.models.RestaurantProfileModel;
import okhttp3.ResponseBody;
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

    @FormUrlEncoded
    @POST("restaurants/restaurantProfile")
    Call<RestaurantProfileModel> getRestaurantData(
            @Field("access_key") String access_key,
            @Field("partner_id") String partner_id,
            @Field("restaurant_id") String restaurant_id
    );

    @FormUrlEncoded
    @POST("restaurants/restaurantProfile")
    Call<RestaurantProfileModel> getRestaurantReviewsData(
            @Field("access_key") String access_key,
            @Field("partner_id") String partner_id,
            @Field("restaurant_id") String restaurant_id
    );

    @FormUrlEncoded
    @POST("bookings/bookingData")
    Call<List<BookingModel>> getBookingData(
            @Field("access_key") String access_key,
            @Field("partner_id") String partner_id,
            @Field("restaurant_id") String restaurant_id,
            @Field("booking_status") String booking_status
    );

    @FormUrlEncoded
    @POST("bookings/UpdateBookings")
    Call<MessageModel> updateBooking(
      @Field("access_key") String access_key,
      @Field("partner_id") String partner_id,
      @Field("booking_id") String booking_id,
      @Field("restaurant_id") String restaurant_id,
      @Field("process_next_step") String next_step,
      @Field("billAmount") String billAmount
    );

    @FormUrlEncoded
    @POST("bookings/getBookings")
        Call<BookingDataModel> getSingleBookingData(
            @Field("access_key") String access_key,
            @Field("partner_id") String partner_id,
            @Field("restaurant_id") String restaurant_id,
            @Field("booking_id") String booking_id
    );

    @GET("restaurants/getCoupons")
    Call<List<CouponsModel>> getCoupons();

    @FormUrlEncoded
    @POST("restaurants/UpdateRestaurant")
    Call<MessageModel> updateCoupons(
            @Field("access_key") String access_key,
            @Field("coupons_list") String coupons,
            @Field("restaurant_id") String resatuarnt_id,
            @Field("partner_id") String partner_id
    );

}
