package com.zenbo.zenboAPI;

import com.asus.robotframework.API.RobotAPI;

public class func {
    public static void functions(String op, RobotAPI robotAPI) {
        String[] words = op.split(" ");
        robotActivity rb = new robotActivity();
        // the sentence to change expression is "be XXX".
        if(words[0] == "be") {
            rb.expressionFunc(words[1], robotAPI);
        }
    }
}

