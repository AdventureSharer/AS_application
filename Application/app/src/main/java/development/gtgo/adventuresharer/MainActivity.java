package development.gtgo.adventuresharer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import development.gtgo.adventuresharer.fragments.MenuFragment;
import development.gtgo.adventuresharer.fragments.LoginFragment;
import development.gtgo.adventuresharer.fragments.SignUpFragment;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginDataPass, SignUpFragment.OnSignUpDataPass {

    String loginEmail;
    String loginPassword;

    String signUpEmail;
    String signUpPassword;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private String TAG = "1234";

    /**
     *  Takes in an email and password from the login fragment and authenticates it again the information
     *  held on the database side
     *
     * @param email
     * @param password
     */
    @Override
    public void OnLoginDataPasser(String email, String password){

        loginEmail = email;
        loginPassword = password;

        if(loginEmail != null && loginPassword != null){
            // FireBase login process
            mAuth.signInWithEmailAndPassword(loginEmail, loginPassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success
                                Log.d(TAG, "signInWithEmail:success");
                                Toast.makeText(MainActivity.this, "Logged in!",
                                        Toast.LENGTH_SHORT).show();
                            } else {
                                // If sign in fails
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }

                    });
        }
    }

    /**
     * This function takes in an email and password from the Sign Up Fragment and creates
     * and account based on that information.
     *
     * @param email
     * @param password
     */
    @Override
    public void OnSignUpDataPasser(String email, String password){
        signUpEmail = email;
        signUpPassword = password;
        if(signUpEmail != null && signUpPassword != null){
            // FireBase Sign Up process
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(MainActivity.this, "Account creation failed! Please try again..",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        // Creates an auth listener to ensure that the user is still signed in
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Fragment frag = new MenuFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction()
                            .replace(R.id.content_test, frag)
                            .commit();

                } else {
                    // User is not signed in
                    Fragment frag = new LoginFragment();
                    FragmentManager manager = getSupportFragmentManager();
                    manager.beginTransaction()
                            .replace(R.id.content_test, frag)
                            .commit();
                }

            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Add the Auth listener when the activity is in focus
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Removes the Auth listener when the activity is out of focus
        mAuth.removeAuthStateListener(mAuthStateListener);
    }
}
