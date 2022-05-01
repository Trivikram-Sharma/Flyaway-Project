package com.flyaway.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "flight_table")
public class Flight {

	@Id
	@Column(name = "flight_id")
	private String flightId;

	@ManyToOne
	@JoinColumn(name = "airlineId")
	Airline airline;

	@Column(name = "seats")
	private int seats = 0;

	@ManyToOne
	@JoinColumn(name = "source")
	Place source;

	@ManyToOne
	@JoinColumn(name = "destination")
	Place destination;

	@Column(name = "ticket_price")
	private double ticketPrice;

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Place getSource() {
		return source;
	}

	public void setSource(Place source) {
		this.source = source;
	}

	public Place getDestination() {
		return destination;
	}

	public void setDestination(Place destination) {
		this.destination = destination;
	}

	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

}
