package com.example.educonnectadmin.api;

import static com.example.educonnectadmin.Notification.Constants.CONTENT_TYPE;
import static com.example.educonnectadmin.Notification.Constants.SERVER_KEY;

import com.example.educonnectadmin.Notification.PushNotification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
//    @Headers({"Authorization: key="+SERVER_KEY,"Content-Type:"+CONTENT_TYPE})
    @Headers({"Content-Type:application/json",
            "Authorization:key=AAAA242hxWI:APA91bEzamsSsGKQZt8Tlker2CuKZgRP31h1XtNZzIcD--ZwpmU1ingIWQqc2mLstDC3rt1CAJLukt8e_xXShHBkYALeGTlSiFvmy7p7hRcOkQ6_nCBk6hi0GnPznN6RwpnBTKH-eEES"})
    @POST("fcm/send")
    Call<PushNotification> sendNotification(@Body PushNotification notification);
}
