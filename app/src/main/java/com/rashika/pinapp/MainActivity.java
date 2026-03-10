package com.rashika.pinapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // View fields — declared at class level for easy access
    private ImageView ivPinImage;
    private TextView  tvTitle;
    private TextView  tvDescription;
    private Button    btnSave;
    private Button    btnShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Bind views by their XML android:id
        ivPinImage    = findViewById(R.id.ivPinImage);
        tvTitle       = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        btnSave       = findViewById(R.id.btnSave);
        btnShare      = findViewById(R.id.btnShare);

        // Button listeners using lambda (Java 8+)
        btnSave.setOnClickListener(v ->
                Toast.makeText(this, "Pin Saved!", Toast.LENGTH_SHORT).show()
        );

        btnShare.setOnClickListener(v ->
                Toast.makeText(this, "Sharing Pin...", Toast.LENGTH_SHORT).show()
        );
    }
}
