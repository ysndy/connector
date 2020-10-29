package com.example.connector.soohyun.shared;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.MainActivity;
import com.example.connector.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONObject;

import java.util.EmptyStackException;
import java.util.HashMap;

class EmptyException extends Exception {
} //null 예외정의

class CheckException extends Exception {
} // CheckBox 안했을 시 예외처리

public class Signup extends AppCompatActivity {

    EditText editName, joinId, joinPass, joinPass2, joinEmail, bodyNum, tailNum, storeName;
    TextView joinAgree;
    Button goBtn, IDver, emailver, agreeBtn;
    CheckBox checkAgree;
    ImageButton backBtn;
    Spinner headNum, category; //휴대폰번호앞, 카테고리
    RadioButton sign_sup, sign_rest;

    String texttName, textID, textPW1, textPW2, textEmail; //Text로 받아오기위한 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        Intent intent = getIntent();


        goBtn = findViewById(R.id.goBtn);
        editName = findViewById(R.id.editName); //이름입력
        joinId = findViewById(R.id.joinId); //아이디 입력
        joinPass = findViewById(R.id.joinPass); //패스워드입력
        joinPass2 = findViewById(R.id.joinPass2); //패스워드확인입력
        joinEmail = findViewById(R.id.joinEmail); //이메일 입력
        headNum = findViewById(R.id.headNum);
        bodyNum = findViewById(R.id.bodyNum);
        tailNum = findViewById(R.id.tailNum);
        checkAgree = findViewById(R.id.checkAgree);
        IDver = findViewById(R.id.IDver);
        emailver = findViewById(R.id.email_ver);
        backBtn = findViewById(R.id.backBtn);
        agreeBtn = findViewById(R.id.agreeBtn);
        sign_rest = findViewById(R.id.sign_rest);
        sign_sup = findViewById(R.id.sign_sup);
        storeName = findViewById(R.id.storeName);
        category = findViewById(R.id.category);

        final String[] spinNum = {"010", "016", "017", "018", "019", "011"}; //전화번호 앞자리 배열데이터

        ArrayAdapter<String> adapter; //전화번호 앞
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, spinNum);
        headNum.setAdapter(adapter);

        final String[] spinCate = {"과일", "정육/계란", "생선/해산물/건어물", "우유/유제품", "냉동식품/밥/돈까스", "라면/면류", "베이커리/잼", "생수/음료", "가루/밀가루", "장류/조미류/식용유", "위생용품/화장지/물티슈", "일회용품", "기타"};
        ArrayAdapter<String> adaptercate;
        adaptercate = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, spinCate);
        category.setAdapter(adaptercate);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        agreeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupAgree.class);
                startActivity(intent);
            }
        });

        IDver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Signup.this);
                dlg.setTitle("아이디 중복확인");
                dlg.setMessage("사용가능한 ID입니다.");
                dlg.setPositiveButton("확인",null);
                dlg.show();
            }
        });


        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String callnumber = (headNum.getSelectedItem().toString() + "-" + bodyNum.getText().toString() + "-" + tailNum.getText().toString()); //전화번호

                try {

                    texttName = editName.getText().toString(); //이름
                    textID = joinId.getText().toString(); //아이디
                    textPW1 = joinPass.getText().toString(); //비밀번호
                    textPW2 = joinPass2.getText().toString(); //비밀번호확인
                    textEmail = joinEmail.getText().toString(); //이메일

                    if (texttName.isEmpty() || textID.isEmpty() || textPW1.isEmpty() || textPW2.isEmpty() || textEmail.isEmpty())
                        throw new EmptyException(); //입력칸 모두 작성 안 했을 경우
                    else if (checkAgree.isChecked()) {
                    } else {
                        throw new CheckException(); // 체크박스 체크 안 했을 경우
                    }
                    //비밀번호 일치여부
                    if (!joinPass.getText().toString().equals((joinPass2.getText().toString()))){
                        Toast.makeText(Signup.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_LONG).show();
                        joinPass.setText("");
                        joinPass2.setText("");
                        joinPass.requestFocus();
                        return;
                    }

                    if (sign_sup.isChecked()) { //공급업자 회원가입

                        //서버연동
                        Response.Listener rListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jResponse = new JSONObject(response);
                                    Log.d("asd", "hellosup");
                                }catch (Exception e) {
                                    Log.d("asd", "Signup.java - " + e.toString());
                                }

                            }
                        };

                        Log.d("asd", "RequestValue1" + editName.getText().toString()+ " " + joinId.getText().toString()+" "+ joinPass.getText().toString()+" "+ joinEmail.getText().toString()+ " "+ callnumber);
                        InsertSignUp_sup insertSignUp_sup = new InsertSignUp_sup(editName.getText().toString(), joinId.getText().toString(), joinPass.getText().toString(), joinEmail.getText().toString(), callnumber, storeName.getText().toString(), category.getSelectedItem().toString(), rListener);
                        RequestQueue queue = Volley.newRequestQueue(Signup.this);
                        queue.add(insertSignUp_sup);

                        msg_Commit_sup(); //입력완료 후 공급업자 회원가입 성공 메세지 메소드
                    }
                    else //외식업자 회원가입
                    //서버연동
                    {
                        Response.Listener rListener = new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject jResponse = new JSONObject(response);
                                    Log.d("asd", "hello_res");
                                }catch (Exception e) {
                                    Log.d("asd", "Signup.java - " + e.toString());
                                }

                            }
                        };
                        Log.d("asd", "RequestValue2 " + editName.getText().toString()+ " " + joinId.getText().toString()+" "+ joinPass.getText().toString()+" "+ joinEmail.getText().toString()+ " "+ callnumber);
                        InsertSignUp_res insertSignUp_res = new InsertSignUp_res(editName.getText().toString(), joinId.getText().toString(), joinPass.getText().toString(), joinEmail.getText().toString(), callnumber, storeName.getText().toString(), category.getSelectedItem().toString(),  rListener);
                        RequestQueue queue = Volley.newRequestQueue(Signup.this);
                        queue.add(insertSignUp_res);

                        msg_Commit_rest(); //입력완료 후 외식업자 회원가입 성공 메세지 메소드
                    }


              } catch (EmptyException e){
                    Exception(0);
               } catch (CheckException ee){
                  Exception(1);
               }

            }
        });

        emailver.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(Signup.this);
                dlg.setTitle("이메일 인증");
                dlg.setMessage("입력하신 이메일의 메일함을 확인하여 인증해주세요.");
                dlg.setPositiveButton("확인",null);
                dlg.show();
            }
       });
    }

    private void msg_Commit_sup(){ //공급자 회원가입 완료 메세지 창
        AlertDialog.Builder dlg = new AlertDialog.Builder(Signup.this);
        dlg.setTitle("공급업자 회원가입 완료");
        dlg.setMessage("축하합니다.\nGo래처 회원이 되었습니다.\n공급업자로 로그인해주세요. ");
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        dlg.show();
    }

    private void msg_Commit_rest(){ //외식업자 회원가입 완료 메세지 창
        AlertDialog.Builder dlg = new AlertDialog.Builder(Signup.this);
        dlg.setTitle("외식업자 회원가입 완료");
        dlg.setMessage("축하합니다.\nGo래처 회원이 되었습니다.\n외식업자로 로그인해주세요. ");
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        dlg.show();
    }

    public void Exception(int num) {
        if (num == 0) { //입력칸 모두 입력 안 했을 경우 예외처리
            Toast.makeText(this, "모든 정보를 입력해주세요.", Toast.LENGTH_LONG).show();
        } else if (num == 1) { //체크박스 체크 안 했을 경우 예외처리
            Toast.makeText(this, "동의여부에 체크하지 않으셨습니다.", Toast.LENGTH_SHORT).show();
        }
    }
}

