package com.golflog.smartgolfbuckle;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.nfc.NfcAdapter;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.nfc.tech.NfcA;
import android.nfc.tech.NfcB;
import android.nfc.tech.NfcF;
import android.nfc.tech.NfcV;
import android.nfc.tech.TagTechnology;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.golflog.smartgolfbuckle.databinding.ActivityTagRecognitionBinding;

public class TagRecognitionActivity extends AppCompatActivity {

    private final String[][] techList = new String[][] {
      new String[] {
              NfcA.class.getName(),
              NfcB.class.getName(),
              NfcF.class.getName(),
              NfcV.class.getName(),
              NdefFormatable.class.getName(),
              TagTechnology.class.getName(),
              IsoDep.class.getName(),
              MifareClassic.class.getName(),
              MifareClassic.class.getName(),
              Ndef.class.getName()
      }
    };

    ActivityTagRecognitionBinding binding;
    Animation nfcAnimation;
    int tagPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_tag_recognition);

        nfcAnimation = AnimationUtils.loadAnimation(this, R.anim.nfc_notification);
        binding.tvNfcNotification.startAnimation(nfcAnimation);

        Intent intent = getIntent();
        tagPosition = intent.getIntExtra("TAG_POSITION", -1);
        if (tagPosition == -1)
            Toast.makeText(getApplicationContext(), "Intent Error", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onResume() {
        super.onResume();

        PendingIntent pendingIntent= PendingIntent.getActivity(this, 0,
                new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter filter = new IntentFilter();
        filter.addAction(NfcAdapter.ACTION_TAG_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_NDEF_DISCOVERED);
        filter.addAction(NfcAdapter.ACTION_TECH_DISCOVERED);
        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, new IntentFilter[]{filter}, this.techList);

    }

    @Override
    protected void onNewIntent(Intent intent) {
       if(intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
           String clubTag = ConvertToHex(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
           SharedPreference.setClubTagByPosition(clubTag, tagPosition);
           Intent retIntent = new Intent(this, ClubSettingActivity.class);
           retIntent.putExtra("CHANGED_POSITION", tagPosition);
           startActivity(retIntent);
           finish();
       }
    }

    private String ConvertToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for(byte b : bytes)
            sb.append(String.format("%02x", b&0xff));
        return sb.toString();
    }
}
