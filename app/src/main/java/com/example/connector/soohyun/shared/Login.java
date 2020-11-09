package com.example.connector.soohyun.shared;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.connector.R;
import com.example.connector.doyeon.lib.IntentName;
import com.example.connector.doyeon.activity.mainview.MainPageActivity;
import com.example.connector.doyeon.lib.request.ValidateRequest;
import com.example.connector.doyeon.lib.request.ValidateRequest_Res;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kakao.auth.ISessionCallback;
import com.kakao.auth.Session;
import com.kakao.util.exception.KakaoException;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1; //구글 API
    private GoogleSignInClient mGoogleSignInClient; //구글 API

    EditText ID, Password;
    Button login1Btn, naverLogin, fjoin, fid, shareLoginBtn;
    SignInButton googleBtn;//구글 로그인 버튼
    //volley test
    String userID, userPW;
    EditText etId;
    RadioGroup loginTypeRG;
    RadioButton rest_check, supply_check, none_check; //로그인 시 외식업자인지 공급업자인지 체크
    CheckBox autoLogin;

    OAuthLogin mOAuthLoginModule;//네이버
    Context mContext; //네이버

    //네이버Client
    private static String OAUTH_CLIENT_ID = "uMzH56lm9EaQQmvV4jJm";
    private static String OAUTH_CLIENT_SECRET = "c0mTBFEUDa";
    //LoginButton kakaoLogin; //카카오로그인버튼

    private static String OAUTH_CLIENT_NAME = "고래처";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getHashKey(); //해시값가져오기

        // 세션 콜백 등록 (카카오)
//        Session.getCurrentSession().addCallback(sessionCallback);

        /*구글API*/
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso); //구글API

        ID = findViewById(R.id.ID);
        Password = findViewById(R.id.Password);
        naverLogin = findViewById(R.id.naverLogin);
        fjoin = findViewById(R.id.fjoin);
        fid = findViewById(R.id.fid);
        shareLoginBtn = findViewById(R.id.ShareLoginBtn);
        googleBtn = findViewById(R.id.googleBtn);
        loginTypeRG = findViewById(R.id.loginTypeRG);
        rest_check = findViewById(R.id.rest_check);
        supply_check = findViewById(R.id.supply_check);
        none_check = findViewById(R.id.none_check);
        autoLogin = findViewById(R.id.autoLogin);

        //입력된 아이디로 서버에서 조회한 뒤 PW 맞추어보고 틀리면 빠꾸 맞으면 로그인

        Intent intent = getIntent();
        mContext = getApplicationContext();

        etId = ID;

        /*구글 로그인*/
        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.googleBtn:
                        Log.d("@@@@", "@@@@");
                        signIn();
                        break;
                }
            }
        });

        /*네이버로그인*/
        naverLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOAuthLoginModule = OAuthLogin.getInstance();
                /*mOAuthLoginModule.init(
                        mContext
                        *//*, getString(R.string.naver_client_id)
                        , getString(R.string.naver_client_secret)
                        , getString(R.string.naver_client_name)*//*
                        //, OAUTH_CALLBACK_INTENT
                        // SDK 4.1.4 버전부터는 OAUTH_CALLBACK_INTENT변수를 사용하지 않습니다.
                );*/
                mOAuthLoginModule.init(
                        mContext
                        , OAUTH_CLIENT_ID
                        , OAUTH_CLIENT_SECRET
                        , OAUTH_CLIENT_NAME
                );

                @SuppressLint("HandlerLeak")
                OAuthLoginHandler mOAuthLoginHandler = new OAuthLoginHandler() {
                    @Override
                    public void run(boolean success) {
                        if (success) {
                            String accessToken = mOAuthLoginModule.getAccessToken(mContext);
                            String refreshToken = mOAuthLoginModule.getRefreshToken(mContext);
                            long expiresAt = mOAuthLoginModule.getExpiresAt(mContext);
                            String tokenType = mOAuthLoginModule.getTokenType(mContext);

                            Log.i("LoginData", "accessToken : " + accessToken);
                            Log.i("LoginData", "refreshToken : " + refreshToken);
                            Log.i("LoginData", "expiresAt : " + expiresAt);
                            Log.i("LoginData", "tokenType : " + tokenType);

                        } else {
                            String errorCode = mOAuthLoginModule
                                    .getLastErrorCode(mContext).getCode();
                            String errorDesc = mOAuthLoginModule.getLastErrorDesc(mContext);
                            Toast.makeText(mContext, "errorCode:" + errorCode
                                    + ", errorDesc:" + errorDesc, Toast.LENGTH_SHORT).show();

                        }
                    }

                    ;
                };

                mOAuthLoginModule.startOauthLoginActivity(Login.this, mOAuthLoginHandler);
            }

        });

/*        *//*카카오 로그인*//*
        kakaoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             세션 콜백 구현
                ISessionCallback sessionCallback = new ISessionCallback() {
                    @Override
                    public void onSessionOpened() {
                        Log.i("KAKAO_SESSION", "로그인 성공");
                    }

                    @Override
                    public void onSessionOpenFailed(KakaoException exception) {
                        Log.e("KAKAO_SESSION", "로그인 실패", exception);
                    }
                };
                // 세션 콜백 등록
                Session.getCurrentSession().addCallback(sessionCallback);
*//*
            }
        });*/

        fjoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);
            }
        });


        fid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Find_ID_Pass.class);
                startActivity(intent);
            }
        });

        //로그인버튼 눌렀을 때 서버에서 아이디 비번 맞는지 확인
        shareLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = etId.getText().toString();
                userPW = Password.getText().toString();
                Log.d("asd", userID);

                //JSONObject를 StringRequest 객체를 통해 받아옴옴
                Response.Listener rListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONObject jResponse = new JSONObject(response);
                            boolean success = jResponse.getBoolean("success");
                            Log.d("asd", "아이디" + jResponse.getString("userID"));
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                AlertDialog dialog = builder.setMessage("아이디 또는 비밀번호를 확인해주세요.")
                                        .setNegativeButton("확인", null).create();
                                dialog.show();
                            } else {
                                Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                                intent.putExtra(IntentName.ID, jResponse.getString("userID"));
                                //공급업자 로그인인지 외식업자 로그인인지 판별
                                if (loginTypeRG.getCheckedRadioButtonId() == R.id.rest_check)
                                    intent.putExtra(IntentName.PROFILE, IntentName.CODE_RES);
                                else intent.putExtra(IntentName.PROFILE, IntentName.CODE_SUP);
                                startActivity(intent);
                            }

                        } catch (Exception e) {
                            Log.d("asd", e.toString());
                        }
                    }
                };

                if(loginTypeRG.getCheckedRadioButtonId() == R.id.supply_check) {
                    ValidateRequest validateRequest = new ValidateRequest(userID, userPW, rListener); //Request 처리 클래스
                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    queue.add(validateRequest);
                    //데이터 전송에 사용할 Volley 큐 생성 및 Request 객체 추가
                }else if(loginTypeRG.getCheckedRadioButtonId() == R.id.rest_check) {
                    ValidateRequest_Res validateRequest_Res = new ValidateRequest_Res(userID, userPW, rListener); //Request 처리 클래스
                    RequestQueue queue = Volley.newRequestQueue(Login.this);
                    queue.add(validateRequest_Res);
                }
                else { //비회원 로그인 추가
                    Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                    startActivity(intent);
                }

            }
        });

    }

    /*getSignInIntent 메서드로 로그인 의도 만들고 의도 사용하여 로그인 버튼 탭 처리*/
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    /*사용자가 로그인한 후 활동 방법에서 사용자에 대한 GoogleSignInAccount개체 얻음*/
    /*개체에는 사용자의 이름과 같이 로그인한 사용자 저보 포함되어 있음. GoogleSignInAccount*/
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data); //구글

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } //구글

        /*카카오*/
        // 카카오톡|스토리 간편로그인 실행 결과를 받아서 SDK로 전달
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data)) {
            return;
        }

        super.onActivityResult(requestCode, resultCode, data); //카카오

    }

    /*getEmail, getId를사용하여 사용자의 Google ID(클라이언트 측 사용용)와 getIdToken을 */
    /*사용하는 사용자의 이메일 주소와 getIdToken을사용하는 사용자를 위한 ID 토큰을 얻을 수도 있음*/
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            String googleEmail = account.getEmail(); //로그인한 사용자의 이메일 주소 반환
            String googleId = account.getId(); //Google 계정에 대한 고유 ID 반환
            String googleName = account.getDisplayName(); //서명된 사용자의 표시 이름 반환
            Log.d("Email", googleEmail);
            Log.d("Id", googleId);
            Log.d("Name", googleName);
            /*// Signed in successfully, show authenticated UI.
            updateUI(account);*/
        } catch (ApiException e) {
           /* // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);*/
        }
    }

    /*해시값가져오기위한 코드*/
    private void getHashKey() {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageInfo == null)
            Log.e("KeyHash", "KeyHash:null");

        for (Signature signature : packageInfo.signatures) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            } catch (NoSuchAlgorithmException e) {
                Log.e("KeyHash", "Unable to get MessageDigest. signature=" + signature, e);
            }
        }
    }

    /* 카카오 로그인 */
    private ISessionCallback sessionCallback = new ISessionCallback() {
        @Override
        public void onSessionOpened() {
            Log.i("KAKAO_SESSION", "로그인 성공");
        }

        @Override
        public void onSessionOpenFailed(KakaoException exception) {
            Log.e("KAKAO_SESSION", "로그인 실패", exception);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // 세션 콜백 삭제
        Session.getCurrentSession().removeCallback(sessionCallback);
    }

}
