package com.bridgelabz.hotelreservationsystem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotelReservationSystemService 
{
	public List<Hotel> hotelsList = new ArrayList<Hotel>();
	
	//method to add hotel to hotels list
	public void addHotel(Hotel hotel) 
	{
		hotelsList.add(hotel);
	}
	
	public String FindCheapestHotel(String startDate,String endDate) 
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
					if (hotelPricesList.containsKey(hotel.getHotelName()))   //if hotel already exists then increment the hotel price
					{
						int exsistingAmount =  hotelPricesList.get(hotel.getHotelName());
						exsistingAmount += hotel.getWeekDaysRateForRegular(); 
						hotelPricesList.put(hotel.getHotelName(), exsistingAmount);													
					}
					else
					{							
						hotelPricesList.put(hotel.getHotelName(), hotel.getWeekDaysRateForRegular());  //adding new hotel to the hotel price list if hotel not in list						
					}
				}
				localStartDate=localStartDate.plusDays(1); //incrementing the days
			}
			
			//finding the hotel with minimum price
			return  hotelPricesList.entrySet().stream().min((entry1,entry2) -> entry1.getValue().compareTo(entry2.getValue())).get().getKey(); 
		}
		else
		{
			return "Invalid dates" ;
		}
	}
}
