package de.inces.nearcon.app.conversation;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import de.inces.nearcon.core.model.conversations.Message;
import de.inces.nearcon.R;
import de.inces.nearcon.core.model.auth.User;
import de.inces.nearcon.overview.OverviewActivity;
import de.inces.nearcon.backend.DataService;

public class ConversationActivity extends AppCompatActivity {

    private DataService.ConversationBinder service;
    private MessageAdapter messages;
    protected Activity actToastHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_conversation);
        this.initializeMessages();
        this.initializeSendUnit();
        this.connectService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.update();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(serviceConnection);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_conversation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item_qrscan) {
            this.actToastHelper = this;
            startQRScanIntent();
        }else{
            //TODO: Show QR Code

            Intent intent = new Intent(this, ShowQRCodeActivity.class);
            startActivity(intent);
        }
        return true;
    }

    private void initializeMessages() {
        this.messages = new MessageAdapter(this, new User("test1"));
        ((ListView) this.findViewById(R.id.list_messages)).setAdapter(this.messages);
    }

    private void initializeSendUnit() {
        final EditText editMessage = (EditText) findViewById(R.id.edit_message);
        final Button btnSend = (Button) findViewById(R.id.btn_send);
        editMessage.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnSend.setEnabled(s.length() > 0);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = editMessage.getText().toString();
                messages.add(new Message(new User("test1"), message));
                editMessage.setText("");
            }
        });
    }

    private void connectService() {
        Intent serviceIntent = new Intent(this, DataService.class)
            .setAction(DataService.CONVERSATION_SERVICE);
        this.bindService(serviceIntent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ConversationActivity.this.service = (DataService.ConversationBinder) service;
            ConversationActivity.this.update();
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            ConversationActivity.this.service = null;
        }
    };

    private void update() {
        if (service != null) {
            this.messages.clear();
            this.messages.addAll(service.getMessages(null));
        } else {
            this.connectService();
        }
    }


    public void startQRScanIntent() {
        try {
            Intent qrScanIntent = new Intent("com.google.zxing.client.android.SCAN");
            qrScanIntent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            qrScanIntent.putExtra("SCAN_WIDTH", 800);
            qrScanIntent.putExtra("SCAN_HEIGHT", 800);
            qrScanIntent.putExtra("RESULT_DISPLAY_DURATION_MS", 200L);
            qrScanIntent.putExtra("PROMPT_MESSAGE", "Scan QR Code");
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK) {
                        String result = data.getStringExtra("SCAN_RESULT");
                        //TODO: DO something with Result;
                        Toast.makeText(actToastHelper, "Meeting Confirmed",
                                Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    // unknown request code
                    break;
            }
        }
    }


}
