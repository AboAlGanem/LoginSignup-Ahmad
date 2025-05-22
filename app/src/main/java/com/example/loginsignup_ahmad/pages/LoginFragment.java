package com.example.loginsignup_ahmad.pages;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginsignup_ahmad.Data.FireBaseServices;
import com.example.loginsignup_ahmad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginFragment extends Fragment {

    private EditText etEmail, etPassword;
    private Button btnSignin;
    private TextView tvForgetPassword, tvSignup;
    private ImageView ivLogo;
    private FireBaseServices fbs;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        fbs = FireBaseServices.getInstance();

        etEmail = getView().findViewById(R.id.etEmailLoginFragment);
        etPassword = getView().findViewById(R.id.etPasswordLoginFragment);
        btnSignin = getView().findViewById(R.id.btnSigninLoginFragment);
        tvForgetPassword = getView().findViewById(R.id.tvForgetPasswordLoginFramgment);
        tvSignup = getView().findViewById(R.id.tvSingupLoginFragment);
        ivLogo = getView().findViewById(R.id.ivLogoLoginFragment);

        ProgressDialog dialog = new ProgressDialog(requireContext());
        dialog.setTitle("Please wait...");

        tvSignup.setOnClickListener(view -> goToSignUpFragment());

        tvForgetPassword.setOnClickListener(view -> goToForgotPasswordFragment());

        btnSignin.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(getActivity(), "Email or password is empty!", Toast.LENGTH_LONG).show();
                return;
            }

            dialog.show();

            fbs.getAuth().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            dialog.dismiss();
                            if (task.isSuccessful()) {
                                goToHomeFragment();
                            } else {
                                Toast.makeText(getActivity(), "Login failed!", Toast.LENGTH_LONG).show();
                                Log.e("LoginError", "Login failed: " + task.getException());
                            }
                        }
                    });
        });
    }

    private void goToSignUpFragment() {
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutsMain, new SignupFragment());
        ft.commit();
    }

    private void goToForgotPasswordFragment() {
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutsMain, new Forgot_passwordFragment());
        ft.commit();
    }

    private void goToHomeFragment() {
        FragmentTransaction ft = requireActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutsMain, new HomeFragment());
        ft.commit();
    }
}
