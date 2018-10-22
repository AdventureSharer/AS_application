package development.gtgo.adventuresharer.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import development.gtgo.adventuresharer.R;

// Placeholder login fragment
public class LoginFragment extends Fragment {

    // Fields used in the logging in sequence
    private EditText emailField;
    private EditText passwordField;
    private TextInputLayout emailTIL;
    private TextInputLayout passwordTIL;

    private String email;
    private String password;

    /** This interface is used to send the Log In credentials to the MainActivity
     *  to handle the authentication and logging in process
     */
    OnLoginDataPass dataPasser;
    public interface OnLoginDataPass {
        void OnLoginDataPasser(String email, String password);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Attaches the data passer for the MainActivity to be able to access it
        try{
            dataPasser = (OnLoginDataPass) context;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // Creates View object based on the fragment_login layout file
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // Instantiates different fields
        // Uses view object to locate ID
        emailField = view.findViewById(R.id.login_email_field);
        passwordField = view.findViewById(R.id.login_password);
        emailTIL = view.findViewById(R.id.login_email_til);
        passwordTIL = view.findViewById(R.id.login_password_til);


        // Sends data grabbed from the login fields to the Main Activity
        final Button loginButton = view.findViewById(R.id.button_login);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                email = emailField.getText().toString();
                password = passwordField.getText().toString();

                // Basic empty field checker that changed the TIL error section
                if(TextUtils.isEmpty(email)) {
                    emailTIL.setError("Please enter your email!");
                } else {
                    emailTIL.setError(null);
                }

                if(TextUtils.isEmpty(password)){
                    passwordTIL.setError("Please enter your password!");
                } else {
                    passwordTIL.setError(null);
                }

                // Basic empty field checker
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
                    dataPasser.OnLoginDataPasser(email, password);
                } else {
                    Toast.makeText(getContext(), "Please fill all fields!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Takes user to the sign up fragment
        final Button signUpButton = view.findViewById(R.id.button_sign_up_link);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Fragment frag = new SignUpFragment();
                FragmentManager manager = getFragmentManager();
                if(manager != null) {
                    manager.beginTransaction()
                            .replace(R.id.content_test, frag)
                            .commit();
                }
            }
        });

        // Returns View object
        return view;
    }

}
