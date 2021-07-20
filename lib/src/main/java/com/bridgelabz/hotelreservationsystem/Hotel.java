package com.bridgelabz.hotelreservationsystem;

public class Hotel
{
	private String hotelName;
	private int weekDaysRateForRegular ;
	private int weekDaysRateForRewards ;
	private int weekendRatesForRegular;
	private int weekendRatesForRewards;
	private int rating;
	

	public Hotel(String hotelName, int weekDaysRateForRegular, int weekDaysRateForRewards, int weekendratesForRegular,int weekendratesForRewards) 
	{
		super();
		this.hotelName = hotelName;
		this.weekDaysRateForRegular = weekDaysRateForRegular;
		this.weekDaysRateForRewards = weekDaysRateForRewards;
		this.weekendRatesForRegular = weekendratesForRegular;
		this.weekendRatesForRewards = weekendratesForRewards;
	}

	public int getRating() {
		return rating;
	}
	
	public void setRating(int rating) {
		this.rating = rating;
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

	public int getWeekendRatesForRegular() 
	{
		return weekendRatesForRegular;
	}

	public void setWeekendRatesForRegular(int weekendratesForRegular) 
	{
		this.weekendRatesForRegular = weekendratesForRegular;
	}

	public int getWeekendRatesForRewards()
	{
		return weekendRatesForRewards;
	}

	public void setWeekendRatesForRewards(int weekendRatesForRewards) 
	{
		this.weekendRatesForRewards = weekendRatesForRewards;
	}

	@Override
	public String toString() {
		return "HotelName=" + hotelName + ", weekDays Rate For Regular=" + weekDaysRateForRegular
				+ ", week Days Rate For Rewards=" + weekDaysRateForRewards + ", weekend Rates For Regular="
				+ weekendRatesForRegular + ", weekend Rates For Rewards=" + weekendRatesForRewards + ", rating=" + rating;
	}
	
	
}
