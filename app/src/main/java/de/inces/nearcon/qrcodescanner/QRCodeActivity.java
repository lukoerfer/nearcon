package de.inces.nearcon.qrcodescanner;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import de.inces.nearcon.R;
import de.inces.nearcon.conversations.Message;

public class QRCodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
    }

    @Override
    protected void onResume(){
        super.onResume();
        this.initQRbutton();
        startQRScanIntent();
    }

    protected void initQRbutton(){
        Button btnQRScan = (Button) findViewById(R.id.btn_startQRscan);
        btnQRScan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // What to do when submitting
                // Or show warning if you have to do something more

                //first get descriptiontext

                startQRScanIntent();
            }
        });
    }

    public void startQRScanIntent() {
        try {
            Intent qrScanIntent = new Intent("com.google.zxing.client.android.SCAN");
            qrScanIntent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            qrScanIntent.putExtra("SCAN_WIDTH", 800);
            qrScanIntent.putExtra("SCAN_HEIGHT", 800);
            qrScanIntent.putExtra("RESULT_DISPLAY_DURATION_MS", 200L);
            qrScanIntent.putExtra("PROMPT_MESSAGE", "Scan a CDJ QR Code");
            this.startActivityForResult(qrScanIntent, 0);
        } catch (Exception ex) {
            startZXingDownloadDialog();
        }
    }


    public void startZXingDownloadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ZXing not found").setMessage("The ZXing app is not installed, but required for QR Code reading. Do you want to download it?");
        builder.setNegativeButton("Download", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startZXingDownloadIntent();
            }
        });
        builder.setPositiveButton("Cancel", null);
        builder.setCancelable(false);
        builder.create().show();
    }

    public void startZXingDownloadIntent() {
        Uri zxingUri = Uri.parse("market://details?id=com.google.zxing.client.android");
        Intent zxingIntent = new Intent(Intent.ACTION_VIEW, zxingUri);
        startActivity(zxingIntent);
    }
}
