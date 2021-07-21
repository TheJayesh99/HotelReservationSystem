package com.bridgelabz.hotelreservationsystemtesting;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Test;

import com.bridgelabz.hotelreservationsystem.Hotel;
import com.bridgelabz.hotelreservationsystem.HotelReservationSystemException;
import com.bridgelabz.hotelreservationsystem.HotelReservationSystemException.ExceptionType;
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
		List<Entry<String, Integer>> cheapestHotel =  hotelService.findCheapestHotel("2021-07-23","2021-07-24","regular");
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
		assertEquals(null,hotelService.findCheapestHotel("2020-07-24","2020-07-25","regular"));
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
	public void getCheapestHotel_WhenFridayAndSaturday_ShoudReturnLakewoodAndBridgewood()
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 50, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		List<Entry<String, Integer>> cheapestHotel =  hotelService.findCheapestHotel("2021-07-23","2021-07-24","regular");
		assertEquals("Bridgewood",cheapestHotel.get(0).getKey() );
		assertEquals("Lakewood",cheapestHotel.get(1).getKey() );
	}

	@Test
	public void setRatingOfHotel_WhenLakewood_ShouldReturn3() 
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 50, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		hotelService.ratingsOfHotel(hotel1, 3);
		assertEquals(3, hotel1.getRating());
	}

	@Test
	public void getRatingOfHotel_WhenNotSet_ShouldReturn0() 
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 50, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		assertEquals(0, hotel1.getRating());
	}

	@Test
	public void getBestRatedHotel_WhenProperDates_ShouldReturnBridgewood()
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 50, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		hotelService.ratingsOfHotel(hotel1, 3);
		hotelService.ratingsOfHotel(hotel2, 4);
		hotelService.ratingsOfHotel(hotel3, 5);
		HashMap<Integer, List<Entry<String, Integer>>> hotels = hotelService.cheapestBestRatedHotel("2021-07-23","2021-07-24","regular");
		assertEquals("Bridgewood", hotels.get(200).get(0).getKey());
	}

	@Test
	public void getBestRatedHotel_WhenNotProperDates_ShouldReturnNull()
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 50, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		hotelService.ratingsOfHotel(hotel1, 3);
		hotelService.ratingsOfHotel(hotel2, 4);
		hotelService.ratingsOfHotel(hotel3, 5);
		HashMap<Integer, List<Entry<String, Integer>>> hotels = hotelService.cheapestBestRatedHotel("2020-07-23","20210-07-24","regular");
		assertEquals(null, hotels);
	}
	
	@Test
	public void getBestratingHotel_WhenProperDates_ShouldReturnRidgewood() 
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 60, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		hotelService.ratingsOfHotel(hotel1, 3);
		hotelService.ratingsOfHotel(hotel2, 4);
		hotelService.ratingsOfHotel(hotel3, 5);
		HashMap<Integer, List<Entry<String, Integer>>> hotels = hotelService.bestRatingHotel("2021-07-23","2021-07-24","regular");
		assertEquals("Ridgewood", hotels.get(370).get(0).getKey());
	}
	
	@Test
	public void getBestratingHotel_WhenNotProperDates_ShouldReturnNull() 
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 60, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		hotelService.ratingsOfHotel(hotel1, 3);
		hotelService.ratingsOfHotel(hotel2, 4);
		hotelService.ratingsOfHotel(hotel3, 5);
		HashMap<Integer, List<Entry<String, Integer>>> hotels = hotelService.bestRatingHotel("2020-07-23","2021-07-24","regular");
		assertEquals(null, hotels);
	}
	
	@Test
	public void getBestRatedHotelForRewarded_WhenProperDates_ShouldReturnRidgewood()
	{
		HotelReservationSystemService hotelService = new HotelReservationSystemService();
		Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
		Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 50, 50);
		Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
		hotelService.addHotel(hotel1);
		hotelService.addHotel(hotel2);
		hotelService.addHotel(hotel3);
		hotelService.ratingsOfHotel(hotel1, 3);
		hotelService.ratingsOfHotel(hotel2, 4);
		hotelService.ratingsOfHotel(hotel3, 5);
		HashMap<Integer, List<Entry<String, Integer>>> hotels = hotelService.cheapestBestRatedHotel("2021-07-23","2021-07-24","reward");
		assertEquals("Ridgewood", hotels.get(140).get(0).getKey());
	}
	
	@Test
	public void getCheapestHotel_WhenDatesNull_ShouldReturnException() 
	{
		try 
		{
			HotelReservationSystemService hotelService = new HotelReservationSystemService();
			Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
			Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 50, 50);
			Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
			hotelService.addHotel(hotel1);
			hotelService.addHotel(hotel2);
			hotelService.addHotel(hotel3);
			hotelService.ratingsOfHotel(hotel1, 3);
			hotelService.ratingsOfHotel(hotel2, 4);
			hotelService.ratingsOfHotel(hotel3, 5);
			hotelService.calculateHotelPricesForDates(null, null, null);
		}
		catch (HotelReservationSystemException e) 
		{
			assertEquals(ExceptionType.ENTERED_NULL, e.type);
		}
	}

	@Test
	public void getCheapestHotel_WhenDatesEmpty_ShouldReturnException() 
	{
		try 
		{
			HotelReservationSystemService hotelService = new HotelReservationSystemService();
			Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
			Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 50, 50);
			Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
			hotelService.addHotel(hotel1);
			hotelService.addHotel(hotel2);
			hotelService.addHotel(hotel3);
			hotelService.ratingsOfHotel(hotel1, 3);
			hotelService.ratingsOfHotel(hotel2, 4);
			hotelService.ratingsOfHotel(hotel3, 5);
			hotelService.calculateHotelPricesForDates("", "", null);
		}
		catch (HotelReservationSystemException e) 
		{
			assertEquals(ExceptionType.ENTERED_EMPTY, e.type);
		}
	}

	@Test
	public void getCheapestHotel_WhenCoustomerNull_ShouldReturnException() 
	{
		try 
		{
			HotelReservationSystemService hotelService = new HotelReservationSystemService();
			Hotel hotel1 = new Hotel("Lakewood",110 , 80, 90, 80);
			Hotel hotel2 = new Hotel("Bridgewood",150 , 110, 50, 50);
			Hotel hotel3 = new Hotel("Ridgewood",220 , 100, 150, 40);
			hotelService.addHotel(hotel1);
			hotelService.addHotel(hotel2);
			hotelService.addHotel(hotel3);
			hotelService.ratingsOfHotel(hotel1, 3);
			hotelService.ratingsOfHotel(hotel2, 4);
			hotelService.ratingsOfHotel(hotel3, 5);
			hotelService.calculateHotelPricesForDates("2021-07-23","2021-07-24", null);
		}
		catch (HotelReservationSystemException e) 
		{
			assertEquals(ExceptionType.ENTERED_NULL, e.type);
		}
	}
	

}
