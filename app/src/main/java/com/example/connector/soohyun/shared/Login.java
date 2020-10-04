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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.connector.MainActivity;
import com.example.connector.R;
import com.example.connector.doyeon.activity.MainPageActivity;
import com.example.connector.soohyun.restaurantpage.EditRequest;
import com.example.connector.soohyun.restaurantpage.MyPage;
import com.example.connector.soohyun.restaurantpage.NowRequest;
import com.example.connector.soohyun.restaurantpage.RestaurantProfileActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.kakao.sdk.auth.LoginClient;
import com.nhn.android.naverlogin.OAuthLogin;
import com.nhn.android.naverlogin.OAuthLoginHandler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {

    public static final int RC_SIGN_IN = 1; //구글 API
    private GoogleSignInClient mGoogleSignInClient; //구글 API

    ImageView login_logoImg;
    EditText ID, Password;
    Button login1Btn, naverLogin, fjoin, fid, shareLoginBtn;
    SignInButton googleBtn;//구글 로그인 버튼

    OAuthLogin mOAuthLoginModule;//네이버
    Context mContext; //네이버

   //네이버Client
    private static String OAUTH_CLIENT_ID = "uMzH56lm9EaQQmvV4jJm" ;
    private static String OAUTH_CLIENT_SECRET = "c0mTBFEUDa" ;
    private static String OAUTH_CLIENT_NAME = "고래처";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        getHashKey(); //해시값가져오기

        /*구글API*/
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso); //구글API

        login_logoImg = findViewById(R.id.login_logoImg);
        ID = findViewById(R.id.ID);
        Password = findViewById(R.id.Password);
        login1Btn = findViewById(R.id.login1Btn);
        naverLogin = findViewById(R.id.naverLogin);
        fjoin = findViewById(R.id.fjoin);
        fid = findViewById(R.id.fid);
        shareLoginBtn = findViewById(R.id.ShareLoginBtn);
        googleBtn = findViewById(R.id.googleBtn);

        Intent intent = getIntent();

        /*구글API*/
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
                        ,OAUTH_CLIENT_ID
                        ,OAUTH_CLIENT_SECRET
                        ,OAUTH_CLIENT_NAME
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

        shareLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(intent);
            }
        });

        login1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(intent);
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
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    /*getEmail, getId를사용하여 사용자의 Google ID(클라이언트 측 사용용)와 getIdToken을 */
    /*사용하는 사용자의 이메일 주소와 getIdToken을사용하는 사용자를 위한 ID 토큰을 얻을 수도 있음*/
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            String googleEmail = account.getEmail(); //로그인한 사용자의 이메일 주소 반환
            String googleId = account.getId(); //Google 계정에 대한 고유 ID 반환
            String googleName =  account.getDisplayName(); //서명된 사용자의 표시 이름 반환
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

}
