package SC13Project.Milestone1.FlightTicket;

import java.util.List;

//Please do not change the name of the package or this interface
//Please add here your implementation
public class FlightTicketImpl implements BookingFlightWS {

	@Override
	public List<FlightInfo> getFlightInfo(String departure, String destination,
			FlightTicketDate date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String bookFlight(String flightNumber, FlightTicketDate date,
			int seats) throws FlightUnAvailableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelBooking(String bookingID) {
		// TODO Auto-generated method stub
		
	}

}
