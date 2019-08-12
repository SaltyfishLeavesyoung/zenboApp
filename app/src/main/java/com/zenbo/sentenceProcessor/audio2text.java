package com.zenbo.sentenceProcessor;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import com.google.api.gax.core.CredentialsProvider;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.cloud.speech.v1.SpeechSettings;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;


public class audio2text {
    public static String getContent(String fileName) throws Exception{
        final GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("/Users/leavesyoung/eclipse-workspace/zenboApp/src/main/java/com/zenbo/zenboApp/381f76f13a06.json")).createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        SpeechSettings settings =
                SpeechSettings.newBuilder().setCredentialsProvider(
                        new CredentialsProvider() {
                            public Credentials getCredentials() throws IOException {
                                return credentials;
                            }
                        }
                ).build();
        try (SpeechClient speechClient = SpeechClient.create(settings)) {
            Path path = Paths.get(fileName);
            byte[] data = Files.readAllBytes(path);
            ByteString audioBytes = ByteString.copyFrom(data);

            RecognitionConfig config = RecognitionConfig.newBuilder()
                    //.setEncoding(AudioEncoding.LINEAR16)
                    //.setSampleRateHertz(44100)
                    .setLanguageCode("en-US")
                    //.setModel("command_and_search")
                    //.setEnableAutomaticPunctuation(true)
                    .build();
            RecognitionAudio audio = RecognitionAudio.newBuilder()
                    .setContent(audioBytes)
                    .build();

            RecognizeResponse response = speechClient.recognize(config, audio);
            List<SpeechRecognitionResult> results = response.getResultsList();

		      /*for (SpeechRecognitionResult result : results) {
		          SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
		          return alternative.getTranscript();
		        }*/
            SpeechRecognitionAlternative alternative = results.get(0).getAlternativesList().get(0);
            return alternative.getTranscript();
        }
    }
}
