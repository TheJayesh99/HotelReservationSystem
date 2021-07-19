package com.bridgelabz.hotelreservationsystem;

public class Hotel
{
	private String hotelName;
	private int weekDaysRateForRegular ;
	private int weekDaysRateForRewards ;
	private int weekendratesForRegular;
	private int weekendratesForRewards;
	
	public Hotel(String hotelName, int weekDaysRateForRegular, int weekDaysRateForRewards, int weekendratesForRegular,int weekendratesForRewards) 
	{
		super();
		this.hotelName = hotelName;
		this.weekDaysRateForRegular = weekDaysRateForRegular;
		this.weekDaysRateForRewards = weekDaysRateForRewards;
		this.weekendratesForRegular = weekendratesForRegular;
		this.weekendratesForRewards = weekendratesForRewards;
	}

	public String getHotelName() 
	{
		return hotelName;
	}

	public void setHotelName(String hotelName) 
	{
		this.hotelName = hotelName;
	}

	public int getWeekDaysRateForRegular() 
	{
		return weekDaysRateForRegular;
	}

	public void setWeekDaysRateForRegular(int weekDaysRateForRegular) 
	{
		this.weekDaysRateForRegular = weekDaysRateForRegular;
	}

	public int getWeekDaysRateForRewards() 
	{
		return weekDaysRateForRewards;
	}

	public void setWeekDaysRateForRewards(int weekDaysRateForRewards) 
	{
		this.weekDaysRateForRewards = weekDaysRateForRewards;
	}

	public int getWeekendratesForRegular() 
	{
		return weekendratesForRegular;
	}

	public void setWeekendratesForRegular(int weekendratesForRegular) 
	{
		this.weekendratesForRegular = weekendratesForRegular;
	}

	public int getWeekendratesForRewards()
	{
		return weekendratesForRewards;
	}

	public void setWeekendratesForRewards(int weekendratesForRewards) 
	{
		this.weekendratesForRewards = weekendratesForRewards;
	}

	@Override
	public String toString() 
	{
		return "Hotel [hotelName=" + hotelName + ", weekDaysRateForRegular=" + weekDaysRateForRegular
				+ ", weekDaysRateForRewards=" + weekDaysRateForRewards + ", weekendratesForRegular="
				+ weekendratesForRegular + ", weekendratesForRewards=" + weekendratesForRewards + "]";
	}
	
	
}
