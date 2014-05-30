package de.rwth.taskplaner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import de.rwth.Entities.MainTask;
import de.rwth.Entities.subTasks;
import de.rwth.constants.Constants;
import de.rwth.dbHandler.CRUD;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout.Alignment;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.example.taskplaner_.R;
public class Taskdivider extends Activity implements OnCheckedChangeListener{
	
	
	protected static String courseName;
	protected static String task_name="Assignment1";
	protected static String t_enddate;
	protected static String t_endtime;
	protected static String project_name;
	private TextView tv1,tv2;
	private EditText et;
	private Spinner spnr;
	private static Button btnadd,btnsave;
	private static TableLayout tl;
	private static RadioGroup allOptions;
	private static int no_tasks=0;
	private CRUD db;
	private Boolean isTaskSaved= false;
	private String mt_id;
	private static MainTask task;
	private static String id= "mt_id";
	private ArrayAdapter SpnrAdapter;
	private static String[]percentagearray = {"10%","20%","30%","40%","50%","60%","70%","80%","90%","100%"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_taskdivider);
	
	//task = new MainTask();
try{
	task = (MainTask)getIntent().getParcelableExtra(Constants.PAR_KEY);
	setMainTask();	
	SpnrAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,percentagearray);
	allOptions= (RadioGroup)findViewById(R.id.radioGroup1);
	allOptions.setOnCheckedChangeListener(this);	
}catch(BadParcelableException e){
	Log.e("Intent",e.getLocalizedMessage());}
	
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		btnadd = (Button)findViewById(R.id.btn);
		btnsave = (Button)findViewById(R.id.btn_save);
		tl = (TableLayout)findViewById(R.id.tableLayout1);
		if(group.getCheckedRadioButtonId()== R.id.radio0)
		{
		
			btnadd.setVisibility(View.INVISIBLE);
			btnsave.setVisibility(View.INVISIBLE);
			tl.removeAllViews();
		}
			if(group.getCheckedRadioButtonId()== R.id.radio1){
			btnadd.setVisibility(View.VISIBLE);
			btnsave.setVisibility(View.VISIBLE);
			tl.removeAllViews();
		}
	}
	public void setMainTask(){
		try{
		tv2 =(TextView)findViewById(R.id.textView3);	
//		tv2.setText(task.getT_name().toString());
		tv1 =(TextView)findViewById(R.id.textView1);
		tv1.setText("Due : " + task.getT_enddate());}
		catch(Exception e){
			Log.e("error",e.getMessage());
		}
	}
	public void btn_click(View v){	
		try{			
			
			TableRow row = new TableRow(this);
			et=new EditText(this);
			et.setLayoutParams(new TableRow.LayoutParams( android.view.ViewGroup.LayoutParams.WRAP_CONTENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 4 ) );
			et.setText("Enter Name of Subtask");
			et.setId(no_tasks);
			et.setTextColor(Color.WHITE);
			spnr = new Spinner(this);
			spnr.setLayoutParams(new TableRow.LayoutParams( android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT, 4) ); 
			spnr.setAdapter(SpnrAdapter);
			//spnr.setBackgroundColor(Color.WHITE);
			no_tasks+=1;
			
			 tl.addView(row);
			row.addView(et);
			row.addView(spnr);
		//	btn_save();
			}catch(Exception e){
			Log.e("Activity_failed",e.getMessage());
			}		
	}
	
public void btn_save(View v){
	
	//ArrayList<subTasks>SubTasks = new ArrayList<subTasks>();
	subTasks[] SubTasks= new subTasks[2];
	
	db = new CRUD(this);  
	if(isTaskSaved==false){
		//Save Main Task
	//MainTask task = new MainTask();
//	task.setT_name(this.task_name);
//	task.setT_enddate(t_enddate);
//	task.setT_endtime(t_endtime);
	db.insertTask(task);
	task.setT_id(db.getMaxMainTaskID());
	}
	for(int i=1;i<tl.getChildCount();i++){
		 TableRow row = (TableRow)tl.getChildAt(i);
		    if(row.getChildCount()>0){
		 	et = (EditText)row.getChildAt(0); // get child index on particular row
		    subTasks stask = new subTasks();
		   stask.setMt_id(task.getT_id());
		   stask.setName(et.getText().toString());
//		   db.insertSubTask(stask);
		   //SubTasks.add(stask);
		   SubTasks[i-1]=stask;
		    //Toast.makeText(this,subTasks.get(i-1),Toast.LENGTH_SHORT).show();
		    }
	}
	Intent mIntent = new Intent(this,Taskdetails.class);
	mIntent.putExtra(de.rwth.taskplaner.Taskdivider.id,task.getT_id());
	Bundle mBundle = new Bundle();  
    //mBundle.putString("mt_id",task.getT_id());
	mBundle.putParcelableArray(Constants.PAR_KEY, SubTasks);
    mIntent.putExtras(mBundle);
    startActivity(mIntent);

}
}