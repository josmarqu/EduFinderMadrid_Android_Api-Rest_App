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


import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

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


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_list);
        initActionBar();
        initFilter();
        initData();
    }

    private void initData() {
        Retrofit retrofit = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService apiRestService = retrofit.create(APIRestService.class);
        Call<EduCenterList> call = apiRestService.getEduCenters();
        call.enqueue(new Callback<EduCenterList>() {
            @Override
            public void onResponse(Call<EduCenterList> call, Response<EduCenterList> response) {
                if (response.isSuccessful()) {
                    EduCenterList eduCenterList = response.body();
                    loadFragment(new FragmentList(eduCenterList.getEduCenters()));
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
        if  (id == R.id.list) {
           // loadFragment(new FragmentList(eduCenterList));
        } else if (id == R.id.map){
            loadFragment(new FragmentMap());
        }
        return super.onOptionsItemSelected(item);
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frgContainer, fragment).addToBackStack(null).commit();
    }

    @Override
    public void onDialogPositiveClick(double lat, double lon, double dis) {
        // Dialog Filter Test
        System.out.println("Lat: " + lat + " Lon: " + lon + " Dis: " + dis);
    }
}