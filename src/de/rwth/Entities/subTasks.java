package de.rwth.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class subTasks implements Parcelable {


    //private variables
    String st_id;
    String mt_id;
   
	String st_name;
    String st_enddate;
    String st_endtime;
    String status;
     
    // Empty constructor
    public subTasks(){
         
    }
    public  subTasks(Parcel in){
        String[] data = new String[3];

        in.readStringArray(data);
        this.st_id = data[0];
        this.mt_id = data[1];
        this.st_name = data[2];
        this.st_enddate = data[3];
        this.st_endtime = data[4];
    }

    
    // constructor
    public void subTasks(String id, String mt_id,String name ,String enddate, String endtime){
        this.st_id = id;
        this.mt_id = mt_id;
        this.st_name = name;
        this.st_enddate = enddate;
        this.st_endtime = endtime;
    }
     
   
    // getting ID
    public String getID(){
        return this.st_id;
    }
     
    // setting id
    public void setID(String id){
        this.st_id = id;
    }
     
    // getting name
    public String getName(){
        return this.st_name;
    }
     
    public String getMt_id() {
		return mt_id;
	}
	public void setMt_id(String mt_id) {
		this.mt_id = mt_id;
	}
    
    // setting name
    public void setName(String name){
        this.st_name = name;
    }
     
    // getting phone number
    public String getenddate(){
        return this.st_enddate;
    }
     
    // setting phone number
    public void setenddate(String enddate){
        this.st_enddate = enddate;
    }
    public String getendtime(){
        return this.st_endtime;
    }
     
    // setting phone number
    public void setendTime(String endtime){
        this.st_endtime = endtime;
    }
    
    
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeArray(new String[]{this.st_id,this.mt_id,this.st_name,this.st_enddate,this.st_endtime,this.status});
		
	}
	public Parcelable.Creator CREATOR = new Parcelable.Creator() {
		 public subTasks createFromParcel(Parcel in) {
             return new subTasks(in); 
         }

         public subTasks[] newArray(int size) {
             return new subTasks[size];
         }
	 }; 
		
		
}