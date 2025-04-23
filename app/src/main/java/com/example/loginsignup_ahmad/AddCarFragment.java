package com.example.loginsignup_ahmad;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddCarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddCarFragment extends Fragment {

    ImageView img;
    private String imageStr;
    private EditText etname,ethorse_power,etOwners,etColor,etCar_num,
            etManufacturer,etYear,etCar_model,etTest,etkilometre,
            etEngine_capacity,etGear_shifting_model,etPrice, etPhone;
    private Button btnAddCar;
    private FireBaseServices fbs;
    private Utils utils;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public AddCarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddRestaurantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddCarFragment newInstance(String param1, String param2) {
        AddCarFragment fragment = new AddCarFragment();
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
        return inflater.inflate(R.layout.fragment_add_car, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        connectComponents();

    }

    private void connectComponents() {
        fbs = FireBaseServices.getInstance();
        utils = Utils.getInstance();
        etname=getView().findViewById(R.id.etNameCarAddFragment);
        ethorse_power=getView().findViewById(R.id.etHorsePowerAddFragment);
        etOwners=getView().findViewById(R.id.etOwnersAddFragment);
        etPhone=getView().findViewById(R.id.etPhoneAddFragment);
        etCar_num=getView().findViewById(R.id.etCarNumberAddFragment);
        etManufacturer=getView().findViewById(R.id.etManufacturerAddFragment);
        //etYear=getView().findViewById(R.id.etYearAddFragment);
        etCar_model=getView().findViewById(R.id.etCarModelAddFragment);
        etTest=getView().findViewById(R.id.etTestAddFragment);
        etkilometre=getView().findViewById(R.id.etKilometreAddFragment);
        etEngine_capacity=getView().findViewById(R.id.etEngineCapacityAddFragment);
        etGear_shifting_model=getView().findViewById(R.id.etGearShiftAddFragment);
        etPrice=getView().findViewById(R.id.etPriceAddFragment);
        btnAddCar = getView().findViewById(R.id.btnAddCarFragment);
        img = getView().findViewById(R.id.ivCarAddCarFragment);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }

            private void openGallery() {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, 123);
            }

        });
        btnAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utils = Utils.getInstance();
                String phone = etPhone.getText().toString();
                String nameCar=etname.getText().toString();
                String horse_power=ethorse_power.getText().toString();
                String owners=etOwners.getText().toString();
                String car_num=etCar_num.getText().toString();
                String manufacturer=etManufacturer.getText().toString();
                String Car_model=etCar_model.getText().toString();
                String test=etTest.getText().toString();
                String kilometre=etkilometre.getText().toString();
                String Engine_capacity=etEngine_capacity.getText().toString();
                String Gear_shifting_model="DSG";
                String price=etPrice.getText().toString();
                // data validation
                if ((nameCar.trim().isEmpty()                                        ||
                        horse_power.trim().isEmpty()                                ||
                        owners.trim().isEmpty()                                     ||
                        car_num.trim().isEmpty()                                    ||
                        manufacturer.trim().isEmpty()                               ||
                        Car_model.trim().isEmpty()                                  ||
                        test.trim().isEmpty()                                       ||
                        kilometre.trim().isEmpty()                                  ||
                        Engine_capacity.trim().isEmpty()                            ||
                        Gear_shifting_model.trim().isEmpty()                        ||
                        price.trim().isEmpty())){
                    Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_LONG).show();
                    return;
                }

                // add data to firestore
                Car car;
                if (fbs.getSelectedImageURL() == null)
                    car = new Car( nameCar, horse_power, owners, phone,
                            car_num, manufacturer, Car_model,
                            test, kilometre, Engine_capacity, Gear_shifting_model, price, fbs.getSelectedImageURL().toString());
                else
                    car = new Car(nameCar, horse_power, owners, phone,
                            car_num, manufacturer, Car_model,
                            test, kilometre, Engine_capacity, Gear_shifting_model, price, fbs.getSelectedImageURL().toString());


                fbs.getFire().collection("Cars").add(car).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(getActivity(), "Successfully added your Car!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("Failure AddCar: ", e.getMessage());
                    }
                });


            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123 && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            img.setImageURI(selectedImageUri);
            Utils.uploadImage(getActivity(), selectedImageUri);
        }
    }
}

