package com.example.oblig1;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class AddItem extends AppCompatActivity {

    private Uri selectedImageUri;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Access to global state
        MyApplication globalState = (MyApplication) getApplicationContext();
        setContentView(R.layout.activity_add_item);
        Button addImageBtn = findViewById(R.id.button_get_image);
        Button addToList = findViewById(R.id.button_add_dog);
        Button backButton = findViewById(R.id.button5);
        EditText dogNameInput = findViewById(R.id.input_dog_race_edit);
        Uri photoUri = null;
        // Registers a photo picker activity launcher in single-select mode.
        ActivityResultLauncher<PickVisualMediaRequest> pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        selectedImageUri = uri;
                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });

        //backButton
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });




        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the photo picker and let the user choose only images.
                pickMedia.launch(new PickVisualMediaRequest.Builder()
                        .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE)
                        .build());
            }
        });


        addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input value from EditText
                String userInput = dogNameInput.getText().toString();

                globalState.addItemToList(new Item(userInput,selectedImageUri));

                goBack();
            }
        });




    }



private void goBack(){
    Intent goBack = new Intent(AddItem.this, Gallery.class);
    startActivity(goBack);
}



}


