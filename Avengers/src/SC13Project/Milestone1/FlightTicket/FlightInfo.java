package SC13Project.Milestone1.FlightTicket;

//Please do not change the name of the package or this class
public class FlightInfo {
   
	/**
	 * flight No.
	 */
	private String flightNo;
	/**
	 * departure city
	 */
	private String departure;
	/**
	 * destination city
	 */
	private String destination;
	/**
	 * available seats
	 */
	private int seats;
	/**
	 * the price
	 */
	private int price;
	
	public String getFlightNo() {
		return flightNo;
	}
	
	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
}
