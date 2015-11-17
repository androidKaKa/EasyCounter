package com.example.maindemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btnCounter=null;
	private static final int COUNTER_START=1;
	private boolean counterFlag=false;
	private int counter=0;
	Handler mHandler=new Handler()
			{
		
		public void handleMessage(Message msg)
		
		{
			switch(msg.what)
			{
				case COUNTER_START:
					if(counterFlag)
					{
					counter++;
					btnCounter.setText("count="+counter);
					}
					break;
				 
			}
			
		};
		
		
		
			};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnCounter=(Button)findViewById(R.id.btnCounter);
		
		btnCounter.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(counterFlag)
				{
					counterFlag=false;
				}else
				{
					counterFlag=true;
				}
			}
		});
		Thread myThread=new Thread(new Runnable() {
			
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					counterFlag=true;
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
