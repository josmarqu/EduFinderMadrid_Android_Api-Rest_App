package app.edufindermadrid;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;




public class EduDetailsActivity extends AppCompatActivity {
    private Button btnBack;
    private TextView tvTittle;
    private TextView tvLocation;
    private TextView tvSubway;
    private TextView tvBus;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edu_details);
        initActionBar();
        initButton();
        setData();

    }

    private void initButton() {
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent1 = new Intent(EduDetailsActivity.this, EduActivity.class);
            startActivity(intent1);
        });
    }

    private void setData() {
        Intent intent = getIntent();
        //Organization organization = intent.getParcelableExtra("organization");
     //   if (organization == null)
        {
            System.out.println("ESTA VACIOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
        }
        tvTittle = findViewById(R.id.tvTittle);
        tvLocation = findViewById(R.id.tvLocation);
        tvSubway = findViewById(R.id.tvSubway);
        tvBus = findViewById(R.id.tvBus);
    }

    private void initActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.action_bar);
    }
}