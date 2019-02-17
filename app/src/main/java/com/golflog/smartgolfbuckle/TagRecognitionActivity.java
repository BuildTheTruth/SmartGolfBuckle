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
    private final String TAG = "TagRecognitionActivity";

    private final String[][] techList = new String[][]{
            new String[]{
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
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
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
        if (intent.getAction().equals(NfcAdapter.ACTION_TAG_DISCOVERED)) {
            String tagID = convertToHex(intent.getByteArrayExtra(NfcAdapter.EXTRA_ID));
            SaveSharedPreference.setClubTagByPosition(tagID, tagPosition);
            finish();
        }
    }

    private String convertToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b & 0xff));
        return sb.toString();
    }

/*    private NdefMessage createTagMessage(String msg) {
        NdefRecord[] records = new NdefRecord[1];
        records[0] = createTextRecord(msg, Locale.KOREAN, true);
        NdefMessage mNdefMessage = new NdefMessage(records);
        return mNdefMessage;
    }

    private NdefRecord createTextRecord(String msg, Locale locale, boolean encodeInUtf8) {
        byte[] langBytes = locale.getLanguage().getBytes(StandardCharsets.US_ASCII);
        Charset utfEncoding = encodeInUtf8 ? StandardCharsets.UTF_8 : Charset.forName("UTF-16");
        byte[] msgBytes = msg.getBytes(utfEncoding);
        int utfBit = encodeInUtf8 ? 0 : (1 << 7);
        char status = (char) (utfBit + langBytes.length);
        byte[] data = null;
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            outputStream.write(new byte[]{(byte) status});
            outputStream.write(langBytes);
            outputStream.write(msgBytes);
            data = outputStream.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], data);
    }

    private void showTagMessage(NdefMessage message) {
        List<ParsedRecord> records = NdefMessageParser.parse(message);
        final int size = records.size();

        for (int i = 0; i < size; i++) {
            ParsedRecord record = records.get(i);
            int recordType = record.getType();
            String recordStr = "";
            if (recordType == ParsedRecord.TYPE_TEXT) {
                recordStr = ((TextRecord) record).getText();
            }
            Toast.makeText(getApplicationContext(), recordStr, Toast.LENGTH_LONG).show();
            Log.d("recordStr", recordStr);
        }
    }

    private void readTagMessage(Parcelable[] rawMsgs) {
        if (rawMsgs == null) {
            Log.d(TAG, "NDEF is null");
            return;
        }
        Toast.makeText(getApplicationContext(), "메시지 존재", Toast.LENGTH_LONG).show();
        NdefMessage[] msgs = new NdefMessage[rawMsgs.length];
        for (int i = 0; i < rawMsgs.length; i++) {
            msgs[i] = (NdefMessage) rawMsgs[i];
            showTagMessage(msgs[i]);
        }
    }*/
}
