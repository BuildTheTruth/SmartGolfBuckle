package com.golflog.smartgolfbuckle;

import android.app.ProgressDialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.golflog.smartgolfbuckle.databinding.ActivityLoginBinding;
import com.golflog.smartgolfbuckle.vo.User;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    User mUser;
    ProgressDialog processDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.btnLogin.setOnClickListener(new LoginButtonClickListener());
        binding.btnRegister.setOnClickListener(new RegisterButtonClickListener());
        binding.btnFindPassword.setOnClickListener(new FIndPasswordButtonClickListener());

        SaveSharedPreference.setLoggedUser(mUser);

    }

    private class LoginButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new  Intent(getApplicationContext(), ClubSettingActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private class RegisterButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    private class FIndPasswordButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }
}
