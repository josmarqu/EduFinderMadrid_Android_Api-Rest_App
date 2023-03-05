package app.edufindermadrid.fragments.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.edufindermadrid.EduInfoActivity;
import app.edufindermadrid.R;
import app.edufindermadrid.entities.EduCenter;

public class FragmentList extends Fragment {

    private List<EduCenter> eduCenterList;
    private Boolean filtered = false;

    public FragmentList(List<EduCenter> eduCenterList, Boolean filtered) {
        this.eduCenterList = eduCenterList;
        this.filtered = filtered;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.rvListEdu);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (filtered) {
            recyclerView.setPadding(0, 160, 0, 0);
        }
        ListAdapter adapter = new ListAdapter(eduCenterList, this.getContext(), filtered);
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(view1 -> {
            EduCenter eduCenter = eduCenterList.get(recyclerView.getChildAdapterPosition(view1));
            Intent intent = new Intent(getContext(), EduInfoActivity.class);
            intent.putExtra("eduTittle", eduCenter.getTitle());
            startActivity(intent);
        });
        return view;
    }
}
