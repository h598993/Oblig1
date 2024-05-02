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
import androidx.lifecycle.ViewModelProvider;

public class AddItem extends AppCompatActivity {

    private Uri selectedImageUri;
    private ItemViewModel itemViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Access to global state can be deleted
        MyApplication globalState = (MyApplication) getApplicationContext();
        setContentView(R.layout.activity_add_item);
        Button addImageBtn = findViewById(R.id.button_get_image);
        Button addToList = findViewById(R.id.button_add_dog);
        Button backButton = findViewById(R.id.button5);
        EditText dogNameInput = findViewById(R.id.input_dog_race_edit);
        Uri photoUri = null;


        // ViewModel setup
        ItemDao itemDao = AppDatabase.getDatabase(getApplicationContext()).itemDao();
        itemViewModel = new ViewModelProvider(this, new ItemViewModelFactory(itemDao)).get(ItemViewModel.class);

        //backButton
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        //Using opendocument to open iamge media instad of the photopicker due to permission issues
        addImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(intent, 1);

            }
        });


//oberserver data for testing
        itemViewModel.getAllItems().observe(this, items -> {
            for (Item item : items) {
                Log.d("LiveDataLog", "Observed Item: " + item.getName());
            }
        });

        addToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the input value from EditText
                String userInput = dogNameInput.getText().toString();
                //remove when finished
                //globalState.addItemToList(new Item(userInput, selectedImageUri.toString()));
                //Adding to database

                itemViewModel.insert(new Item(userInput, selectedImageUri.toString()));
                goBack();
            }


        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri uri = data.getData();
            persistUriPermission(uri);
            selectedImageUri = uri;
        }
    }

    private void persistUriPermission(Uri uri) {
        if (!uri.toString().startsWith("android")) {
            final int takeFlags = Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION;
            getContentResolver().takePersistableUriPermission(uri, takeFlags);
        }
    }


    private void goBack() {
        finish();
    }


}


