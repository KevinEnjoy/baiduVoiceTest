package com.test.baiduvoicetest;

import android.content.Intent;
import android.speech.SpeechRecognizer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.baidudu.voice.util.ConstantBD;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
    }

    public void start(View view){
        startVoiceRecognition();
    }

    private static final int REQUEST_UI = 1;

    public void startVoiceRecognition(){

        Intent intent = new Intent();
        intent.putExtra(ConstantBD.EXTRA_SOUND_START, R.raw.bdspeech_recognition_start);
        intent.putExtra(ConstantBD.EXTRA_SOUND_END, R.raw.bdspeech_speech_end);
        intent.putExtra(ConstantBD.EXTRA_SOUND_SUCCESS, R.raw.bdspeech_recognition_success);
        intent.putExtra(ConstantBD.EXTRA_SOUND_ERROR, R.raw.bdspeech_recognition_error);
        intent.putExtra(ConstantBD.EXTRA_SOUND_CANCEL, R.raw.bdspeech_recognition_cancel);
        intent.setAction("com.baidu.action.RECOGNIZE_SPEECH");
        startActivityForResult(intent, REQUEST_UI);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            Bundle results = data.getExtras();
            ArrayList<String> nbest = results.getStringArrayList( SpeechRecognizer.RESULTS_RECOGNITION);

            String temp = Arrays.toString(nbest.toArray(new String[nbest.size()]));
            System.out.println("识别成功：" + temp);
            tv.setText(temp);
            String json_res = results.getString("origin_result");
            try {
                System.out.println("origin_result=\n" + new JSONObject(json_res).toString(4));
            } catch (Exception e) {
                System.out.println("origin_result=[warning: bad json]\n" + json_res);
            }
            //tv.setText(nbest.get(0));
        }
    }
}
