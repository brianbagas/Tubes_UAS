package com.example.tubes_uts_e_2.fragment;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.adapter.TicketAdapter;
import com.example.tubes_uts_e_2.db.DatabaseTicket;
import com.example.tubes_uts_e_2.model.Ticket;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment {

    private RecyclerView rv_ticket;
    private List<Ticket> ticketList;
    private TicketAdapter ticketAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThirdFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ThirdFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ThirdFragment newInstance(String param1, String param2) {
        ThirdFragment fragment = new ThirdFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false);
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rv_ticket = view.findViewById(R.id.ticket_list);
        rv_ticket.setLayoutManager(new LinearLayoutManager(getActivity()));

        getTickets();
        ticketList = new ArrayList<>();
    }

    private void getTickets() {
        class GetTickets extends AsyncTask<Void, Void, List<Ticket>> {

            @Override
            protected List<Ticket> doInBackground(Void... voids) {
                List<Ticket> ticketList = DatabaseTicket.getInstance(getActivity())
                        .getDatabase()
                        .ticketDao()
                        .getAll();

                return ticketList;
            }

            @Override
            protected void onPostExecute(List<Ticket> ticketList) {
                super.onPostExecute(ticketList);
                ticketAdapter = new TicketAdapter(ticketList, getActivity());
                rv_ticket.setAdapter(ticketAdapter);
            }
        }
        GetTickets get = new GetTickets();
        get.execute();
    }
}