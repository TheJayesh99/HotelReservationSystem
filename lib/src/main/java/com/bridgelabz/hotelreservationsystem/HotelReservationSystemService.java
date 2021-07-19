package com.bridgelabz.hotelreservationsystem;

import java.util.ArrayList;
import java.util.List;

public class HotelReservationSystemService 
{
	public List<Hotel> hotelsList = new ArrayList<Hotel>();
	
	//method to add hotel to hotels list
	public void addHotel(Hotel hotel) 
	{
		hotelsList.add(hotel);
	}
}
