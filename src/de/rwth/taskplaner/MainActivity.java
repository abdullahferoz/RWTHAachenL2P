package de.rwth.taskplaner;


import com.example.taskplaner_.R;

import de.rwth.Entities.MainTask;
import de.rwth.constants.Constants;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;

public class MainActivity extends ActionBarActivity {
MainTask task= new MainTask();
Button btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
			
		}
		
	}
public void btn1_click(View v){
	
	try{
		btn = (Button)findViewById(R.id.button1);
		task.setT_name(btn.getText().toString());
		task.setT_enddate("07.07.2014");
		task.setT_endtime("12:00");
		//v.setBackgroundColor(Color.DKGRAY);
	Intent i = new Intent(this,Taskdivider.class);
	Bundle mbundle = new Bundle();
	mbundle.putParcelable(Constants.PAR_KEY, task);
	i.putExtras(mbundle);
	//i.putExtra(Constants.PAR_KEY, task);
	startActivity(i);
	}
	catch(Exception e){
		Log.e("Activity_failed",e.getMessage());
	}
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
