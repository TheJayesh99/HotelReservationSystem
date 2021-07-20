package com.bridgelabz.hotelreservationsystemtesting;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map.Entry;

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
		List<Entry<String, Integer>> cheapestHotel =  hotelService.FindCheapestHotel("2021-07-23","2021-07-24");
		assertEquals("Lakewood", cheapestHotel.get(0).getKey());
	}

	@Test
	public void getCheapestHotel_WhenDatesPreviousCurrentDate_ShoudReturnNull()
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",160 , 110, 60, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		assertEquals(null,hotelService.FindCheapestHotel("2020-07-24","2020-07-25"));
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

	@Test
	public void getCheapestHotel_WhenFridayAndSaturday_ShoudReturnLakewood()
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 50, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		List<Entry<String, Integer>> cheapestHotel =  hotelService.FindCheapestHotel("2021-07-23","2021-07-24");
		assertEquals("Bridgewood",cheapestHotel.get(0).getKey() );
		assertEquals("Lakewood",cheapestHotel.get(1).getKey() );
	}
}
