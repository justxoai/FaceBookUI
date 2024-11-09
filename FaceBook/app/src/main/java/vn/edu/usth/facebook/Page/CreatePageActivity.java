package vn.edu.usth.facebook.Page;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.usth.facebook.R;
import vn.edu.usth.facebook.model.request.PageRequest;
import vn.edu.usth.facebook.model.response.CommonResponse;
import vn.edu.usth.facebook.retrofit.RetrofitService;
import vn.edu.usth.facebook.retrofit.api.PageAPI;

public class CreatePageActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Button createPageButton;
    private ImageButton backButton;
    private EditText editName;
    private EditText editDescription;
    private Button avatarSelect;
    private ImageView avatarPreview;
    private Uri avatarUri;
    private PageAPI pageAPI;
    private RetrofitService retrofitService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_page);
        init();
        setUpButtonListeners();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Get the selected image URI
            avatarUri = data.getData();
            Log.d("CreatePageActivity", "Selected avatar URI: " + avatarUri);
            // Set image preview
            avatarPreview.setImageURI(avatarUri);
        } else {
            Toast.makeText(this, "Image selection failed", Toast.LENGTH_SHORT).show();
        }
    }
    private void init() {
        createPageButton = findViewById(R.id.create_page_button);
        backButton = findViewById(R.id.back_button);
        editName = findViewById(R.id.create_page_name);
        editDescription = findViewById(R.id.create_page_description);
        avatarSelect = findViewById(R.id.create_page_select_avatar);
        avatarPreview = findViewById(R.id.create_page_preview_avatar);
        retrofitService = new RetrofitService();
        pageAPI = retrofitService.getRetrofit().create(PageAPI.class);
    }
    private void setUpButtonListeners(){
        avatarSelect.setOnClickListener(view -> openImagePicker());
        createPageButton.setOnClickListener(view -> collectData());
        backButton.setOnClickListener(view -> finish());
    }
    private void openImagePicker() {
        // Intent to pick an image
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }
    private File getFileFromContentUri(Uri contentUri, Context context) {
        File file = null;
        try {
            ContentResolver resolver = context.getContentResolver();
            InputStream inputStream = resolver.openInputStream(contentUri);
            // Get the file extension from the URI
            String fileExtension = MimeTypeMap.getSingleton()
                    .getExtensionFromMimeType(resolver.getType(contentUri));
            if (fileExtension == null) fileExtension = "jpg"; // Default if unknown
            // Create a temporary file with the correct extension
            file = File.createTempFile("avatar_", "." + fileExtension, context.getCacheDir());
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }
    private String encodeImageToBase64(File imageFile) {
        try {
            FileInputStream fileInputStream = new FileInputStream(imageFile);
            byte[] bytes = new byte[(int) imageFile.length()];
            fileInputStream.read(bytes);
            fileInputStream.close();
            return Base64.encodeToString(bytes, Base64.DEFAULT); // Base64 encoding
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    private void collectData() {
        // Collect data from EditTexts
        String name = editName.getText().toString().trim();
        String description = editDescription.getText().toString().trim();
        // Convert the selected avatar image file to a Base64 string
        String avatarB64 = null;
        if (avatarUri != null) {
            File avatarFile = getFileFromContentUri(avatarUri, this);
            if (avatarFile != null && avatarFile.exists()) {
                avatarB64 = encodeImageToBase64(avatarFile);
            }
        }
        // Build PageRequest object TODO: more fields
        PageRequest pageRequest = new PageRequest();
        pageRequest.setName(name);
        pageRequest.setDescription(description);
        pageRequest.setAvatarB64(avatarB64);
        // Convert PageRequest to JSON
        String pageRequestJson = new Gson().toJson(pageRequest);
        RequestBody pageRequestBody = RequestBody.create(pageRequestJson, MediaType.parse("application/json"));
        // Make network call
        sendRequest(pageRequestBody);
    }
    private void sendRequest(RequestBody pageRequestBody) {
        // Send the request
        Call<CommonResponse> call = pageAPI.createPage(pageRequestBody);
        call.enqueue(new Callback<CommonResponse>() {
            @Override
            public void onResponse(Call<CommonResponse> call, Response<CommonResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Handle successful response
                    Log.i("CreatePageActivity", "done post");
                    finish();
                } else {
                    // Handle server errors
                    Log.i("CreatePageActivity", "fail post");
                }
            }
            @Override
            public void onFailure(Call<CommonResponse> call, Throwable t) {
                // Handle network failure
            }
        });
    }
}