package com.example.loginsignup_ahmad.pages;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.loginsignup_ahmad.Data.Car;
import com.example.loginsignup_ahmad.Data.FireBaseServices;
import com.example.loginsignup_ahmad.R;
import com.example.loginsignup_ahmad.Utilities.Utils;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

public class AddCarFragment extends Fragment {

    private ImageView img;
    private EditText etname, ethorse_power, etOwners, etColor, etCar_num,
            etManufacturer, etYear, etCar_model, etTest, etkilometre,
            etEngine_capacity, etGear_shifting_model, etPrice, etPhone;
    private Button btnAddCar;
    private FireBaseServices fbs;
    private Utils utils;

    private ActivityResultLauncher<String> imagePickerLauncher;

    public AddCarFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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

        etname = getView().findViewById(R.id.etNameCarAddFragment);
        ethorse_power = getView().findViewById(R.id.etHorsePowerAddFragment);
        etOwners = getView().findViewById(R.id.etOwnersAddFragment);
        etPhone = getView().findViewById(R.id.etPhoneAddFragment);
        etCar_num = getView().findViewById(R.id.etCarNumberAddFragment);
        etManufacturer = getView().findViewById(R.id.etManufacturerAddFragment);
        etCar_model = getView().findViewById(R.id.etCarModelAddFragment);
        etTest = getView().findViewById(R.id.etTestAddFragment);
        etkilometre = getView().findViewById(R.id.etKilometreAddFragment);
        etEngine_capacity = getView().findViewById(R.id.etEngineCapacityAddFragment);
        etGear_shifting_model = getView().findViewById(R.id.etGearShiftAddFragment);
        etPrice = getView().findViewById(R.id.etPriceAddFragment);
        btnAddCar = getView().findViewById(R.id.btnAddCarFragment);
        img = getView().findViewById(R.id.ivCarAddCarFragment);

        // إعداد Launcher لاختيار صورة من المعرض
        imagePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                uri -> {
                    if (uri != null) {
                        img.setImageURI(uri);
                        Utils.uploadImage(getActivity(), uri);
                    }
                }
        );

        img.setOnClickListener(view -> {
            imagePickerLauncher.launch("image/*");
        });

        btnAddCar.setOnClickListener(view -> {
            String phone = etPhone.getText().toString().trim();
            String nameCar = etname.getText().toString().trim();
            String horse_power = ethorse_power.getText().toString().trim();
            String owners = etOwners.getText().toString().trim();
            String car_num = etCar_num.getText().toString().trim();
            String manufacturer = etManufacturer.getText().toString().trim();
            String car_model = etCar_model.getText().toString().trim();
            String test = etTest.getText().toString().trim();
            String kilometre = etkilometre.getText().toString().trim();
            String engine_capacity = etEngine_capacity.getText().toString().trim();
            String gear_shifting_model = etGear_shifting_model.getText().toString().trim();
            String price = etPrice.getText().toString().trim();

            if (nameCar.isEmpty() || horse_power.isEmpty() || owners.isEmpty() ||
                    car_num.isEmpty() || manufacturer.isEmpty() || car_model.isEmpty() ||
                    test.isEmpty() || kilometre.isEmpty() || engine_capacity.isEmpty() ||
                    gear_shifting_model.isEmpty() || price.isEmpty()) {

                Toast.makeText(getActivity(), "Some fields are empty!", Toast.LENGTH_LONG).show();
                return;
            }

            if (fbs.getSelectedImageURL() == null) {
                Toast.makeText(getActivity(), "Please select an image!", Toast.LENGTH_SHORT).show();
                return;
            }

            Car car = new Car(nameCar, horse_power, owners, phone,
                    car_num, manufacturer, car_model,
                    test, kilometre, engine_capacity,
                    gear_shifting_model, price,
                    fbs.getSelectedImageURL().toString());

            fbs.getFire().collection("Cars").add(car)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(getActivity(), "Successfully added your Car!", Toast.LENGTH_SHORT).show();
                    })
                    .addOnFailureListener(e -> {
                        Log.e("Failure AddCar", e.getMessage());
                        Toast.makeText(getActivity(), "Failed to add car.", Toast.LENGTH_LONG).show();
                    });
        });
    }
}
