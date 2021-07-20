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
	
	public List<Entry<String, Integer>> FindCheapestHotel(String startDate,String endDate) 
	{
		LocalDate localStartDate = LocalDate.parse(startDate); 
		LocalDate localEndDate = LocalDate.parse(endDate);
		
		if (localStartDate.compareTo(localEndDate) <= 0 && localStartDate.compareTo(LocalDate.now()) >= 0)
		{
			HashMap<String,Integer> hotelPricesList = new HashMap<>(); //making a price list to have mapping with price and hotels
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
			
			//finding the hotel with minimum price
			int minvalue = hotelPricesList.entrySet().stream().min((entry1,entry2) -> entry1.getValue().compareTo(entry2.getValue())).get().getValue();
		    List<Entry<String, Integer>> minPricehotel = hotelPricesList.entrySet().stream().filter(price -> price.getValue().equals(minvalue)).collect(Collectors.toList());
			return minPricehotel;
		}
		else
		{
			System.out.println("Invalid dates");
			return null  ;
		}
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
}
