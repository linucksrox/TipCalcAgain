package com.linucksrox.tipcalcagain;

import java.text.NumberFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class TipCalcAgainActivity extends Activity
	implements OnEditorActionListener, OnSeekBarChangeListener {
	
	private String billAmountString;
	private float billAmount;
	private EditText billAmountEditText;
	private TextView percentAmountTextView;
	private SeekBar percentSeekBar;
	private float tipPercent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_t);
		
		// get a handle on the widgets we need to get input from or change
		billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
		percentAmountTextView = (TextView) findViewById(R.id.percentAmount);
		percentSeekBar = (SeekBar) findViewById(R.id.percentSeekBar);
		
		// set up listeners for stuff
		billAmountEditText.setOnEditorActionListener(this);
		percentSeekBar.setOnSeekBarChangeListener(this);
	}
	
	public void calculateAndDisplay() {
		// get the bill amount, but if nothing was entered then use $0
		billAmountString = billAmountEditText.getText().toString();
		if (billAmountString.equals("")) {
			billAmount = 0;
		}
		else {
			billAmount = Float.parseFloat(billAmountString);
		}
		
		// get tip percent
		int progress = percentSeekBar.getProgress();
		tipPercent = (float) progress / 100;
		
		// display new values
		NumberFormat percent = NumberFormat.getPercentInstance();
		percentAmountTextView.setText(percent.format(tipPercent));
	}

	@Override
	public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		if (actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_UNSPECIFIED) {
			calculateAndDisplay();
		}
		return false;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		calculateAndDisplay();
	}

}
