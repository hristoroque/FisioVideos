package com.example.fisiovideos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;

import de.hdodenhof.circleimageview.CircleImageView;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 123;
    private static final String PROVEEDOR_DESCONOCIDO = "Proveedor Desconocido";
    private static final String PASSWORD_FIREBASE = "firebase";
    private static final String GOOGLE = "google.com";

    CircleImageView imgPhotoProfile;
    TextView tvUserName;
    TextView tvEmail;
    TextView tvProvider;

    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        createComponents();

        mFirebaseAuth = FirebaseAuth.getInstance();

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    onSetDataUser(user.getDisplayName(), user.getEmail(), user.getProviderData().get(0) != null ?
                            user.getProviderData().get(0).getProviderId() : PROVEEDOR_DESCONOCIDO);

                    RequestOptions options = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .centerCrop();

                    Glide.with(LoginActivity.this).load(user.getPhotoUrl()).apply(options).into(imgPhotoProfile);
                } else {
                    onSignerOutCleanup();
                    AuthUI.IdpConfig googleIdp = new AuthUI.IdpConfig.GoogleBuilder()
                            .build();
                    startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
                            .setIsSmartLockEnabled(false).setTosUrl("http://databaseremote.esy.es/RegisterLite/html/privacidad.html")
                            .setAvailableProviders(Arrays.asList(new AuthUI.IdpConfig.EmailBuilder().build(), googleIdp))
                            .build(), RC_SIGN_IN);

                }
            }
        };
    }

    private void createComponents() {
        imgPhotoProfile = (CircleImageView)findViewById(R.id.imgPhotoProfile);
        tvUserName = (TextView)findViewById(R.id.tvUserName);
        tvEmail = (TextView)findViewById(R.id.tvEmail);
        tvProvider = (TextView)findViewById(R.id.tvProvider);
    }

    private void onSignerOutCleanup() {
        onSetDataUser("","","");
    }

    private void onSetDataUser(String userName, String email, String provider) {
        tvUserName.setText(userName);
        tvEmail.setText(email);

        int drawableRes;
        switch (provider){
            case GOOGLE:
                drawableRes = R.drawable.ic_google_plus;
                break;
            case PASSWORD_FIREBASE:
                drawableRes = R.drawable.ic_google;
                //drawableRes = R.drawable.ic_firebase;
                provider = GOOGLE;
                break;
            default:
               drawableRes = R.drawable.ic_block_helper;
               provider = PROVEEDOR_DESCONOCIDO;
               break;
        }
        tvProvider.setCompoundDrawablesRelativeWithIntrinsicBounds(drawableRes, 0,0,0);
        tvProvider.setText(provider);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Bienvenido...", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Algo fallo, intente de nuevo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_sign_out:
                AuthUI.getInstance().signOut(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void goToVideoListView(View view) {
        Intent intent = new Intent(this,VideoListView.class);
        startActivity(intent);
    }
}
