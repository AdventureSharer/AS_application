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

public class SignUpFragment extends Fragment {

    private EditText signUpEmail;
    private EditText signUpPassword;
    private EditText signUpPasswordConfirm;
    private TextInputLayout emailTIL;
    private TextInputLayout passwordTIL;
    private TextInputLayout confirmPasswordTIL;

    private String email;
    private String password;
    private String confirmPassword;

    /** This interface is used to send the Sign Up credentials to the MainActivity
     *  to handle the authentication and signing up process
     */
    OnSignUpDataPass dataPasser;
    public interface OnSignUpDataPass {
        void OnSignUpDataPasser(String email, String password);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Attaches the data passer for the MainActivity to be able to access it
        try{
            dataPasser = (SignUpFragment.OnSignUpDataPass) context;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        // Instantiates different fields
        // Uses view object to locate ID
        signUpEmail = view.findViewById(R.id.sign_up_email);
        signUpPassword = view.findViewById(R.id.sign_up_password);
        signUpPasswordConfirm = view.findViewById(R.id.sign_up_confirm_password);
        emailTIL = view.findViewById(R.id.sign_up_email_til);
        passwordTIL = view.findViewById(R.id.sign_up_password_til);
        confirmPasswordTIL = view.findViewById(R.id.sign_up_confirm_password_til);


        final Button signUpButton = view.findViewById(R.id.button_sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                // Gather fields
                email = signUpEmail.getText().toString();
                password = signUpPassword.getText().toString();
                confirmPassword = signUpPasswordConfirm.getText().toString();

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
                if(TextUtils.isEmpty(confirmPassword)){
                    confirmPasswordTIL.setError("Please enter your password!");
                } else {
                    confirmPasswordTIL.setError(null);
                }

                // Basic empty field checker
                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword)){
                    if(password.equals(confirmPassword)){
                        dataPasser.OnSignUpDataPasser(email, password);
                    } else {
                        Toast.makeText(getContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Takes user back to login page
        final Button loginLinkButton = view.findViewById(R.id.button_login_link);
        loginLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment frag = new LoginFragment();
                FragmentManager manager = getFragmentManager();
                if(manager != null) {
                    manager.beginTransaction()
                            .replace(R.id.content_test, frag)
                            .addToBackStack(null)
                            .commit();
                }
            }
        });

        return view;
    }
}
