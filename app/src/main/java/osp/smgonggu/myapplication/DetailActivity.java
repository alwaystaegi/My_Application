package osp.smgonggu.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class DetailActivity extends AppCompatActivity {

    // 로그에 사용할 TAG
    final private String TAG = getClass().getSimpleName();

    // 사용할 컴포넌트 선언
    TextView title_tv, content_tv, user_tv;
    LinearLayout comment_layout;
    EditText comment_et;
    Button reg_button;
    View customView;


    //댓글 리스트
    Comment[] comment;

    // 선택한 게시물의 번호
    String board_seq = "";

    // 유저아이디 변수
    String userid = "";


    FirebaseAuth mAuth;

    private FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

// ListActivity 에서 넘긴 변수들을 받아줌
        board_seq = getIntent().getStringExtra("board_seq");
        userid = getIntent().getStringExtra("userid");

// 컴포넌트 초기화
        title_tv = findViewById(R.id.title_tv);
        content_tv = findViewById(R.id.content_tv);
        user_tv = findViewById(R.id.user_tv);

        comment_layout = findViewById(R.id.comment_layout);
        comment_et = findViewById(R.id.comment_et);
        reg_button = findViewById(R.id.reg_button);

        // custom_comment 를 불러오기 위한 객체
        LayoutInflater layoutInflater = LayoutInflater.from(DetailActivity.this);
        customView = layoutInflater.inflate(R.layout.custom_comment, null);




// 등록하기 버튼을 눌렀을 때 댓글 등록 함수 호출
            reg_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RegCmt();
                }
            });


    }
    // onResume() 은 해당 액티비티가 화면에 나타날 때 호출됨
    @Override
    protected void onResume() {
        super.onResume();
// 해당 액티비티가 활성화 될 때, 게시물 리스트를 불러오는 함수를 호출
        loadBoard();
        LoadCmt();
    }
    //게시물 불러오는 함수
    public void loadBoard(){
        db.collection("Board")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                String seq = document.get("index").toString();
                                if (seq== board_seq) {
                                    Toast.makeText(DetailActivity.this,document.getId(),Toast.LENGTH_SHORT).show();
                                    String title = document.get("title").toString();
                                    title_tv.setText(title);
                                    String uid = document.get("uid").toString();
                                    user_tv.setText(uid);
                                    String content= document.get("content").toString();
                                    content_tv.setText(content);

                                }

                            }
                        }
                    }
                });

    }


    // 게시물의 댓글을 읽어오는 함수
    void LoadCmt () {

        db.collection("Board")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String seq = document.get("index").toString();
                                if (seq== board_seq) {


                                    String title = document.get("title").toString();
                                    String content = document.get("uid").toString();
                                    String crt_dt = document.get("content").toString();

                                    ((TextView)customView.findViewById(R.id.cmt_userid_tv)).setText(userid);
                                    ((TextView)customView.findViewById(R.id.cmt_content_tv)).setText(content);
                                    ((TextView)customView.findViewById(R.id.cmt_date_tv)).setText(crt_dt);

// 댓글 레이아웃에 custom_comment 의 디자인에 데이터를 담아서 추가
                                    comment_layout.addView(customView);
                                }

                            }
                        }
                    }
                });

    }

    // 댓글을 등록하는 함수
    void RegCmt  ()
    {


    }
}