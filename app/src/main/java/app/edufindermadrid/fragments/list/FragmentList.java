package app.edufindermadrid.fragments.list;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.edufindermadrid.R;
import app.edufindermadrid.api.APIRestService;
import app.edufindermadrid.api.RetrofitClient;
import app.edufindermadrid.entities.EduCenterList;
import app.edufindermadrid.entities.EduOrganization;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class FragmentList extends Fragment {
    private EduCenterList eduCenterList;
    private Boolean filtered;

    public FragmentList() {
        // Required empty public constructor
    }

    public static FragmentList newInstance(EduCenterList eduCenterList, Boolean filtered) {
        FragmentList fragment = new FragmentList();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        args.putParcelable("eduCenterList", eduCenterList);
        args.putBoolean("filtered", filtered);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            eduCenterList = (EduCenterList) getArguments().getParcelable("eduCenterList");
            filtered = getArguments().getBoolean("filtered");
        }
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
                    loadData(recyclerView, view1);
                }
        );
        return view;
    }

    private void loadData(RecyclerView recyclerView, View view1) {
        Retrofit retrofit = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService service = retrofit.create(APIRestService.class);
        String[] urlSplit = eduCenterList.getEduCenters().get(recyclerView.getChildAdapterPosition(view1)).getIdJson().split("/");
        String jsonUrl = urlSplit[urlSplit.length - 1];
        Call<EduOrganization> call = service.getEduOrganization(jsonUrl);
        call.enqueue(new retrofit2.Callback<EduOrganization>() {
            @Override
            public void onResponse(Call<EduOrganization> call, Response<EduOrganization> response) {
                EduOrganization eduOrganization = response.body();
                System.out.println(eduOrganization.getGraph().getOrganizationDetails().getDescription());

            }

            @Override
            public void onFailure(Call<EduOrganization> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}