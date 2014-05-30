package de.rwth.taskplaner;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Array;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Date;
import java.sql.NClob;
import java.sql.Ref;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLXML;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.example.taskplaner_.R;

import de.rwth.Entities.MainTask;
import de.rwth.Entities.subTasks;
import de.rwth.constants.Constants;
import de.rwth.dbHandler.CRUD;
public class Taskdetails extends Activity {  
private String mt_id;
private String mt_name;
private String mt_enddate;
private String mt_endtime;
private MainTask mt;
private CRUD db;
private CheckBox chkbox1,chkbox2;
private TextView tvStatus;


@Override
protected void onCreate(Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_taskdetails);
	 tvStatus = new TextView(this);
	 tvStatus  = (TextView)this.findViewById(R.id.textView3);
	//db = new CRUD(this);
//	getmainTask();
//	getSubTasks();
//	populatelistview();
} 
public void getmainTask(){
	try{
		mt_id = getIntent().getStringExtra("mt_id");	
		mt = db.getMaintask(mt_id);}
	catch(Exception e){
		Log.e("intent",e.getMessage());
		}
}
public void getSubTasks(){
	subTasks[] subtasklist= (subTasks[]) getIntent().getParcelableArrayExtra(Constants.PAR_KEY);	
}
public void populatelistview(){
//	lv= (ListView)findViewById(R.id.listView1);
//	
//	lv.setAdapter(adapter);
	}
public void onCheckBoxClicked(View V){
	// Is the view now checked?
    boolean checked = ((CheckBox) V).isChecked();
   
    // Check which checkbox was clicked
   // switch(V.getId()) {
       if( V.getId()== R.id.checkBox1){
    //case R.id.checkBox1 : 
    if (checked){ 
    	tvStatus.setText("10% Completed" );
    }   }      
    //case R.id.checkBox2:
    if( V.getId()== R.id.checkBox2){
    	if (checked){
    	tvStatus.setText("30% Completed" );
    	}        
    }
}
}



