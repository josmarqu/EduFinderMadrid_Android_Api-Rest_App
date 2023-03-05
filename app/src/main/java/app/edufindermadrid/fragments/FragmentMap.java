package app.edufindermadrid.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;


import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import app.edufindermadrid.R;
import app.edufindermadrid.entities.EduCenter;
import app.edufindermadrid.entities.EduCenterList;

public class FragmentMap extends Fragment {
    protected SupportMapFragment map;
    private EduCenterList eduCenterList;

    public static  FragmentMap newInstance(EduCenterList eduCenterList) {
        FragmentMap fragment = new FragmentMap();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putSerializable("eduCenterList", eduCenterList);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            eduCenterList = (EduCenterList) getArguments().getSerializable("eduCenterList");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        map = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        map.getMapAsync(googleMap -> {
            googleMap.clear();
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            LatLng pos = null;
            for (EduCenter eduCenter : eduCenterList.getEduCenters()) {
                pos = new LatLng(eduCenter.getLocation().getLatitude(), eduCenter.getLocation().getLongitude());
                googleMap.addMarker(new MarkerOptions().position(pos).title(eduCenter.getTitle()));
                builder.include(pos);
            }
            if (pos == null)
                return;
            googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(builder.build(), 128));
        });
        return view;
    }
}
