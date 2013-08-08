package com.mahesh.lovecalculator;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoveCalculatorActivity extends Activity {

	EditText editTextYourName, editTextPartnerName;
	TextView textViewPercentageText, textViewPercentageTextValue;
	Button getLovePercent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		editTextYourName = (EditText) findViewById(R.id.editTextYourName);
		editTextPartnerName = (EditText) findViewById(R.id.editTextPartnerName);
		textViewPercentageText = (TextView) findViewById(R.id.textViewPercentageText);
		textViewPercentageTextValue = (TextView) findViewById(R.id.textViewPercentageTextValue);
		getLovePercent = (Button) findViewById(R.id.buttonCalculate);
		getLovePercent.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String yourName = editTextYourName.getText().toString();
				String partnerName = editTextPartnerName.getText().toString();
				String lovePercentage = getLovePercentage(yourName, partnerName);
				textViewPercentageText.setText(getResources().getString(
						R.string.percentage_text_1)
						+ " "
						+ yourName
						+ " "
						+ getResources().getString(R.string.percentage_text_2)
						+ " "
						+ partnerName
						+ " "
						+ getResources().getString(R.string.percentage_text_3)
						+ " " + lovePercentage);
				// textViewPercentageTextValue.setText(lovePercentage);
			}
		});

	}

	private String getLovePercentage(String yourName, String partnerName) {
		String name = yourName + partnerName;
		String compareString = "LOVESloves";
		ArrayList<Integer> lovesCount = new ArrayList<Integer>();
		for (int j = 0; j < 5; j++) {
			int tempValue = 0;
			for (int i = 0; i < name.length(); i++) {
				if (name.charAt(i) == compareString.charAt(j)
						|| name.charAt(i) == compareString.charAt(j + 5)) {
					tempValue++;
				}
			}
			lovesCount.add(tempValue);
		}
		String tempLovesCount = null;
		for (int i = 1; i > 0; i++) {
			tempLovesCount = buildListToString(lovesCount);
			if (Integer.parseInt(tempLovesCount) > 100) {
				lovesCount = calculateCount(tempLovesCount);
			} else {
				break;
			}
		}

		return tempLovesCount;

	}

	private String buildListToString(ArrayList<Integer> tempList) {
		String value = null;
		for (int i = 0; i < tempList.size(); i++) {
			if (value != null) {
				value = value + tempList.get(i).toString();
			} else {
				value = tempList.get(i).toString();
			}

		}
		return value;
	}

	private ArrayList<Integer> calculateCount(String value) {

		ArrayList<Integer> tempList = new ArrayList<Integer>();
		for (int i = 0; i < value.length() - 1; i++) {
			int tempValue = Character.getNumericValue(value.charAt(i))
					+ Character.getNumericValue(value.charAt(i + 1));
			tempList.add(tempValue);
		}
		return tempList;
	}

}
