package com.gcfhfbj.casadocodigo.casadocodigo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.gcfhfbj.casadocodigo.casadocodigo.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android7281 on 06/09/17.
 */

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_email)
    EditText email;

    @BindView(R.id.login_senha)
    EditText senha;

    private static final  String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    private boolean flagUsuarioLogado = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();

                if (user != null && !flagUsuarioLogado) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());

                    flagUsuarioLogado = true;
                    vaiParaMain();
                    finish();
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
            }
        };

        mAuth.addAuthStateListener(mAuthListener);

        /*mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d(TAG, "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(EmailPasswordActivity.this, R.string.auth_failed,
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });*/
    }

    private void vaiParaMain() {
        Intent vaiParaMain = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(vaiParaMain);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @OnClick(R.id.login_logar)
    public void logar() {
        String email = this.email.getText().toString().trim();
        String senha = this.senha.getText().toString().trim();

        if(!email.isEmpty() || !senha.isEmpty()) {
            mAuth.signInWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "signInWithEmail:onComplete:" + task.isSuccessful());

                            if (!task.isSuccessful()) {
                                Log.w(TAG, "signInWithEmail", task.getException());
                                Snackbar.make(LoginActivity.this.email, "Acesso não autorizado, verifique suas informações", Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Snackbar.make(this.senha, "Por favor complete todos os campos", Snackbar.LENGTH_SHORT).show();

        }
    }

    @OnClick(R.id.login_novo)
    public void novoUsuario() {
        final String email = this.email.getText().toString();
        String senha = this.senha.getText().toString();

        if(!email.isEmpty() || !senha.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Snackbar.make(LoginActivity.this.email, "Acesso não autorizado, verifique suas informações", Snackbar.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Snackbar.make(LoginActivity.this.senha, "Por favor complete todos os campos", Snackbar.LENGTH_SHORT).show();
        }
    }
}
