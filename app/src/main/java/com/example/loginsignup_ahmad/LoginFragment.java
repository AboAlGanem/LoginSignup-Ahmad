package com.example.loginsignup_ahmad;

import static android.app.PendingIntent.getActivity;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {
    private EditText etEmail, etPassword;
    private Button btnSingin;
    private ImageButton btnBack;
    private FireBaseServices fbs;
    private TextView tvForgetPassword, tvSingup;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        fbs = FireBaseServices.getInstance();
        etEmail = getView().findViewById(R.id.etEmailLoginFragment);
        etPassword = getView().findViewById(R.id.etPasswordLoginFragment);
        btnSingin = getView().findViewById(R.id.btnSigninLoginFragment);
        tvForgetPassword = getView().findViewById(R.id.tvForgetPasswordLoginFramgment);
        tvSingup = getView().findViewById(R.id.tvSingupLoginFragment);
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setTitle("please wait!");

        tvSingup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSignUpFragment();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainFragment();
            }
        });
        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToForgot_password_Fragment();
            }
        });

        btnSingin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                String email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (email.trim().isEmpty() && password.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_LONG).show();
                    return;
                }
                fbs.getAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //TODO: decide what to do
                            //goToHomeFragment();
                            dialog.dismiss();
                        } else {
                            dialog.dismiss();
                            Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_LONG).show();
                            //TODO: decide what to do
                        }
                    }
                });
            }
        });
    }

    private void goToSignUpFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutsMain, new SignupFragment());
        ft.commit();
    }

    private void goToMainFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutsMain, new MainFragment());
        ft.commit();
    }

    private void goToForgot_password_Fragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutsMain, new Forgot_passwordFragment());
        ft.commit();
    }

    private void gotoAddCarFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutsMain, new AddCarFragment());
        ft.commit();
    }
}
