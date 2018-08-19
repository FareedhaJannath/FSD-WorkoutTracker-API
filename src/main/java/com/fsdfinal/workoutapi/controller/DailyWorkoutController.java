/**
 * 
 */
package com.fsdfinal.workoutapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsdfinal.workoutapi.service.DailyWorkoutService;
import com.fsdfinal.workoutapi.service.WorkoutService;
import com.fsdfinal.workoutapi.to.DailyWorkoutRequestTO;
import com.fsdfinal.workoutapi.to.DailyWorkoutResponseTO;
import com.fsdfinal.workoutapi.valueobject.DailyWorkout;

/**
 * @author Fareedha
 *
 */
@RestController
@RequestMapping("/")
public class DailyWorkoutController {

	@Autowired
	private DailyWorkoutService dailyWorkoutService;
		

	@Autowired
	private WorkoutService workoutService;
 
	@RequestMapping(method = RequestMethod.GET, value = "/getDailyWorkouts")
	public DailyWorkoutResponseTO geDailytWorkouts() 
	{
		DailyWorkoutResponseTO dailyWorkoutResponse = new DailyWorkoutResponseTO();
		List<DailyWorkout> dailyWorkouts = dailyWorkoutService.getDailyWorkouts();
		dailyWorkoutResponse.setDailyWorkouts(dailyWorkouts);
		
		return dailyWorkoutResponse;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveDailyWorkout")
	public DailyWorkoutResponseTO saveDailyWorkout(@RequestBody DailyWorkoutRequestTO dailyWorkoutTO) 
	{
		DailyWorkoutResponseTO dailyWorkoutResponse = new DailyWorkoutResponseTO();
		 
		dailyWorkoutResponse= dailyWorkoutService.saveDailyWorkout(dailyWorkoutTO);
		dailyWorkoutResponse.setSuccess(true);
		dailyWorkoutResponse.setMessage("Daily Workout List");
		if(dailyWorkoutResponse.isSuccess()){ 
			List<DailyWorkout> dailyWorkouts = dailyWorkoutService.getDailyWorkouts();
			dailyWorkoutResponse.setDailyWorkouts(dailyWorkouts);
			dailyWorkoutResponse.setWorkouts(workoutService.getWorkouts());
		}
		return dailyWorkoutResponse;
	}

}
