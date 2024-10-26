package com.nep.iot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="sensorstates")
public class SensorState {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	long id;
	boolean redLed;
	boolean yellowLed;
	boolean greenLed;
	boolean soundDetected;
	boolean motionDetected;
	boolean servoOpen;
	boolean playMel;
	
	public SensorState() {}

	public SensorState(boolean redLed, boolean yellowLed, boolean greenLed, boolean soundDetected,
			boolean motionDetected, boolean servoOpen, boolean playMel) {
		this.redLed = redLed;
		this.yellowLed = yellowLed;
		this.greenLed = greenLed;
		this.soundDetected = soundDetected;
		this.motionDetected = motionDetected;
		this.servoOpen = servoOpen;
		this.playMel = playMel;
	}

	public boolean isPlayMel() {
		return playMel;
	}

	public void setPlayMel(boolean playMel) {
		this.playMel = playMel;
	}

	public boolean isRedLed() {
		return redLed;
	}

	public void setRedLed(boolean redLed) {
		this.redLed = redLed;
	}

	public boolean isYellowLed() {
		return yellowLed;
	}

	public void setYellowLed(boolean yellowLed) {
		this.yellowLed = yellowLed;
	}

	public boolean isGreenLed() {
		return greenLed;
	}

	public void setGreenLed(boolean greenLed) {
		this.greenLed = greenLed;
	}

	public boolean isSoundDetected() {
		return soundDetected;
	}

	public void setSoundDetected(boolean soundDetected) {
		this.soundDetected = soundDetected;
	}

	public boolean isMotionDetected() {
		return motionDetected;
	}

	public void setMotionDetected(boolean motionDetected) {
		this.motionDetected = motionDetected;
	}

	public boolean isServoOpen() {
		return servoOpen;
	}

	public void setServoOpen(boolean servoOpen) {
		this.servoOpen = servoOpen;
	}

	public long getId() {
		return id;
	}
	
	

}
