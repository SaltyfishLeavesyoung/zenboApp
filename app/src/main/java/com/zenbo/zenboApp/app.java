package com.zenbo.zenboApp;

import com.zenbo.sentenceProcessor.audio2text;
import com.zenbo.sentenceProcessor.wordProperty;
import com.zenbo.zenboAPI.func;
import com.asus.robotframework.API.RobotAPI;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class app
{
    public static void mainAction(RobotAPI robotAPI) throws Exception {
        // read the wav file
        String fileName = "/Users/leavesyoung/Desktop/testAudio.wav";
        // get the content(to test, I use the test sentence.)
        //String ans = audio2text.getContent(fileName);
        String ans = "be happy be shy be proud";
        System.out.println("speech:");
        System.out.println(ans);
        // get words
        String[] split = ans.split(" ");
        // get the property of the words.
        String[] prop = wordProperty.getPriperties(ans);
        // divide it into operations
        List<String> ops = getOps(split, prop);
        System.out.println("operations:");
        // do the operations continuously
        for(int i = 0; i < ops.size(); i ++) {
            func.functions(ops.get(i), robotAPI);
        }
    }

    public static List<String> getOps(String[] split, String[] prop){
        List<String> ops = new ArrayList<String>();
        int begin = 0;
        // the operations often are imperative sentences, which begin with a verb.
        for(int i = 0; i < split.length; i ++) {
            if(i > 0 && Pattern.matches("^V.*", prop[i])) {
                String op = split[begin];
                for(int j = begin + 1; j < i; j ++) {
                    op = op + " " + split[j];
                }
                ops.add(op);
                begin = i;
            }
        }
        String op = split[begin];
        for(int j = begin + 1; j < split.length; j ++) {
            op = op + " " + split[j];
        }
        ops.add(op);
        return ops;
    }
}