package com.flyaway.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CascadeType;

@Entity
@Table(name="Place_table")
public class Place {
	@javax.persistence.Id
	@GeneratedValue
	@Column(name="placeId")
	private int placeId;
	
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	
	@OneToMany(mappedBy="source")
	List<Flight> departingFlights;
	
	@OneToMany(mappedBy="destination")
	List<Flight> arrivingFlights;

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<Flight> getDepartingFlights() {
		return departingFlights;
	}
	public String getDeparting(List<Flight> flist) {
		StringBuilder sb = new StringBuilder();
		for(Flight f: flist){
			sb.append(f.getFlightId()+",");
		}
		return sb.toString();
	}

	public void setDepartingFlights(List<Flight> departingFlights) {
		this.departingFlights = departingFlights;
	}

	public List<Flight> getArrivingFlights() {
		return arrivingFlights;
	}
	
	public String getArriving(List<Flight> flist) {
		StringBuilder sb = new StringBuilder();
		for(Flight f: flist){
			sb.append(f.getFlightId()+",");
		}
		return sb.toString();
	}
	public void setArrivingFlights(List<Flight> arrivingFlights) {
		this.arrivingFlights = arrivingFlights;
	}
}
