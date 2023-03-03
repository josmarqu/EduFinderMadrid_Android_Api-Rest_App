package app.edufindermadrid.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import app.edufindermadrid.R;

public class FragmentMap extends Fragment {

    public FragmentMap() {
        // Required empty public constructor
    }

    public View onCreateView(android.view.LayoutInflater inflater, android.view.ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);
        return view;
    }
}
