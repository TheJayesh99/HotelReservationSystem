package com.bridgelabz.hotelreservationsystem;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class HotelReservationSystemService 
{
	public List<Hotel> hotelsList = new ArrayList<Hotel>();

	//method to add hotel to hotels list
	public void addHotel(Hotel hotel) 
	{
		hotelsList.add(hotel);
	}

	//method to find cheapest hotel available in given date range
	public List<Entry<String, Integer>> findCheapestHotel(String startDate,String endDate) 
	{
		HashMap<String, Integer> hotelPricesList = calculateHotelPricesForDates(startDate, endDate);
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
	private HashMap<String, Integer> calculateHotelPricesForDates(String startDate, String endDate) {
		LocalDate localStartDate = LocalDate.parse(startDate); 
		LocalDate localEndDate = LocalDate.parse(endDate);

		HashMap<String,Integer> hotelPricesList = new HashMap<>(); //making a price list to have mapping with price and hotels
		if (localStartDate.compareTo(localEndDate) <= 0 && localStartDate.compareTo(LocalDate.now()) >= 0)
		{
			while(localStartDate.compareTo(localEndDate) <= 0)
			{
				for (Hotel hotel : hotelsList) 
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
				localStartDate=localStartDate.plusDays(1); //incrementing the days
			}

		}
		else
		{
			return null  ;
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
	public HashMap<Integer, List<Entry<String, Integer>>> cheapestBestRatedHotel(String startDate, String endDate) 
	{
		try 
		{
			HashMap<String, Integer> cheapHotelsWithBestRating = new HashMap<String, Integer>(); //created list for hotels having minimum rating
			List<Entry<String, Integer>> cheapHotels = findCheapestHotel(startDate, endDate);
			for (Hotel hotel : hotelsList)
			{
				for (Entry<String, Integer> cheapHotel : cheapHotels)
				{
					if (cheapHotel.getKey().equals(hotel.getHotelName())) 
					{
						cheapHotelsWithBestRating.put(hotel.getHotelName(),hotel.getRating());
					}
				}
			}		
			//calculating maximum rating
			int maxRating = cheapHotelsWithBestRating.entrySet().stream().max((entry1,entry2) -> entry1.getValue().compareTo(entry2.getValue())).get().getValue();
			List<Entry<String, Integer>> maxRatedHotel = cheapHotelsWithBestRating.entrySet().stream().filter(price -> price.getValue().equals(maxRating)).collect(Collectors.toList());
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
}
