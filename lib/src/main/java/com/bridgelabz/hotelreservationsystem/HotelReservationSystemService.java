package com.bridgelabz.hotelreservationsystem;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.bridgelabz.hotelreservationsystem.HotelReservationSystemException.ExceptionType;

public class HotelReservationSystemService 
{
	public List<Hotel> hotelsList = new ArrayList<Hotel>();

	//method to add hotel to hotels list
	public void addHotel(Hotel hotel) 
	{
		hotelsList.add(hotel);
	}

	//method to find cheapest hotel available in given date range
	public List<Entry<String, Integer>> findCheapestHotel(String startDate,String endDate,String CoustomerType) 
	{
		HashMap<String, Integer> hotelPricesList = calculateHotelPricesForDates(startDate, endDate, CoustomerType);
		//finding the hotel with minimum price
		try 
		{
			int minvalue = hotelPricesList.entrySet().stream().min((entry1,entry2) -> entry1.getValue().compareTo(entry2.getValue())).get().getValue();
			List<Entry<String, Integer>> minPricehotel = hotelPricesList.entrySet().stream().filter(price -> price.getValue().equals(minvalue)).collect(Collectors.toList());
			return minPricehotel;	
		} 
		catch (Exception e) 
		{
			System.out.println("Invalid dates");
			return null;
		}
	}

	//method to calculate hotels prices in given date range
	public HashMap<String, Integer> calculateHotelPricesForDates(String startDate, String endDate,String CoustomerType) throws HotelReservationSystemException
	{
		HashMap<String,Integer> hotelPricesList = new HashMap<>(); //making a price list to have mapping with price and hotels
		try {
			if (startDate.isEmpty() || endDate.isEmpty()) 
			{
				throw new HotelReservationSystemException(ExceptionType.ENTERED_EMPTY,"Dates cannot be empty string");
			}
			LocalDate localStartDate = LocalDate.parse(startDate); 
			LocalDate localEndDate = LocalDate.parse(endDate);	
			if (localStartDate.compareTo(localEndDate) <= 0 && localStartDate.compareTo(LocalDate.now()) >= 0)
			{
				while(localStartDate.compareTo(localEndDate) <= 0)
				{
					for (Hotel hotel : hotelsList) 
					{	
						try {
						if (CoustomerType.toLowerCase().equals("reward"))
						{
							if (localStartDate.getDayOfWeek() == DayOfWeek.SUNDAY|| localStartDate.getDayOfWeek() == DayOfWeek.SATURDAY )  //condition to check weather its a  weekend
							{
								setHotelsAndPrice(hotelPricesList, hotel, hotel.getWeekendRatesForRewards()); //for weekend we will update weekend price
							}
							else
							{						
								setHotelsAndPrice(hotelPricesList, hotel, hotel.getWeekDaysRateForRewards());  //for weekdays we will pass regular price 
							}
						}
						else
						{
							if (localStartDate.getDayOfWeek() == DayOfWeek.SUNDAY|| localStartDate.getDayOfWeek() == DayOfWeek.SATURDAY )  //condition to check weather its a  weekend
							{
								setHotelsAndPrice(hotelPricesList, hotel, hotel.getWeekendRatesForRegular()); //for weekend we will update weekend price
							}
							else
							{						
								setHotelsAndPrice(hotelPricesList, hotel, hotel.getWeekDaysRateForRegular());  //for weekdays we will pass regular price 
							}

						}
						}
						catch (Exception e) 
						{
							throw new HotelReservationSystemException(ExceptionType.ENTERED_NULL,"Coustomer Type cannot be null");
						}
					}
					localStartDate=localStartDate.plusDays(1); //incrementing the days
				}

			}	
			else
			{
				return null;
			}
		}
		catch (NullPointerException e) 
		{
			throw new HotelReservationSystemException(ExceptionType.ENTERED_NULL,"Dates cannot be null");
		}
		return hotelPricesList;

	}

	//method to update the hotels along with the prices  
	private void setHotelsAndPrice(HashMap<String, Integer> hotelPricesList, Hotel hotel, int rate) 
	{
		if (hotelPricesList.containsKey(hotel.getHotelName())) // if hotel already exists then we will update the price 
		{
			int exsistingAmount =  hotelPricesList.get(hotel.getHotelName());
			exsistingAmount += rate; 
			hotelPricesList.put(hotel.getHotelName(), exsistingAmount);													
		}
		else
		{							
			hotelPricesList.put(hotel.getHotelName(), rate); //here we added new hotel to list
		}
	}

	//method to set the rating of the hotel
	public void ratingsOfHotel(Hotel hotel,int rating)
	{
		hotel.setRating(rating);
	}

	//method to find Cheap Best Rating hotel
	public HashMap<Integer, List<Entry<String, Integer>>> cheapestBestRatedHotel(String startDate, String endDate,String CoustomerType) 
	{
		try 
		{
			HashMap<String, Integer> hotelsWithBestRating = new HashMap<String, Integer>(); //created list for hotels having minimum rating
			List<Entry<String, Integer>> cheapHotels = findCheapestHotel(startDate, endDate,CoustomerType);
			for (Hotel hotel : hotelsList)
			{
				for (Entry<String, Integer> cheapHotel : cheapHotels)
				{
					if (cheapHotel.getKey().equals(hotel.getHotelName())) 
					{
						hotelsWithBestRating.put(hotel.getHotelName(),hotel.getRating());
					}
				}
			}		
			//calculating maximum rating
			List<Entry<String, Integer>> maxRatedHotel = getBestRated(hotelsWithBestRating);
			HashMap<Integer ,List<Entry<String, Integer>>> bestRatedHotels = new HashMap<Integer, List<Entry<String,Integer>>>();
			bestRatedHotels.put(cheapHotels.get(0).getValue(), maxRatedHotel); //returning hash map of hotels having minimum price and same rating
			return bestRatedHotels;
		} 
		catch (Exception e) 
		{
			System.out.println("Invalid dates");
			return null;
		}
	}

	//method to find best rated hotel for given date range
	public HashMap<Integer, List<Entry<String, Integer>>> bestRatingHotel(String startDate,String endDate,String CoustomerType) 
	{
		try 
		{
			HashMap<String, Integer> hotelPricesList = calculateHotelPricesForDates(startDate, endDate, CoustomerType);
			HashMap<String, Integer> hotelsWithBestRating = new HashMap<String, Integer>(); //created list for hotels having minimum rating
			for (Hotel hotel : hotelsList) 
			{
				hotelsWithBestRating.put(hotel.getHotelName(),hotel.getRating());
			}
			List<Entry<String, Integer>> maxRatedHotel = getBestRated(hotelsWithBestRating);
			HashMap<Integer ,List<Entry<String, Integer>>> bestRatedHotels = new HashMap<Integer, List<Entry<String,Integer>>>();
			bestRatedHotels.put(hotelPricesList.get(maxRatedHotel.get(0).getKey()), maxRatedHotel); //returning hash map of hotels having minimum price and same rating
			return bestRatedHotels;
		} 
		catch (Exception e) 
		{
			System.out.println("Invalid dates");
			return null;
		}
	}

	//method to find highest rating among them
	private List<Entry<String, Integer>> getBestRated(HashMap<String, Integer> hotelsWithBestRating)
	{
		int maxRating = hotelsWithBestRating.entrySet().stream().max((entry1,entry2) -> entry1.getValue().compareTo(entry2.getValue())).get().getValue();
		List<Entry<String, Integer>> maxRatedHotel = hotelsWithBestRating.entrySet().stream().filter(price -> price.getValue().equals(maxRating)).collect(Collectors.toList());
		return maxRatedHotel;
	}
}
