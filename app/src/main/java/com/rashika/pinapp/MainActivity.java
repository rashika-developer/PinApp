package com.rashika.pinapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout tilTitle, tilDescription;
    private Spinner spinnerCategory;
    private TextView tvCategoryError;
    private Button btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tilTitle       = findViewById(R.id.tilTitle);
        tilDescription = findViewById(R.id.tilDescription);
        spinnerCategory = findViewById(R.id.spinnerCategory);
        tvCategoryError = findViewById(R.id.tvCategoryError);
        btnCreate      = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(v -> validateAndCreate());
    }

    private void validateAndCreate() {
        String title = tilTitle.getEditText().getText().toString().trim();
        String desc  = tilDescription.getEditText().getText().toString().trim();
        int   catPos = spinnerCategory.getSelectedItemPosition();

        boolean valid = true;

        // Validate title
        if (title.isEmpty()) {
            tilTitle.setError(getString(R.string.error_empty_title));
            valid = false;
        } else {
            tilTitle.setError(null);   // clear previous error
        }

        // Validate description
        if (desc.isEmpty()) {
            tilDescription.setError(getString(R.string.error_empty_description));
            valid = false;
        } else {
            tilDescription.setError(null);
        }

        // Validate category (position 0 = "Select category" placeholder)
        if (catPos == 0) {
            tvCategoryError.setText(getString(R.string.error_no_category));
            tvCategoryError.setVisibility(View.VISIBLE);
            valid = false;
        } else {
            tvCategoryError.setVisibility(View.GONE);
        }

        // Inside MainActivity.validateAndCreate() — replace the TODO with this:

        if (valid) {
            String category = spinnerCategory.getSelectedItem().toString();

            Intent intent = new Intent(this, PinPreviewActivity.class);
            intent.putExtra(PinPreviewActivity.KEY_TITLE,       title);
            intent.putExtra(PinPreviewActivity.KEY_DESCRIPTION, desc);
            intent.putExtra(PinPreviewActivity.KEY_CATEGORY,    category);
            startActivity(intent);
        }

    }
}
