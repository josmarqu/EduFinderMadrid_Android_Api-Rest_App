package app.edufindermadrid.fragments.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.edufindermadrid.R;
import app.edufindermadrid.entities.EduCenter;

public class FragmentList extends Fragment {

    private List<EduCenter> eduCenterList;

    public FragmentList(List<EduCenter> eduCenterList) {
        this.eduCenterList = eduCenterList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvListEdu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        System.out.println("EDUCENTERLIST: " + eduCenterList.get(0).getTittle());
        recyclerView.setAdapter(new ListAdapter(eduCenterList));
        return view;
    }

}
