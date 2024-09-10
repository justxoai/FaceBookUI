package vn.edu.usth.facebook;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class FaceBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_face_book);

//        HeaderFragment headerFragment = new HeaderFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.main, headerFragment).commit();
//
//        StatusFragment statusFragment = new StatusFragment();
//        getSupportFragmentManager().beginTransaction().add(R.id.main, statusFragment).commit();



        Log.i("FaceBook", "FaceBook Open");
    }

    @Override
    public void onStart(){
        super.onStart();
        Log.i("FaceBook", "FaceBook Start");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("FaceBook", "FaceBook Resume");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i("FaceBook", "FaceBook Pause");
    }

    @Override
    public void onStop(){
        super.onStop();
        Log.i("FaceBook", "FaceBook Stop");
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        Log.i("FaceBook", "FaceBook Destroy");
    }
}