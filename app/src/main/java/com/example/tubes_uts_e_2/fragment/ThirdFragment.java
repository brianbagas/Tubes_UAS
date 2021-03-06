package com.example.tubes_uts_e_2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.tubes_uts_e_2.R;
import com.example.tubes_uts_e_2.activity.HomeActivity;
import com.example.tubes_uts_e_2.adapter.TicketAdapter;
import com.example.tubes_uts_e_2.api.ApiClient;
import com.example.tubes_uts_e_2.api.ApiInterface;
import com.example.tubes_uts_e_2.callback.CallBackInterface;
import com.example.tubes_uts_e_2.model.Ticket;
import com.example.tubes_uts_e_2.model.TicketResponse;
import com.example.tubes_uts_e_2.model.User;
import com.example.tubes_uts_e_2.preferences.UserPreferences;
// import com.facebook.shimmer.ShimmerFrameLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThirdFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThirdFragment extends Fragment implements CallBackInterface {
//    private ShimmerFrameLayout layout;
    private RecyclerView rv_ticket;
    private List<Ticket> ticketList;
    private TicketAdapter ticketAdapter;
    private ApiInterface apiService;
    private LinearLayout layoutLoading;
    private long temp;
    private Handler handler = new Handler();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    UserPreferences userPreferences;
    User user;
    String username;

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

        apiService = ApiClient.getClient().create(ApiInterface.class);
    //    layoutLoading = view.findViewById(R.id.layout_loading);
//        layout = view.findViewById(R.id.shimer);
        rv_ticket = view.findViewById(R.id.ticket_list);
        rv_ticket.setLayoutManager(new LinearLayoutManager(getContext()));
        ticketList = new ArrayList<>();

        userPreferences = new UserPreferences(getContext());
        user = userPreferences.getUserLogin();
        username = user.getUsername();
        getUserTickets(username);

//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                layout.stopShimmer();
//                layout.hideShimmer();
//                layout.setVisibility(View.GONE);
//
//
//                rv_ticket.setVisibility(View.VISIBLE);
//            }
//        },8000);
    }

    private void getTickets() {
        Call<TicketResponse> call = apiService.getAllTicket();
        call.enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
           if (response.isSuccessful()){
               ticketList = response.body().getTicketList();
               rv_ticket.setAdapter(new TicketAdapter(ticketList,getActivity()));
                   Toast.makeText(getActivity(),
                           response.body().getMessage(),
                           Toast.LENGTH_SHORT).show();
           }
           else{
                   try { JSONObject jObjError = new JSONObject(response.errorBody().string());
                       Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                   }
                   catch (Exception e) {
                         Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                   }
              }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                Toast.makeText(getActivity(),"Network Error 1",Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

//        class GetTickets extends AsyncTask<Void, Void, List<Ticket>> {
//
//            @Override
//            protected List<Ticket> doInBackground(Void... voids) {
//                List<Ticket> ticketList = DatabaseTicket.getInstance(getActivity())
//                        .getDatabase()
//                        .ticketDao()
//                        .getAll();
//
//                return ticketList;
//            }
//
//            @Override
//            protected void onPostExecute(List<Ticket> ticketList) {
//                super.onPostExecute(ticketList);
//                ticketAdapter = new TicketAdapter(ticketList, getActivity());
//                rv_ticket.setAdapter(ticketAdapter);
//            }
//        }
//        GetTickets get = new GetTickets();
//        get.execute();

    private void getUserTickets(String user) {
        Call<TicketResponse> call = apiService.getTicketByUser(user);
        call.enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                if (response.isSuccessful()) {
                    ticketList = response.body().getTicketList();
                    rv_ticket.setAdapter(new TicketAdapter(ticketList, getActivity()));
                    Toast.makeText(getActivity(),
                            response.body().getMessage(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(), jObjError.getString("message"), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Network Error 1", Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    public void deleteTicket(int id) {
        // TODO: Tambahkan fungsi untuk menghapus data buku.
        Call<TicketResponse> call = apiService.deleteTicket(id);
//        setLoading(true);
        call.enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {
                if (response.isSuccessful() && response.body() != null){
                    Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    getTickets();
                }else {
                    try { JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(getActivity(),
                                jObjError.getString("message"),
                                Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
//                setLoading(false);
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Network error", Toast.LENGTH_SHORT).show();
//                setLoading(false);
            }
        });
    }


    @Override
    public void callBackMethod() {
        getTickets();
    }

//    private void setLoading(boolean isLoading) {
//        if (isLoading) {
//            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
//                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            layoutLoading.setVisibility(View.VISIBLE);
//        } else {
//            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
//            layoutLoading.setVisibility(View.GONE);
//        }
//    }
}