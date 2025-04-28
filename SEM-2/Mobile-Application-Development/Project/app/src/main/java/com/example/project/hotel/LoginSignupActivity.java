package com.example.project.hotel;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.project.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginSignupActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "GoogleSignInActivity";

    EditText email, password;
    Button signup, login, googleSignInButton;
    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_signup);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize Firebase components
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("MADL Practical 8 on Firebase Database");

        // Initialize views
        initializeViews();

        // Configure Google Sign In
        configureGoogleSignIn();

        // Set click listeners
        setupClickListeners();
    }

    private void initializeViews() {
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);
        googleSignInButton = findViewById(R.id.googleSignInButton);
        firebaseAuth = FirebaseAuth.getInstance();
        progressBar = findViewById(R.id.progressBar);
    }

    private void configureGoogleSignIn() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void setupClickListeners() {
        signup.setOnClickListener(v -> handleEmailSignUp());
        login.setOnClickListener(v -> handleEmailLogin());
        googleSignInButton.setOnClickListener(v -> signInWithGoogle());
    }

    private void handleEmailSignUp() {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            showToast("Please fill all fields");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        showToast("User Created Successfully!");
                        goToMainActivity();
                    } else {
                        handleSignUpError(task.getException());
                    }
                });
    }

    private void handleEmailLogin() {
        String userEmail = email.getText().toString().trim();
        String userPassword = password.getText().toString().trim();

        if (userEmail.isEmpty() || userPassword.isEmpty()) {
            showToast("Please fill all fields");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener(task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        showToast("Login Successful!");
                        goToMainActivity();
                    } else {
                        handleLoginError(task.getException());
                    }
                });
    }

    private void signInWithGoogle() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Log.w(TAG, "Google sign in failed", e);
                showToast("Google sign in failed: " + e.getStatusCode());
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        progressBar.setVisibility(View.VISIBLE);
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    progressBar.setVisibility(View.GONE);
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        Log.d(TAG, "signInWithCredential:success");
                        showToast("Google sign in successful");
                        goToMainActivity();
                    } else {
                        Log.w(TAG, "signInWithCredential:failure", task.getException());
                        showToast("Authentication failed");
                    }
                });
    }

    private void goToMainActivity() {
        Intent firebaseIntent = new Intent(this, HomeActivity.class);
        startActivity(firebaseIntent);
        finish();
    }

    private void handleSignUpError(Exception e) {
        if (e instanceof FirebaseAuthUserCollisionException) {
            showToast("Email already registered!");
        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
            showToast("Invalid Email Format!");
        } else {
            showToast("User Creation Failed: " + e.getMessage());
        }
    }

    private void handleLoginError(Exception e) {
        if (e instanceof FirebaseAuthInvalidUserException) {
            showToast("User Not Found!");
        } else if (e instanceof FirebaseAuthInvalidCredentialsException) {
            showToast("Invalid Password!");
        } else {
            showToast("Login Failed: " + e.getMessage());
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser != null) {
            goToMainActivity();
        }
    }
}