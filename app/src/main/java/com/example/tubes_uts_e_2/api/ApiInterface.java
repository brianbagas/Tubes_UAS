package com.example.tubes_uts_e_2.api;

import com.example.tubes_uts_e_2.model.Ticket;
import com.example.tubes_uts_e_2.model.TicketResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @Headers({"Accept: application/json"})
    @GET("ticket")
    Call<TicketResponse> getAllTicket();

    @Headers({"Accept: application/json"})
    @GET("ticket/{id}")
    Call<TicketResponse> getTicketById(@Path("id") long id);

    @Headers({"Accept: application/json"})
    @GET("ticket/user/{user}")
    Call<TicketResponse> getTicketByUser(@Path("user") long id);

    @Headers({"Accept: application/json"})
    @POST("ticket")
    Call<TicketResponse> createTicket(@Body Ticket ticket);

    @Headers({"Accept: application/json"})
    @PUT("ticket/{id}")
    Call<TicketResponse> updateTicket(@Path("id") long id, @Body Ticket ticket);

    @Headers({"Accept: application/json"})
    @DELETE("ticket/{id}")
    Call<TicketResponse> deleteTicket(@Path("id") long id);

}
