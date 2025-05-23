package com.example.loginsignup_ahmad.pages;

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

import com.example.loginsignup_ahmad.Data.FireBaseServices;
import com.example.loginsignup_ahmad.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {
    private EditText etEmail , etPassword , etConfirmPassword , etFullName;
    private Button btnSignUp;
    private FireBaseServices fbs;
    private TextView tvLogin;
    private ImageButton btnBack;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignupFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        fbs = FireBaseServices.getInstance();
        etEmail = getView().findViewById(R.id.etEmailSignUpFragment);
        etPassword = getView().findViewById(R.id.etPasswordSignUpFragment);
        etConfirmPassword = getView().findViewById(R.id.etConfirmPasswordSignUpFragment);
        btnSignUp = getView().findViewById(R.id.btnSignUpSignUpFragment);
        tvLogin = getView().findViewById(R.id.tvLoginSignUpFragment);
        etFullName = getView().findViewById(R.id.etFullNameSignUpFragment);
        btnBack = getView().findViewById(R.id.btnBackSignUpFragment);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginFragment();
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToLoginFragment();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                if (Email.trim().isEmpty() && password.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "some fields are empty !!", Toast.LENGTH_SHORT).show();
                    return;
                }
                fbs.getAuth().createUserWithEmailAndPassword(Email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(getActivity(), "Successfully signed up", Toast.LENGTH_SHORT).show();
                        goToHomeFragment();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
    private void goToLoginFragment(){
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutsMain, new LoginFragment());
        ft.commit();
    }
    private void goToHomeFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.FrameLayoutsMain, new HomeFragment());
        ft.commit();
    }

}

