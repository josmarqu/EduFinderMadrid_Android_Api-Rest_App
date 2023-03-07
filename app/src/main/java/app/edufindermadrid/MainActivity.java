package app.edufindermadrid;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
private Button btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButton();
    }

    private void initButton() {
        btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EduActivity.class);
            startActivity(intent);
        });
    }
}