package app.edufindermadrid;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


public class EduInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_info);

        Intent intent = getIntent();
        String eduTittle = intent.getStringExtra("eduTittle");
        System.out.println(eduTittle);
    }
}