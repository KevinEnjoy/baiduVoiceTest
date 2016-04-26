package com.test.baiduvoicetest;

import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONObject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.TextView;

import com.baidu.speech.VoiceRecognitionService;
import com.baidudu.voice.util.ConstantBD;

/**
 * 使用百度API的sdk来识别
 * 但是各种逻辑可以自己处理
 * @author Administrator
 *
 */
public class MainAPIActivity extends Activity {

	TextView tv;
	SpeechRecognizer speechRecognizer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//startActivity(new Intent(this,ApiActivity.class));

		tv = (TextView)findViewById(R.id.tv);
		speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this, new ComponentName(this, VoiceRecognitionService.class));
		speechRecognizer.setRecognitionListener(new RecognitionListener() {

			@Override
			public void onRmsChanged(float rmsdB) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onResults(Bundle results) {
				// TODO Auto-generated method stub
				//long end2finish = System.currentTimeMillis() - speechEndTime;
				ArrayList<String> nbest = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
				System.out.println("识别成功：" + Arrays.toString(nbest.toArray(new String[nbest.size()])));
				String json_res = results.getString("origin_result");
				try {
					System.out.println("origin_result=\n" + new JSONObject(json_res).toString(4));
				} catch (Exception e) {
					System.out.println("origin_result=[warning: bad json]\n" + json_res);
				}
				tv.setText(nbest.get(0));
			}

			@Override
			public void onReadyForSpeech(Bundle params) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPartialResults(Bundle partialResults) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onEvent(int eventType, Bundle params) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onError(int error) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onEndOfSpeech() {
				// TODO Auto-generated method stub
				System.out.println("检测到用户的已经停止说话");
			}

			@Override
			public void onBufferReceived(byte[] buffer) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onBeginningOfSpeech() {
				// TODO Auto-generated method stub
				System.out.println("检测到用户的已经开始说话");
			}
		});
	}



	public void start(View v){

		Intent intent = new Intent();
		intent.putExtra(ConstantBD.EXTRA_SOUND_START, R.raw.bdspeech_recognition_start);
		intent.putExtra(ConstantBD.EXTRA_SOUND_END, R.raw.bdspeech_speech_end);
		intent.putExtra(ConstantBD.EXTRA_SOUND_SUCCESS, R.raw.bdspeech_recognition_success);
		intent.putExtra(ConstantBD.EXTRA_SOUND_ERROR, R.raw.bdspeech_recognition_error);
		intent.putExtra(ConstantBD.EXTRA_SOUND_CANCEL, R.raw.bdspeech_recognition_cancel);

		speechRecognizer.startListening(intent);
	}

}
