package com.bridgelabz.hotelreservationsystemtesting;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.bridgelabz.hotelreservationsystem.Hotel;
import com.bridgelabz.hotelreservationsystem.HotelReservationSystemService;

public class HotelReservationSystemTesting 
{
	@Test
	public void getHotelDetails_WhenLakeWood_ShoudReturnLakewood()
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",160 , 110, 60, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		assertEquals("Lakewood",hotelService.hotelsList.get(0).getHotelName());
	}

	@Test
	public void getCheapestHotel_WhenDatesProper_ShoudReturnLakewood()
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",160 , 110, 60, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		assertEquals("Lakewood",hotelService.FindCheapestHotel("2021-07-24","2021-07-25"));
	}

	@Test
	public void getCheapestHotel_WhenDatesPreviousCurrentDate_ShoudReturnLakewood()
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",160 , 110, 60, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		assertEquals("Invalid dates",hotelService.FindCheapestHotel("2020-07-24","2020-07-25"));
	}

	@Test
	public void getWeekendrate_WhenBridgeWood_ShoudReturn60()
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",160 , 110, 60, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		assertEquals(60 , hotelService.hotelsList.get(1).getWeekendRatesForRegular());
	}
}
