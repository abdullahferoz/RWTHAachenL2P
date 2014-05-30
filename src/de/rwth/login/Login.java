package de.rwth.login;



import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import de.rwth.Entities.MainTask;
import de.rwth.constants.Constants;
import de.rwth.dbHandler.CRUD;
import de.rwth.taskplaner.L2pAccess;
import de.rwth.taskplaner.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Interpolator.Result;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.taskplaner_.R;
public class Login extends Activity{


    //private variables
    int user_id;
    String user_name;
    String password;
    ArrayList<MainTask> tasklist= new ArrayList<MainTask>();
    Intent i;
   //APi Access
   
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new L2pAccess().execute();
		setContentView(R.layout.login_window);  
		}

	public void imagebuttonlogin_clicked(View v){	
		try{
			
			Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(L2pAccess.surname+"?q=verify&d="+L2pAccess.code));
			startActivity(browserIntent);
			/*
			TextView textView = (TextView) findViewById(R.id.errottv);
			
			EditText editText; 
			editText = (EditText) findViewById(R.id.usernametxt);
			this.user_name = editText.getText().toString();
			
			editText = (EditText) findViewById(R.id.passwordtxt);
			this.password = editText.getText().toString();	
			//CRUD crud = new CRUD(this);
			//if(crud.checkLogin(this.user_name, this.password) == true){
				//textView.setText("You are logged in");
			if((this.user_name.equals("af651317")||this.user_name.equals("mk094020")||this.user_name.equals("ss028713") ) && this.password.equals("123"))
			{
			i = new Intent(this,MainActivity.class);
			startActivity(i);
			}
			else
			{
//				ContextWrapper c = new ContextWrapper(this);
//				c.getDatabasePath(Constants.MYDATABASE_NAME);
				textView.setText("Oops please check your login details");
			}
			*/
		    }catch(Exception e){
			Log.e("Activity_failed",e.getMessage());
			}		
	}
	
	

}

