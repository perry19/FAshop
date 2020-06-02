package com.example.fashop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegistrationActivity extends AppCompatActivity {
    private ImageView logo, joinus;
    private AutoCompleteTextView username, email, password;
    private Button signup;
    private TextView signin;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    //private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        initializeGUI();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String inputName = username.getText().toString().trim();
                final String inputPw = password.getText().toString().trim();
                final String inputEmail = email.getText().toString().trim();

                if(validateInput(inputName, inputPw, inputEmail))
                    registerUser(inputName, inputPw, inputEmail);

            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
            }
        });
    }

    private void initializeGUI() {
        logo = findViewById(R.id.ivRegLogo);
        joinus = findViewById(R.id.ivJoinUs);
        username = findViewById(R.id.atvUsernameReg);
        email =  findViewById(R.id.atvEmailReg);
        password = findViewById(R.id.atvPasswordReg);
        signin = findViewById(R.id.tvRegisterTxt);
        signup = findViewById(R.id.btnSignUp);
        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
    }


    private void registerUser(final String inputName, final String inputPw, String inputEmail) {

        progressDialog.setMessage("Verifying...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(inputEmail,inputPw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    FirebaseUser user = firebaseAuth.getCurrentUser();
                    Toast.makeText(RegistrationActivity.this,"You've been registered successfully.",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                }
                else{
                    progressDialog.dismiss();
                    Toast.makeText(RegistrationActivity.this,"Email already exists.",Toast.LENGTH_SHORT).show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(RegistrationActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    private boolean validateInput(String inName, String inPw, String inEmail){

        if(inName.isEmpty()){
            username.setError("Username is empty.");
            return false;
        }
        if(inPw.isEmpty() && inPw.length()<6){
            password.setError("Password should not be empty and must contain at least 6 characters.");
            return false;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(inEmail).matches()){
            email.setError("Invalid Email");
            return false;
        }

        return true;
    }
}
