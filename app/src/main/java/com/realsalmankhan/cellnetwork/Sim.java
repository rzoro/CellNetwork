package com.realsalmankhan.cellnetwork;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.widget.TextView;

public class Sim extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sim);
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            return;
        }
try {

    String phoneNumber = tm.getLine1Number();
    String subscriberId = tm.getSubscriberId();
    String simOperatorName = tm.getSimOperatorName();
    String simcountry = tm.getSimCountryIso();
    TextView textView = findViewById(R.id.text);
    TextView sub = findViewById(R.id.sub);
    TextView opr = findViewById(R.id.opr);
    TextView cn = findViewById(R.id.cn);
    textView.setText("Phone: " + phoneNumber);
    sub.setText("Subscriber ID: " + subscriberId);
    opr.setText("SIM Operator: " + simOperatorName);
    cn.setText("SIM Country: " + simcountry.toUpperCase());
}catch (Exception e){

}
    }
}
