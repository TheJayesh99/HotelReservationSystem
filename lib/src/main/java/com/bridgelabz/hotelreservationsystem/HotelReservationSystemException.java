package com.bridgelabz.hotelreservationsystem;


public class HotelReservationSystemException extends RuntimeException
{
	public enum ExceptionType
	{
		ENTERED_NULL,ENTERED_EMPTY
	}
	
	public ExceptionType type;

	public HotelReservationSystemException(ExceptionType type,String message) {
		super(message);
		this.type = type;
	}

	
}
