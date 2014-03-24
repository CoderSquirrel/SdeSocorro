package com.example.sdesocorro;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity implements
		OnGesturePerformedListener {
	private GestureLibrary mLibrary;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mLibrary = GestureLibraries.fromRawResource(this, R.raw.gestures);
		if (!mLibrary.load()) {
			Toast.makeText(MainActivity.this,
					"Não foi possivel carrega os gestures", Toast.LENGTH_LONG)
					.show();
		} else {
			Toast.makeText(MainActivity.this, "Gestures carregados",
					Toast.LENGTH_LONG).show();
		}
		GestureOverlayView gestureView = (GestureOverlayView) findViewById(R.id.gestures_overlay);
		gestureView.addOnGesturePerformedListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onGesturePerformed(GestureOverlayView arg0, Gesture gesture) {
		ArrayList<Prediction> predictions = mLibrary.recognize(gesture);

		// Get highest-ranked prediction
		if (predictions.size() > 0) {
			Prediction prediction = predictions.get(0);

			// Ignore weak predictions

			if (prediction.score > 2.0) {
				if (prediction.name.equals("s")) {

					// } else if (prediction.name.equals(NEXT)) {
					//
					//
					// } else if (prediction.name.equals(YES)) {
					//
					//
					// } else if (prediction.name.equals(NO)) {
					//
					//
					Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT)
							.show();
				} else {
					Toast.makeText(this, prediction.name, Toast.LENGTH_SHORT)
							.show();

				}
			}

		}
	}

	
}
