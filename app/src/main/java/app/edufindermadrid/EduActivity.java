package app.edufindermadrid;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import app.edufindermadrid.api.APIRestService;
import app.edufindermadrid.api.RetrofitClient;
import app.edufindermadrid.dialog.DialogFilter;
import app.edufindermadrid.dialog.OnDialogListener;
import app.edufindermadrid.entities.EduCenterList;
import app.edufindermadrid.fragments.FragmentMap;
import app.edufindermadrid.fragments.list.FragmentList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EduActivity extends AppCompatActivity implements OnDialogListener{
    private Button btnFilter;
    private LinearLayout lLayTvMeasures;
    private TextView tvMeasures;
    private Boolean filter = false;
    private Double lat;
    private Double lon;
    private int dis;
    private EduCenterList eduCenterList;
    private Fragment frgContainer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_list);
        initActionBar();
        initFilter();
        loadData(new FragmentList());
    }

    private void loadData(Fragment frg) {
        if (!checkInternetConnection()) {
            Toast.makeText(this, R.string.no_internet, Toast.LENGTH_SHORT).show();
        }
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
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frgContainer, ((FragmentList) frg).newInstance(eduCenterList, filter));
                        transaction.commit();
                    }else {
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frgContainer, ((FragmentMap) frg).newInstance(eduCenterList));
                        transaction.commit();
                    }
                    if (EduActivity.this.eduCenterList.getEduCenters().size() == 0) {
                        Toast.makeText(EduActivity.this, R.string.filter_error, Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onFailure(Call<EduCenterList> call, Throwable t) {
                Toast.makeText(EduActivity.this, getString(R.string.error) + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public boolean checkInternetConnection() {
        boolean isConnected = false;
        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            isConnected = true;
        }
        return isConnected;
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
        if  (id == R.id.list) {
            loadData(new FragmentList());
        } else if (id == R.id.map){
            loadData(new FragmentMap());
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDialogPositiveClick(double lat, double lon, double dis) {
        this.lat = lat;
        this.lon = lon;
        this.dis = (int) dis;
        setFilterLl(lat, lon, dis);
        filter = true;
        frgContainer = getSupportFragmentManager().findFragmentById(R.id.frgContainer);
        if (frgContainer instanceof FragmentList) {
            loadData(new FragmentList());
        }else
        {
            loadData(new FragmentMap());
        }
    }

    @Override
    public void onDialogResetClick() {
        System.out.println("Reset");
        filter = false;
        frgContainer = getSupportFragmentManager().findFragmentById(R.id.frgContainer);
        if (frgContainer instanceof FragmentList) {
            loadData(new FragmentList());
        }else
        {
            loadData(new FragmentMap());
        }
        lLayTvMeasures = findViewById(R.id.lLayTvMeasures);
        lLayTvMeasures.setVisibility(LinearLayout.GONE);
    }

    private void setFilterLl(double lat, double lon, double dis) {
        lLayTvMeasures = findViewById(R.id.lLayTvMeasures);
        tvMeasures = findViewById(R.id.tvMeasures);
        tvMeasures.setText("Lat: " + lat + " Lon: " + lon + "\n Distance: " + dis + " meters");
        lLayTvMeasures.setVisibility(LinearLayout.VISIBLE);
    }
}