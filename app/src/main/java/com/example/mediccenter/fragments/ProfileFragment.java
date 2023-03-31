package com.example.mediccenter.fragments;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mediccenter.R;
import com.example.mediccenter.models.CreateCardPaitentModel;
import com.example.mediccenter.models.ImageProfileModel;
import com.example.mediccenter.models.UpdateCardPatientModel;
import com.example.mediccenter.view_model.CreateCardPatientViewModel;
import com.example.mediccenter.view_model.ImageProfileViewModel;
import com.example.mediccenter.view_model.UpdateCardPatientViewModel;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment {

    CircleImageView imageViewProfile;

    ImageView icoImageView;

    String[] cameraPermissions;

    String[] storagePermissions;

    String encodedImage;

    String current = "";

    String ddmmyyyy = "DDMMYYYY";

    Calendar cal = Calendar.getInstance();


    EditText firstNameEditText, middleNameEditText, lastNameEditText, dateBirthEditText;

    Spinner genderEditText;

    UpdateCardPatientViewModel updateCardPatientViewModel;

    ImageProfileViewModel imageProfileViewModel;

    Button btnSaveProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        imageViewProfile = view.findViewById(R.id.imageViewProfile);
        icoImageView = view.findViewById(R.id.icoImageView);
        firstNameEditText = view.findViewById(R.id.firstNameEditText);
        middleNameEditText = view.findViewById(R.id.middleNameEditText);
        lastNameEditText = view.findViewById(R.id.lastNameEditText);
        dateBirthEditText = view.findViewById(R.id.dateBirthEditText);
        genderEditText = view.findViewById(R.id.genderEditText);
        btnSaveProfile = view.findViewById(R.id.btnSaveProfile);

        Bundle arguments = getActivity().getIntent().getExtras();

        if(arguments!=null){
            firstNameEditText.setText(arguments.getString("firstName"));
            middleNameEditText.setText(arguments.getString("middleName"));
            lastNameEditText.setText(arguments.getString("lastName"));
            dateBirthEditText.setText(arguments.getString("dateBirth"));
            genderEditText.setPrompt(arguments.getString("gender"));
        }

        installViewModel();
        installDateFormat();
        imageViewProfile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            pickImage.launch(intent);
        });

        btnSaveProfile.setOnClickListener(v ->
                updateCardPatient());
        return view;
    }

    private void updateCardPatient() {
        updateCardPatientViewModel.getUpdateCardPatientData().observe((LifecycleOwner) getContext(), user -> {
            if (user == null) {
                Toast.makeText(getContext(), "Неудалось обновить карту пациента...", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(), "Карта пациента успешно обновлена!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        UpdateCardPatientModel updateCardPatientModel = new UpdateCardPatientModel(
                firstNameEditText.getText().toString(), lastNameEditText.getText().toString(), middleNameEditText.getText().toString(),
                dateBirthEditText.getText().toString(), genderEditText.getSelectedItem().toString()
        );
        updateCardPatientViewModel.updateUser(updateCardPatientModel);
    }

    private void uploadImageProfile() {
        imageProfileViewModel.getImageProfileData().observe((LifecycleOwner) getContext(), imageProfileModel -> {
            if (imageProfileModel == null) {
                Toast.makeText(getContext(), "Картинка пациента не загружена...", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(), "Картинка пациента загружена!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        });

        ImageProfileModel imageProfileModel = new ImageProfileModel(imageViewProfile.getTransitionName());
        imageProfileViewModel.uploadImageProfile(imageProfileModel);
    }

    private void installViewModel() {
        updateCardPatientViewModel = new ViewModelProvider(this).get(UpdateCardPatientViewModel.class);
        imageProfileViewModel = new ViewModelProvider(this).get(ImageProfileViewModel.class);
    }

    private void installDateFormat() {
        dateBirthEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @SuppressLint("DefaultLocale")
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals(current)) {
                    String clean = s.toString().replaceAll("[^\\d.]|\\.", "");
                    String cleanC = current.replaceAll("[^\\d.]|\\.", "");

                    int cl = clean.length();
                    int sel = cl;
                    for (int i = 2; i <= cl && i < 6; i += 2) {
                        sel++;
                    }
                    if (clean.equals(cleanC)) sel--;

                    if (clean.length() < 8){
                        clean = clean + ddmmyyyy.substring(clean.length());
                    }else{
                        int day  = Integer.parseInt(clean.substring(0,2));
                        int mon  = Integer.parseInt(clean.substring(2,4));
                        int year = Integer.parseInt(clean.substring(4,8));

                        mon = mon < 1 ? 1 : Math.min(mon, 12);
                        cal.set(Calendar.MONTH, mon-1);
                        year = (year<1900)?1900: Math.min(year, 2100);
                        cal.set(Calendar.YEAR, year);

                        day = Math.min(day, cal.getActualMaximum(Calendar.DATE));
                        clean = String.format("%02d%02d%02d",day, mon, year);
                    }

                    clean = String.format("%s/%s/%s", clean.substring(0, 2),
                            clean.substring(2, 4),
                            clean.substring(4, 8));

                    sel = Math.max(sel, 0);
                    current = clean;
                    dateBirthEditText.setText(current);
                    dateBirthEditText.setSelection(Math.min(sel, current.length()));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private String encodeImage(Bitmap bitmap) {
        int previewWidth = 148;
        int previewHeight = bitmap.getHeight() * previewWidth / bitmap.getWidth();
        Bitmap previewBitmap = Bitmap.createScaledBitmap(bitmap, previewWidth, previewHeight, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        previewBitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] bytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);
    }

    private final ActivityResultLauncher<Intent> pickImage = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if(result.getResultCode() == RESULT_OK){
                    if (result.getData() != null){
                        Uri imageUri = result.getData().getData();
                        try {
                            InputStream inputStream = requireActivity().getContentResolver().openInputStream(imageUri);
                            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                            imageViewProfile.setImageBitmap(bitmap);
                            icoImageView.setVisibility(View.GONE);
                            encodedImage = encodeImage(bitmap);
                            uploadImageProfile();
                        }
                        catch(FileNotFoundException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
    );
}