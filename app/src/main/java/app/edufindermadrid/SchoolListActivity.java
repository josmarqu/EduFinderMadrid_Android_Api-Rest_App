package app.edufindermadrid;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

public class SchoolListActivity extends AppCompatActivity {
    private TextView tvTitle;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_list);
        initActionBar();
        tvTitle = findViewById(R.id.tvTitle);
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

    private void setTitle(String title) {
        tvTitle.setText(title);
    }
}