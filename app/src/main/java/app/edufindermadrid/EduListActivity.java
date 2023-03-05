package app.edufindermadrid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import app.edufindermadrid.api.APIRestService;
import app.edufindermadrid.api.RetrofitClient;
import app.edufindermadrid.dialog.DialogFilter;
import app.edufindermadrid.dialog.OnDialogListener;
import app.edufindermadrid.entities.EduCenter;
import app.edufindermadrid.entities.EduCenterList;
import app.edufindermadrid.fragments.FragmentMap;
import app.edufindermadrid.fragments.list.FragmentList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EduListActivity extends AppCompatActivity implements OnDialogListener{
    private Button btnFilter;
    private LinearLayout lLayTvMeasures;
    private TextView tvMeasures;
    private Boolean filter = false;
    private Double lat;
    private Double lon;
    private int dis;
    private EduCenterList eduCenterList;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_list);
        initActionBar();
        initFilter();
        FragmentList fragmentList = new FragmentList();
        loadData(fragmentList);
    }


    private void loadData(Fragment frg) {
        Retrofit retrofit = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService apiRestService = retrofit.create(APIRestService.class);
        Call<EduCenterList> call;
        if (filter == false) {
            call = apiRestService.getEduCenters();
        }
        else {
            call = apiRestService.getEduCenterFilter(lat, lon, dis);
        }
        call.enqueue(new Callback<EduCenterList>() {
            @Override
            public void onResponse(Call<EduCenterList> call, Response<EduCenterList> response) {
                if (response.isSuccessful()) {
                    eduCenterList = response.body();
                    if (frg instanceof FragmentList) {
                        FragmentList fragmentList = (FragmentList) frg;
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frgContainer, fragmentList.newInstance(eduCenterList, filter));
                        transaction.commit();
                    }else
                    {
                        FragmentMap fragmentMap = (FragmentMap) frg;
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frgContainer, fragmentMap.newInstance(eduCenterList));
                        transaction.commit();
                    }
                    if (EduListActivity.this.eduCenterList.getEduCenters().size() == 0) {
                        Toast.makeText(EduListActivity.this, R.string.filterError, Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<EduCenterList> call, Throwable t) {
                System.out.println("Error: " + t.getMessage());
            }
        });
    }

    private void initFilter() {
        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(view -> {
            DialogFilter dialogFilter = new DialogFilter();
            dialogFilter.setCancelable(false);
            dialogFilter.show(getSupportFragmentManager(), "dialogFilter");
        });
    }

    private void initActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        Drawable drawable = getDrawable(R.drawable.hamburger_menu_icon);
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{drawable});
        layerDrawable.setLayerInset(0, 0, 40, 20, 0);
        toolbar.setOverflowIcon(layerDrawable);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.action_bar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Fragment frg = null;
        if  (id == R.id.list) {
            frg = new FragmentList(); 
        } else if (id == R.id.map){
            frg = new FragmentMap(); 
        }
        loadData(frg);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogPositiveClick(double lat, double lon, double dis) {
        this.lat = lat;
        this.lon = lon;
        this.dis = (int) dis;
        setFilterLl(lat, lon, dis);
        filter = true;
        FragmentList fragmentList = FragmentList.newInstance(eduCenterList, filter);
        loadData(fragmentList);
    }

    private void setFilterLl(double lat, double lon, double dis) {
        lLayTvMeasures = findViewById(R.id.lLayTvMeasures);
        tvMeasures = findViewById(R.id.tvMeasures);
        tvMeasures.setText("Lat: " + lat + " Lon: " + lon + "\n Distance: " + dis + " meters");
        lLayTvMeasures.setVisibility(LinearLayout.VISIBLE);
    }

}