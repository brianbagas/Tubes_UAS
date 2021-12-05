package com.example.tubes_uts_e_2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TicketResponse {
    private String message;

    @SerializedName("ticket")
    public List<Ticket> ticketList;

    public String getMessage(){
        return message;
    }

    public void setMessage(String message){this.message = message;}

    public List<Ticket> getTicketList(){return ticketList;}

    public void setTicketList(List<Ticket> ticketList){
        this.ticketList = ticketList;
    }
}
