package com.example.tubes_uts_e_2.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tubes_uts_e_2.R;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {
    private MapView mapView;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
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
        Mapbox.getInstance(getContext().getApplicationContext(), getString(R.string.mapbox_access_token));

        View view  = inflater.inflate(R.layout.fragment_second, container, false);
        mapView = (MapView) view.findViewById(R.id.mapView);


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull MapboxMap mapboxMap) {
                mapboxMap.setStyle(Style.MAPBOX_STREETS);
                LatLng pos1 = new LatLng(-7.787155, 110.436537);
                LatLng pos2 = new LatLng(-7.779672, 110.425458);
                LatLng pos3 = new LatLng(-7.781628, 110.440986);
                LatLng pos4 = new LatLng(-7.766478,110.357402);
                LatLng pos5 = new LatLng(-7.730418,110.462527);
                LatLng pos6 = new LatLng(-8.391409,115.237746);
                LatLng pos7 = new LatLng(-8.315320,114.622109);
                LatLng pos8 = new LatLng(-8.086967,113.599714);
                LatLng pos9 = new LatLng(-7.336282,110.502572);
                mapboxMap.addMarker(new MarkerOptions().position(pos1));
                mapboxMap.addMarker(new MarkerOptions().position(pos2));
                mapboxMap.addMarker(new MarkerOptions().position(pos3));
                mapboxMap.addMarker(new MarkerOptions().position(pos4));
                mapboxMap.addMarker(new MarkerOptions().position(pos5));
                mapboxMap.addMarker(new MarkerOptions().position(pos6));
                mapboxMap.addMarker(new MarkerOptions().position(pos7));
                mapboxMap.addMarker(new MarkerOptions().position(pos8));
                mapboxMap.addMarker(new MarkerOptions().position(pos9));
                mapboxMap.addOnMapClickListener(new MapboxMap.OnMapClickListener() {
                    @Override
                    public boolean onMapClick(@NonNull LatLng point) {
                        //MarkerOptions markerOptions =  new MarkerOptions();
                        //markerOptions.position(point);
                        //mapboxMap.clear();
                        //mapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                        //      point,10
                        //));
                        //mapboxMap.addMarker(markerOptions);
                        return false;
                    }

                });
            }

        });

        return view;
    }

    @Override
    @SuppressWarnings({"MissingPermission"})
    public void onStart(){
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory(){
        super.onLowMemory();
        mapView.onLowMemory();
    }
}