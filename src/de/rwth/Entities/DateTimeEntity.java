package de.rwth.Entities;

import android.os.Parcel;
import android.os.Parcelable;

public class DateTimeEntity implements Parcelable {
private static int day;
private static int month;
private static int year;

private static int hours;
private static int minutes;


public static int getDay() {
	return day;
}
public static void setDay(int day) {
	DateTimeEntity.day = day;
}
public static int getMonth() {
	return month;
}
public static void setMonth(int month) {
	DateTimeEntity.month = month;
}
public static int getYear() {
	return year;
}
public static void setYear(int year) {
	DateTimeEntity.year = year;
}
public static int getHours() {
	return hours;
}
public static void setHours(int hours) {
	DateTimeEntity.hours = hours;
}
public static int getMinutes() {
	return minutes;
}
public static void setMinutes(int minutes) {
	DateTimeEntity.minutes = minutes;
}
public DateTimeEntity(){}
public DateTimeEntity(int _day,int _month,int _year,int _hours,int _minutes)
{
	this.day = _day;
	this.month = _month;
	this.year = _year;
	this.hours = _hours;
	this.minutes=_minutes;
	}

public  DateTimeEntity(Parcel in){
    int [] data = new int[3];

    in.readIntArray(data);
    this.day = data[0];
    this.month = data[1];
    this.year = data[2];
    this.hours = data[3];
    this.minutes = data[4];
}

@Override
public int describeContents() {
	// TODO Auto-generated method stub
	return 0;
}

@Override
public void writeToParcel(Parcel dest, int flags) {
	// TODO Auto-generated method stub
	dest.writeIntArray(new int[]{this.day,this.month,this.year,this.hours,this.minutes});
}
public Parcelable.Creator CREATOR = new Parcelable.Creator() {
	 public DateTimeEntity createFromParcel(Parcel in) {
        return new DateTimeEntity(in); 
    }

    public DateTimeEntity[] newArray(int size) {
        return new DateTimeEntity[size];
    }
}; 
	
	
	
	
}
