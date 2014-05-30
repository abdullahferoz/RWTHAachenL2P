package de.rwth.dbHandler;
import java.util.ArrayList;


import de.rwth.Entities.MainTask;
import de.rwth.Entities.subTasks;
import  de.rwth.constants.Constants;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteClosable;

public class CRUD extends SQLiteOpenHelper {

private static String main_taskID ;
private static String main_taskName;
private static String main_endDate;
private static String main_endTime;

private static String Sub_taskID="_id";
private static String Sub_taskName;
private static String Sub_endDate;
private static String Sub_endTime;
private static String SubTaskstatus;
private String CREATE_TABLE_SRING;
private static String isCompleted;

//create table MY_DATABASE (ID integer primary key, Content text not null);




public CRUD(Context c){
super(c,Constants.MYDATABASE_NAME,null,Constants.MYDATABASE_VERSION);
}

@Override
public void onCreate(SQLiteDatabase db) {
	// TODO Auto-generated method stub
	CreateMainTaskTable(db);
	CreateSubTaskTable(db);
	CreateUserTable(db);
}
public void CreateMainTaskTable(SQLiteDatabase db){
	CREATE_TABLE_SRING = mainTaskQueryString();
	db.execSQL(CREATE_TABLE_SRING);
	
} 
public void CreateSubTaskTable(SQLiteDatabase db){
	
		CREATE_TABLE_SRING = subTaskQueryString();	
		db.execSQL(CREATE_TABLE_SRING);
}
@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// TODO Auto-generated method stub
	 db.execSQL("DROP TABLE IF EXISTS " + Constants.MAIN_TASK_TABLE_NAME);
	 db.execSQL("DROP TABLE IF EXISTS " + Constants.SUB_TASK_TABLE_NAME);
	 db.execSQL("DROP TABLE IF EXISTS " + Constants.LOGIN_TABLE_NAME);
     onCreate(db);
}

public  void CreateUserTable(SQLiteDatabase DB){
	String query = "CREATE TABLE IF NOT EXISTS " + Constants.LOGIN_TABLE_NAME + "("
            + " User_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + " UserName" + " TEXT,"
            + " PASSWORD" + " TEXT)";
   // return query;
	DB.execSQL(query);
}
public Boolean checkLogin(String user_name,String password) {
    // Select All Query
    String selectQuery = "SELECT  * FROM " + Constants.LOGIN_TABLE_NAME + " Where UserName ='" + user_name + "' AND PASSWORD = '" + password + "'";
    
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null); 
    if (cursor.getCount()>0)
    	return true;
    else
    	return false;	
}


public  String mainTaskQueryString(){
	String query = "CREATE TABLE IF NOT EXISTS " + Constants.MAIN_TASK_TABLE_NAME + "( " 
            + "mt_id" + " INTEGER PRIMARY KEY AUTOINCREMENT, " + "tName" + " TEXT, "
            + "endDate" + " TEXT, " + "endTime" +" TEXT)";
    return query;
}

public  String subTaskQueryString(){
	String query = "CREATE TABLE IF NOT EXISTS " + Constants.SUB_TASK_TABLE_NAME + "( "
            + "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " + "mt_id" + " INTEGER FOREIGHN KEY, " + "stName" + " TEXT, "
            + "st_endDate" + " TEXT, " + "st_endTime" +" TEXT," + "isCompleted" + " BOOLEAN)";
    return query;
}

public ArrayList<subTasks> getAllsubTasks() {
    ArrayList<subTasks> subtaskslists = new ArrayList<subTasks>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + Constants.MAIN_TASK_TABLE_NAME;
 
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null); 
 
    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
        do {
        	subTasks task = new subTasks();
        	task.setID(cursor.getString(0));
        	task.setMt_id(cursor.getString(1));
        	task.setName(cursor.getString(2));
        	task.setenddate(cursor.getString(3));
        	task.setendTime(cursor.getString(4));
        	task.setStatus(cursor.getString(5));
            // Adding contact to list
        	subtaskslists.add(task);
        } while (cursor.moveToNext());
    }
 
    // return contact list
    db.close();
    return subtaskslists;
}

public MainTask getMaintask(String id){
	
	
	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + Constants.MAIN_TASK_TABLE_NAME + " Where mt_id = " + id;
	 
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null); 
	 
	    // looping through all rows and adding to list
//	    if (cursor.moveToFirst()) {
//	        do {
	        	MainTask task = new MainTask();
	        	task.setT_id(cursor.getString(0));
	        	task.setT_name(cursor.getString(1));
	        	task.setT_enddate(cursor.getString(2));
	        	task.setT_endtime(cursor.getString(3));
	            // Adding contact to list
	        	//subtaskslists.add(task);
//	        } while (cursor.moveToNext());
//	    }
	 
	    // return contact list
	    db.close();
	    //return subtaskslists;
	return task;
	
}

public void insertTask(MainTask task){
	SQLiteDatabase db = this.getWritableDatabase();
	 
    ContentValues values = new ContentValues();
 // Subtask Detail
    values.put(Sub_taskName, task.getT_name());
    values.put(Sub_endDate, task.getT_enddate()); 
    values.put(Sub_endTime, task.getT_endtime());
    // Inserting Row
    db.insert(Constants.MAIN_TASK_TABLE_NAME, null, values);
    db.close(); // Closing database connection
} 

public void insertSubTask(subTasks task ){
	SQLiteDatabase db = this.getWritableDatabase();
	 
    ContentValues values = new ContentValues();
 // Subtask Detail
    values.put(Sub_taskName, task.getName());
    values.put(main_taskID, task.getMt_id()); 
    values.put(Sub_endDate, task.getenddate()); 
    values.put(Sub_endTime, task.getendtime());
    values.put(isCompleted, task.getStatus());
    // Inserting Row
    db.insert(Constants.SUB_TASK_TABLE_NAME, null, values);
    db.close(); // Closing database connection
	}
public String getMaxMainTaskID(){
	 	String selectQuery = "SELECT  mt_id FROM " + Constants.MAIN_TASK_TABLE_NAME + " ORDER BY mt_id" ;
	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);
	    if (cursor.getCount()>0){ 
	    	
	    	return(cursor.getString(cursor.getColumnCount()-1));
	    }
	    else return null;
}
}