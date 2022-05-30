package osp.smgonggu.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

public class JoinActivity extends AppCompatActivity {

    private DatabaseReference mDatabase;// 파이어베이스 연동할 데이터베이스 리퍼런스 함수

    // 로그 찍을 때 사용하는 TAG 변수
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    EditText userid_et, passwd_et;
    Button join_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

// 컴포넌트 초기화
        userid_et = findViewById(R.id.userid_et);
        passwd_et = findViewById(R.id.passwd_et);
        join_button = findViewById(R.id.join_button);
        mDatabase = FirebaseDatabase.getInstance().getReference(); //

// 버튼 이벤트 추가
        join_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
// 회원가입 함수 호출
                String getUserId = userid_et.getText().toString();
                String getUserPassword = passwd_et.getText().toString();

                //hashmap 만들기
                //hashmap?: 해시코드를 생성해서 해시맵에 있는 요소(이 코드로 치면
                HashMap result = new HashMap<>();
                result.put("Id", getUserId);
                result.put("password", getUserPassword);

                writeNewUser("1", getUserId, getUserPassword);

            }
        });

    }
    private void writeNewUser(String userId, String Id, String password) {
        User user = new User(Id, password);

        mDatabase.child("users").child(userId).setValue(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Write was successful!
                        Toast.makeText(MainActivity.this, "저장을 완료했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Write failed
                        Toast.makeText(MainActivity.this, "저장을 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

    }


}
