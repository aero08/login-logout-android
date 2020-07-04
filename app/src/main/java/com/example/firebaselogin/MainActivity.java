package com.example.firebaselogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button button;
    private FirebaseAuth mAuth;

    //make sure your email password are not null


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(this, "Already in", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        button = findViewById(R.id.register);


        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegister();
            }
        });


    }

    public void onRegister() {
        String myemail = email.getText().toString();
        String mypass = password.getText().toString();


        mAuth.createUserWithEmailAndPassword(myemail, mypass)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");
                            // FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this, "Sucess", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Failure", Toast.LENGTH_SHORT).show();


                        }

                        // ...
                    }
                });

    }

    public void onLogin(View view) {
        String myemail = email.getText().toString();
        String mypass = password.getText().toString();

        mAuth.signInWithEmailAndPassword(myemail, mypass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.i("tag", "login successful with email and password");
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid().toString();
                    Toast.makeText(MainActivity.this, "Auth successful", Toast.LENGTH_SHORT).show();
                    Log.i("tag", "user:" + user.toString());
                    Log.i("tag", "userID:" + userID);


                } else {
                    Log.i("tag", "login failed with email and password");
                    Toast.makeText(MainActivity.this, "Auth failed", Toast.LENGTH_SHORT).show();


                }
            }
        });

    }
    public void logout(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MainActivity.this,"user signout",Toast.LENGTH_SHORT).show();
    }

}