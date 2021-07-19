package com.bridgelabz.hotelreservationsystemtesting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bridgelabz.hotelreservationsystem.Hotel;

public class HotelReservationSystemTesting 
{
	@Test
	public void getHotelDetails_WhenLakeWood_ShoudReturnLakewood()
	{
		Hotel hotel = new Hotel("Lakewood",110 , 80, 90, 80);
		assertEquals("Lakewood",hotel.getHotelName());
	}

	@Test
	public void getHotelDetails_WhenWeekdaysForRegular_ShoudReturn110()
	{
		Hotel hotel = new Hotel("Lakewood",110 , 80, 90, 80);
		assertEquals(110,hotel.getWeekDaysRateForRegular());
	}
	
	@Test
	public void getHotelDetails_WhenWeekdaysForRewarded_ShoudReturn80()
	{
		Hotel hotel = new Hotel("Lakewood",110 , 80, 90, 80);
		assertEquals(80,hotel.getWeekDaysRateForRewards());
	}
	
	@Test
	public void getHotelDetails_WhenWeekendsForRegular_ShoudReturn90()
	{
		Hotel hotel = new Hotel("Lakewood",110 , 80, 90, 80);
		assertEquals(90,hotel.getWeekendratesForRegular());
	}

	@Test
	public void getHotelDetails_WhenWeekendsForReward_ShoudReturn80()
	{
		Hotel hotel = new Hotel("Lakewood",110 , 80, 90, 80);
		assertEquals(80,hotel.getWeekendratesForRewards());
	}

}
