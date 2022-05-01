package com.flyaway.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="airline_table")
public class Airline {

	@Id
	@GeneratedValue
	@Column(name="airline_id")
	private int airlineId;
	
	@Column(name="airline_name")
	private String airlineName;
	
	@Column(name="scope")
	private String scope;
	
	@OneToMany(mappedBy="airline",cascade=CascadeType.ALL)
	List<Flight> flights;

	public int getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(int airlineId) {
		this.airlineId = airlineId;
	}

	public String getAirlineName() {
		return airlineName;
	}

	public void setAirlineName(String airlineName) {
		this.airlineName = airlineName;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public List<Flight> getFlights() {
		return flights;
	}
	public String getFlightList() {
		StringBuilder sb = new StringBuilder();
		List<Flight> flist = getFlights();
		for(Flight f: flist) {
			sb.append(f.getFlightId() + ",");
		}
		return sb.toString();
	}

	public void setFlights(List<Flight> flights) {
		this.flights = flights;
	}
	
}
