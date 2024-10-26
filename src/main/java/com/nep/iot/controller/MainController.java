package com.nep.iot.controller;

import java.io.DataOutputStream;
import java.net.Socket;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nep.iot.model.SensorState;
import com.nep.iot.model.SensorStateDOA;
import com.nep.iot.model.Temperature;
import com.nep.iot.model.TemperatureDOA;

@Controller
public class MainController {
	
	@Value("${esp32.api.key}")
	private String esp32APIKEY;
	
	@Autowired
	TemperatureDOA tempDOA;
	
	@Autowired
	SensorStateDOA sensorStateDOA;

	@GetMapping(value="/")
	public String index(ModelMap modelMap) {
		List<SensorState> sensorStates = (List<SensorState>) sensorStateDOA.findAll();
		SensorState sensorState = null;
		if(!sensorStates.isEmpty()) {
			sensorState = sensorStates.get(0);
		}
		List<Temperature> tempData = (List<Temperature>) tempDOA.findFirst10ByOrderByIdDesc();
		modelMap.put("sensorState", sensorState);
		modelMap.put("tempData", tempData);
		return "index";
	}
	
	@GetMapping(value="/sensor/{action}")
	public String sensorControl(@PathVariable String action) {
		try {			
			Socket socket = new Socket("localhost", 9001);
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {						
						DataOutputStream output = new DataOutputStream(socket.getOutputStream());
						output.writeUTF(action);
						Thread.sleep(1000);
					} catch (Exception e) {						
					}
				}
				
			}).start();
		} catch (Exception e) {
			
		}
		return "redirect:/";
	}
	
	@PostMapping(value="/sensorData")
	@ResponseBody
	public String receiveData(
			@RequestParam String apiKey,
			@RequestParam double temp,
			@RequestParam double humid,
			@RequestParam boolean red,
			@RequestParam boolean yellow,
			@RequestParam boolean green,
			@RequestParam boolean sound,
			@RequestParam double ultrason,
			@RequestParam boolean motion,
			@RequestParam double water,
			@RequestParam boolean servo,
			@RequestParam boolean playMel
			) {
		if(apiKey.equals(esp32APIKEY)) {
			List<SensorState> sensorStates = (List<SensorState>) sensorStateDOA.findAll();
			SensorState sensorState;
			if(sensorStates.isEmpty()) {
				sensorState = new SensorState(red, yellow, green, sound, motion, servo, playMel);
			} else {
				sensorState = sensorStates.get(0);
				sensorState.setRedLed(red);
				sensorState.setYellowLed(yellow);
				sensorState.setGreenLed(green);
				sensorState.setSoundDetected(sound);
				sensorState.setMotionDetected(motion);
				sensorState.setServoOpen(servo);
			}
			sensorStateDOA.save(sensorState);
			Temperature newTemp = new Temperature(temp, humid);
			tempDOA.save(newTemp);
			return "Success";
		} else {
			return "Failed";
		}
	}
	
	@PostMapping(value="antah")
	@ResponseBody
	public String antah(
			@RequestParam String msg,
			@RequestParam String cakap
			) {
		return msg + " " + cakap;
	}
	
}
