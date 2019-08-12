package com.example.zenbotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.asus.robotframework.API.RobotAPI;
import com.asus.robotframework.API.RobotCallback;

import com.zenbo.zenboApp.app;

public class MainActivity extends AppCompatActivity {

    public RobotAPI robotAPI;
    RobotCallback robotCallback;
    RobotCallback.Listen robotListenCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.robotAPI = new RobotAPI(getApplicationContext(), robotCallback);
        try {
            app.mainAction(robotAPI);
        }
        catch(Exception exp){}
        setContentView(R.layout.activity_main);
    }
}
