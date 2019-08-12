package com.zenbo.sentenceProcessor;

import java.util.List;
import java.util.Properties;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;

public class wordProperty {
    public static String[] getPriperties(String sentence){
        int len = sentence.length();
        Properties props = new Properties();
        props.put("annotators", "tokenize, ssplit, pos");
        StanfordCoreNLP processor = new StanfordCoreNLP(props);
        Annotation str = new Annotation(sentence);
        processor.annotate(str);
        List<CoreMap> listStr = str.get(SentencesAnnotation.class);
        String[] ret = new String[len];
        int count = 0;
        for(CoreMap s: listStr){
            for (CoreLabel token: s.get(TokensAnnotation.class)){
                String pos = token.get(PartOfSpeechAnnotation.class);
                ret[count] = pos;
                count ++;
            }
        }
        return ret;
    }
}
