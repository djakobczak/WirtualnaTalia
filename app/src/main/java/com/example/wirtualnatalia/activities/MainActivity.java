package com.example.wirtualnatalia.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


import com.example.wirtualnatalia.R;
import com.example.wirtualnatalia.network.service.ServiceManager;
import com.example.wirtualnatalia.network.service.StatusService;

import java.net.ServerSocket;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button serverActivityBtn;
    private Button clientActivityBtn;
    private Button startServiceBtn;
    private Button startDiscoveryBtn;

    private StatusService statusService;
    private ServiceManager serviceManager;

    private Context currentContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentContext = this;

        serverActivityBtn = (Button) findViewById(R.id.nextActivityServerBtn);
        clientActivityBtn = (Button) findViewById(R.id.nextActivityClientBtn);
        startServiceBtn = (Button) findViewById(R.id.startServiceBtn);
        startDiscoveryBtn = (Button) findViewById(R.id.startDiscoveryBtn);

        statusService = new StatusService();
        serviceManager = new ServiceManager(this);

        serverActivityBtn.setOnClickListener(v -> launchServerActivity());
        clientActivityBtn.setOnClickListener(v -> launchClientActivity());
        startServiceBtn.setOnClickListener(v -> statusService.registerService(
                                           currentContext, 5678));
        startDiscoveryBtn.setOnClickListener(v -> serviceManager.start());
    }

    @Override
    protected void onPause() {
        super.onPause();
//        !TODO
    }

    private void launchServerActivity() {
        Intent intent = new Intent(this, ServerActivity.class);
        startActivity(intent);
    }

    private void launchClientActivity() {
        Intent intent = new Intent(this, ClientActivity.class);
        startActivity(intent);
    }
}