package de.rwth.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class MainTask implements Parcelable {
	 String t_id;
	 String t_name;
	 String t_enddate;
	 String t_endtime;
	 public String getT_id() {
		return t_id;
	}
	public void setT_id(String t_id) {
		this.t_id = t_id;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_enddate() {
		return t_enddate;
	}
	public void setT_enddate(String t_enddate) {
		this.t_enddate = t_enddate;
	}
	public String getT_endtime() {
		return t_endtime;
	}
	public void setT_endtime(String t_endtime) {
		this.t_endtime = t_endtime;
	}
	
	 @Override
		public int describeContents() {
			// TODO Auto-generated method stub
			return 0;
		}
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			//dest.writeArray(new String[]{this.t_id,this.t_name,this.t_enddate,this.t_endtime});	
			dest.writeString(this.t_name);
			dest.writeString(this.t_enddate);
			dest.writeString(this.t_endtime);
		}
		public static final Parcelable.Creator<MainTask> CREATOR = new Parcelable.Creator<MainTask>() {
			 public MainTask createFromParcel(Parcel in) {
				 MainTask task = new MainTask();  
				 task.setT_name(in.readString()); 
				 task.setT_enddate(in.readString());
				 task.setT_endtime(in.readString());
				   
				   
			     return task;  
			 
				 
				// return new MainTask(in); 
	         }

	         public MainTask[] newArray(int size) {

	        	 return new MainTask[size];
	         }
		 }; 
}
