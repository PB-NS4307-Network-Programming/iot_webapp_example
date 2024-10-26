package com.nep.iot.model;

import java.sql.Timestamp;
import java.time.Instant;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="temps")
public class Temperature {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	double temp;
	double humid;
	@CreationTimestamp
	Instant timestamp;
	
	public Temperature() {}
	
	public Temperature(double temp, double humid) {
		this.temp = temp;
		this.humid = humid;
	}

	public long getId() {
		return id;
	}

	public double getTemp() {
		return temp;
	}

	public void setTemp(double temp) {
		this.temp = temp;
	}

	public double getHumid() {
		return humid;
	}

	public void setHumid(double humid) {
		this.humid = humid;
	}
	
	public Instant getTimestamp() {
		return timestamp;
	}

}
