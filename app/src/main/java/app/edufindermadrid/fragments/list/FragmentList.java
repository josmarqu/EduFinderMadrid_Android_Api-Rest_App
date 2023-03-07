package app.edufindermadrid.fragments.list;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.edufindermadrid.EduActivity;
import app.edufindermadrid.EduDetailsActivity;
import app.edufindermadrid.R;
import app.edufindermadrid.api.APIRestService;
import app.edufindermadrid.api.RetrofitClient;
import app.edufindermadrid.entities.EduCenterList;
import app.edufindermadrid.entities.EduDetails;
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
        EduActivity eduActivity = (EduActivity) getActivity();
        if (!eduActivity.checkInternetConnection()){
            Toast.makeText(getContext(), getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            return;
        }
        Retrofit retrofit = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService service = retrofit.create(APIRestService.class);
        String[] urlSplit = eduCenterList.getEduCenters().get(recyclerView.getChildAdapterPosition(view1)).getIdJson().split("/");
        String jsonUrl = urlSplit[urlSplit.length - 1];
        Call<EduDetails> call = service.getEduOrganization(jsonUrl);
        call.enqueue(new retrofit2.Callback<EduDetails>() {
            @Override
            public void onResponse(Call<EduDetails> call, Response<EduDetails> response) {

                EduDetails eduDetails = response.body();
                String organization = eduDetails.getGraph().getOrganizationDetails().getDescription();

                String[] transport = organization.split("(Metro:)|(Bus:)");
                String subway;
                String bus;
                if (transport.length > 1) {
                    subway = transport[1].trim();
                    if (transport.length > 2) {
                        bus = transport[2].trim();
                        bus = bus.substring(0, bus.indexOf('.'));
                    }
                    else {
                        bus = "n/a";
                    }
                }
                else
                {
                    subway = "n/a";
                    bus = "n/a";
                    return;
                }

                String locality = eduDetails.getGraph().getAddress().getLocality();
                String streetAddress = eduDetails.getGraph().getAddress().getStreetAddress();
                String postalCode = eduDetails.getGraph().getAddress().getPostalCode();
                String address = locality + ", " + streetAddress + ", " + postalCode;

                Intent intent = new Intent(getContext(), EduDetailsActivity.class);
                intent.putExtra("subway", subway);
                intent.putExtra("bus", bus);
                intent.putExtra("address", address);
                intent.putExtra("title", eduDetails.getGraph().getTitle());
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<EduDetails> call, Throwable t) {
                Toast.makeText(getContext(), getString(R.string.error) + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}