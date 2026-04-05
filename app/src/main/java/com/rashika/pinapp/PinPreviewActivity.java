package com.rashika.pinapp;

import androidx.core.content.FileProvider;
import android.content.pm.PackageManager;
import android.net.Uri;
import java.io.File;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PinPreviewActivity extends AppCompatActivity {

    public static final String KEY_TITLE       = "pin_title";
    public static final String KEY_DESCRIPTION = "pin_description";
    public static final String KEY_CATEGORY    = "pin_category";

    private ImageView ivPreviewImage;
    private Uri photoUri;

    // ── GALLERY PICKER ───────────────────────────────────────────────────
    // Register BEFORE onCreate — this is the modern replacement for
    // startActivityForResult(). The callback fires when the user picks.
    private final ActivityResultLauncher<String> pickImageLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.GetContent(),
                    uri -> {
                        if (uri != null) {
                            ivPreviewImage.setImageURI(uri);
                        }
                    }
            );
    private final ActivityResultLauncher<Uri> cameraLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.TakePicture(),
                    success -> {
                        if (success) {
                            // photoUri already points to the saved photo
                            ivPreviewImage.setImageURI(photoUri);
                        }
                    }
            );


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_preview);

        ivPreviewImage = findViewById(R.id.ivPreviewImage);

        // Populate pin data from Intent
        String title    = getIntent().getStringExtra(KEY_TITLE);
        String desc     = getIntent().getStringExtra(KEY_DESCRIPTION);
        String category = getIntent().getStringExtra(KEY_CATEGORY);
        ((TextView) findViewById(R.id.tvPreviewTitle)).setText(title);
        ((TextView) findViewById(R.id.tvPreviewDescription)).setText(desc);
        ((TextView) findViewById(R.id.tvPreviewCategory)).setText(category);

        // Gallery button
        findViewById(R.id.btnPickGallery).setOnClickListener(v ->
                pickImageLauncher.launch("image/*")  // MIME type filter
        );

        // Camera button — wired up in Step 4
        findViewById(R.id.btnTakePhoto).setOnClickListener(v ->
                takePicture()
        );
    }
    private void takePicture() {
        // Check camera hardware exists on this device
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)) {
            Toast.makeText(this, "No camera found", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create temp file in external cache — FileProvider will share it
        File photoFile = new File(getExternalCacheDir(), "pin_photo.jpg");
        photoUri = FileProvider.getUriForFile(
                this,
                getApplicationContext().getPackageName() + ".fileprovider",
                photoFile
        );

        // Launch camera — it saves directly to photoUri
        cameraLauncher.launch(photoUri);
    }

}
