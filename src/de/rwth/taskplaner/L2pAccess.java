package de.rwth.taskplaner;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Interpolator.Result;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.view.accessibility.AccessibilityEvent;

import org.json.JSONException;
import org.json.JSONObject;


public class L2pAccess extends AsyncTask<Void,Void, Void>{
private Activity activity;
	private static HttpClient httpclient = new DefaultHttpClient();
    private  static HttpPost httppost = new HttpPost("https://oauth.campus.rwth-aachen.de/oauth2waitress/oauth2.svc/code");
    private static HttpResponse response ;
    private static HttpEntity entity;
    private static  String values;
    public static String surname,code;
    @Override
	protected Void doInBackground(Void... params) {
	try{
		// TODO Auto-generated method stub
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();  
        nameValuePairs.add(new BasicNameValuePair("client_id", "dsdZwIskJbTEVcCWby735MVugmuvrBQcjVcYrEaHuHFLWlqMTz9TXJ315zs3BCL1.apps.rwth-aachen.de"));
        nameValuePairs.add(new BasicNameValuePair("scope", "application/x-www-form-urlencoded"));
        httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        response = httpclient.execute(httppost);
       
        entity = response.getEntity();
        values = EntityUtils.toString(entity);
      //  InputStream in = response.getEntity().getContent();
        System.out.println(values);
       JSONObject oJSON = new JSONObject(values);
       //String ver_url = (String) oJSON.get("verification_url");
       surname = oJSON.getString("verification_url");
       code = oJSON.getString("user_code");

		return null;
	}
	catch(Exception e){
		e.getMessage();
	}
	finally{
		return null;
	}
	}
	

	
	
}


