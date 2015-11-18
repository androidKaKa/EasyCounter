package com.example.maindemo;

import java.util.regex.PatternSyntaxException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private String TAG="Counter demo--->>";
	private Button btnCounter=null;
	private Button btnStop=null;
	private TextView textVShow=null;
	private EditText editGetNum=null;
	private static final int COUNTER_START=1;
	private static final int COUNTER_STOP=0;
	private static final int NUM_ERROR=3;
	private boolean counterFlag=false;
	private int counter=100;
	
	Handler mHandler=new Handler()
			{
		
		public void handleMessage(Message msg)
		
		{
			switch(msg.what)
			{
				case COUNTER_START:
					if(counterFlag)
					{
					counter--;
					textVShow.setText("count="+counter);
					}
					break;
				case COUNTER_STOP:
					counterFlag=false;
					break;
					
				case NUM_ERROR:
					textVShow.setText("num error");
					break;
					
			}
			
		};
		
		
		
			};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		 
		
		btnCounter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(match(editGetNum.getText().toString()))
				{
				counter=Integer.parseInt(editGetNum.getText().toString());
				Log.i(TAG, "counter="+counter);
				counterFlag=true;
				
				} else
				{
					mHandler.sendEmptyMessage(NUM_ERROR);
				}
				
				
			}
		});
		
		btnStop.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(counterFlag)
				{
					counterFlag=false;
				}
			}
		});
		Thread myThread=new Thread(new Runnable() {
			
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					
					 while(true) //这个可以设置个标志位判断 退出循环。
					 {
						 Thread.sleep(1000);
						 mHandler.sendEmptyMessage(COUNTER_START);
					 }
					
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		myThread.start();
	}

	public void initView()
	{
		btnCounter=(Button)findViewById(R.id.btnCounter);
		editGetNum=(EditText)findViewById(R.id.editGetNum);
		btnStop=(Button)findViewById(R.id.btnStop);
		textVShow=(TextView)findViewById(R.id.textVShow);
	}
	
	//正则表达式 判断一下是否为整数，可以为负数。
	public boolean match(String strSource)
	{
		boolean foundMatch =false;
		try {
			  foundMatch = strSource.matches("^-?[1-9]\\d*$");
		} catch (PatternSyntaxException ex) {
			// Syntax error in the regular expression
		}
		
		return foundMatch;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
