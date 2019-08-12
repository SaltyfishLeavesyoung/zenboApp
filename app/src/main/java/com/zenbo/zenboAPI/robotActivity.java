package com.zenbo.zenboAPI;

import android.app.Activity;

import com.asus.robotframework.API.RobotAPI;
import com.asus.robotframework.API.RobotFace;

public class robotActivity extends Activity{
    public void expressionFunc(String expression, RobotAPI robotAPI) {
        // use the implemented api to change the expression.
        switch (expression) {
            case "interested":
                robotAPI.robot.setExpression(RobotFace.INTERESTED);
                break;
            case "happy":
                robotAPI.robot.setExpression(RobotFace.HAPPY);
                break;
            case "shy":
                robotAPI.robot.setExpression(RobotFace.SHY);
                break;
            case "proud":
                robotAPI.robot.setExpression(RobotFace.PROUD);
                break;
        }
    }
}
