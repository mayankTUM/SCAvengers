package SC13Project.Milestone1.FlightTicket;

import java.util.List;

//Please do not change the name of the package or the interface
public interface BookingFlightInfo {
    
	/**
	 * Get all the available flight info
	 * @param departure departure city
	 * @param destination destination city
	 * @param date departure date
	 * @return available flight info
	 * 
	 * 
	 */
	public List<FlightInfo> getFlightInfo(String departure, String destination, FlightTicketDate date);
	
	/**
	 * Book a flight
	 * @param flightNumber the number of the flight
	 * @param date the departure date
	 * @param seats the number of seats to book
	 * @return the booking ID
	 * @exception FlightUnAvailableException
	 * 
	 *  
	 */
	public String bookFlight(String flightNumber, FlightTicketDate date, int seats) throws FlightUnAvailableException;
	
	/**
	 * Cancel a booking
	 * @param bookingID the ID of the booking
	 * 
	 * 
	 */
	public void cancelBooking(String bookingID);
}
