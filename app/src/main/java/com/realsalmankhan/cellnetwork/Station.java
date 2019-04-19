package com.realsalmankhan.cellnetwork;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrengthCdma;
import android.telephony.CellSignalStrengthGsm;
import android.telephony.CellSignalStrengthLte;
import android.telephony.CellSignalStrengthWcdma;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.util.List;

public class Station extends AppCompatActivity {

    TextView t1,t2,t3,t4,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station);
        t1 = findViewById(R.id.text1);
        t2 = findViewById(R.id.text2);
        t3 = findViewById(R.id.text3);
        t4 = findViewById(R.id.text4);
        t5 = findViewById(R.id.text5);
        TelephonyManager tm = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            } else {
                List<CellInfo> cellInfos = tm.getAllCellInfo();
                CellInfo cell = cellInfos.get(0);
                if (cell instanceof CellInfoGsm) {
                    final CellSignalStrengthGsm gsm = ((CellInfoGsm) cell).getCellSignalStrength();
                    final CellIdentityGsm identityGsm = ((CellInfoGsm) cell).getCellIdentity();
                    // Signal Strength
                    t1.setText(String.format("Tower Strength: %d", gsm.getDbm()));
                    // Cell Identity

                    t2.setText(String.format("CID: %d", identityGsm.getCid()));

                    t3.setText(String.format("MCC: %d", identityGsm.getMcc()));

                    t4.setText(String.format("MNC: %d", identityGsm.getMnc()));

                    t5.setText(String.format("LAC: %d", identityGsm.getLac()));

                } else if (cell instanceof CellInfoCdma) {
                    final CellSignalStrengthCdma cdma = ((CellInfoCdma) cell).getCellSignalStrength();
                    final CellIdentityCdma identityCdma = ((CellInfoCdma) cell).getCellIdentity();
                    // Signal Strength
                    t1.setText(String.format("Tower Strength: %d", cdma.getDbm()));
                    // Cell Identity
                    t2.setText(String.format("BasestationId: %d", identityCdma.getBasestationId()));
                    t3.setText(String.format("MNC: %d", identityCdma.getSystemId()));
                    t4.setText(String.format("NetworkId: %d", identityCdma.getNetworkId()));
                    t5.setText(String.format("SystemId: %d", identityCdma.getSystemId()));

                } else if (cell instanceof CellInfoLte) {
                    final CellSignalStrengthLte lte = ((CellInfoLte) cell).getCellSignalStrength();
                    final CellIdentityLte identityLte = ((CellInfoLte) cell).getCellIdentity();
                    // Signal Strength
                    t1.setText(String.format("Tower Strength: %d", lte.getDbm()));
                    t2.setText(String.format("TimingAdvance: %d", lte.getTimingAdvance()));
                    // Cell Identity
                    t3.setText(String.format("MCC: %d", identityLte.getMcc()));
                    t4.setText(String.format("MNC: %d", identityLte.getMnc()));
                    t5.setText(String.format("CID: %d", identityLte.getCi()));

                } else if  (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2 && cell instanceof CellInfoWcdma) {
                    final CellSignalStrengthWcdma wcdma = ((CellInfoWcdma) cell).getCellSignalStrength();
                    final CellIdentityWcdma identityWcdma = ((CellInfoWcdma) cell).getCellIdentity();
                    // Signal Strength
                    t1.setText(String.format("Tower Strength: %d", wcdma.getDbm()));
                    // Cell Identity
                    t2.setText(String.format("LAC: %d", identityWcdma.getLac()));
                    t3.setText(String.format("MCC: %d", identityWcdma.getMcc()));
                    t4.setText(String.format("MNC: %d", identityWcdma.getMnc()));
                    t5.setText(String.format("CID: %d", identityWcdma.getCid()));
                    //t2.setText(String.format("BasestationId: %d", identityWcdma.getPsc());

                } else {
                    Log.i("Cell:","Unknown type of cell signal!"
                            + "\n ClassName: " + cell.getClass().getSimpleName()
                            + "\n ToString: " + cell.toString());
                }
            }
        }
    }
}
