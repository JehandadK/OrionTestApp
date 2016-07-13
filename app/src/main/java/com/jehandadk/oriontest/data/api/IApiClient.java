package com.jehandadk.oriontest.data.api;

import com.jehandadk.oriontest.data.models.Contact;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by jehandad.kamal on 7/13/2016.
 */
public interface IApiClient {

    @GET("users")
    Observable<List<Contact>> contacts();
}
