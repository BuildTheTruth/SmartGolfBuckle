package com.golflog.smartgolfbuckle;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.golflog.smartgolfbuckle.databinding.ActivityRegisterBinding;
import com.golflog.smartgolfbuckle.vo.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    DatabaseReference mDatabaseReference;
    ProgressDialog progressDialog;

    String[] ageGroups = {"10대", "20대", "30대", "40대", "50대", "60대", "70대", "80대"};
    String[] avgHitNumberGroups = {"70대", "80대", "90대", "100대"};
    String[] gender = {"남성", "여성"};
    boolean isValidPhoneNumber, isValidPassword;
    ArrayAdapter<String> ageGroupSpinnerAdapter, genderSpinnerAdapter, avgHitNumberSpinnerAdapter;
    User newUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("users");

        ageGroupSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ageGroups);
        ageGroupSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.ageGroupSpinner.setAdapter(ageGroupSpinnerAdapter);
        binding.ageGroupSpinner.setSelection(3); // default = 40대

        genderSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, gender);
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.genderSpinner.setAdapter(genderSpinnerAdapter);

        avgHitNumberSpinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, avgHitNumberGroups);
        avgHitNumberSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.avgHitNumberSpinner.setAdapter(avgHitNumberSpinnerAdapter);

        binding.checkPhoneNumberButton.setOnClickListener(new CheckPhoneNumberButtonClickListener());
        binding.submitButton.setOnClickListener(new SubmitButtonClickListener());
        binding.cancelButton.setOnClickListener(new CancelButtonClickListener());

    }

    private class CheckPhoneNumberButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Iterator<DataSnapshot> child = dataSnapshot.getChildren().iterator();
                    while(child.hasNext()) {
                        String inputPhoneNumber = binding.phoneNumberEditText.getText().toString();
                        if(inputPhoneNumber.equals(child.next().getKey())) {
                            Toast.makeText(getApplicationContext(), "이미 등록된 휴대폰 번호입니다.", Toast.LENGTH_LONG).show();
                            isValidPhoneNumber=false;
                            break;
                        } else {
                            Toast.makeText(getApplicationContext(), "등록 가능한 휴대펀 번호입니다.", Toast.LENGTH_LONG).show();
                            isValidPhoneNumber=true;
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private class SubmitButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }

    private class CancelButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            finish();
        }
    }

}
