/**
 * 
 */
package com.fsdfinal.workoutapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsdfinal.workoutapi.service.WorkoutService;
import com.fsdfinal.workoutapi.to.WorkoutRequestTO;
import com.fsdfinal.workoutapi.to.WorkoutResponseTO;
import com.fsdfinal.workoutapi.valueobject.Workout;

/**
 * @author Fareedha
 *
 */
@RestController
@RequestMapping("/")
public class WorkoutController {
	
	@Autowired
	private WorkoutService workoutService;
	
	@RequestMapping(method = RequestMethod.GET, value = "/getWorkouts")
	public WorkoutResponseTO getWorkouts() 
	{
		WorkoutResponseTO workoutResponse = new WorkoutResponseTO();
		List<Workout> workouts = workoutService.getWorkouts();
		workoutResponse.setWorkouts(workouts);
		workoutResponse.setSuccess(true);
		workoutResponse.setMessage("Workout List");
		return workoutResponse;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/getWorkout/{id}")
	public WorkoutResponseTO getWorkout(@PathVariable("id") int workoutId) 
	{
		WorkoutResponseTO workoutResponse = new WorkoutResponseTO();
		 
		WorkoutRequestTO requestTO  = new WorkoutRequestTO();
		requestTO.setWorkoutId(workoutId);
		workoutResponse= workoutService.getWorkout(requestTO);
		 
		return workoutResponse;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/saveWorkout")
	public WorkoutResponseTO saveWorkout(@RequestBody WorkoutRequestTO workoutTO) 
	{
		WorkoutResponseTO workoutResponse = new WorkoutResponseTO();
		 
		workoutResponse= workoutService.saveWorkout(workoutTO);
		if(workoutResponse.isSuccess()){ 
			List<Workout> workouts = workoutService.getWorkouts();
			workoutResponse.setWorkouts(workouts);
		}
		return workoutResponse;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/deleteWorkout/{id}")
	public WorkoutResponseTO deleteWorkout(@PathVariable("id") int workoutId) 
	{
		WorkoutResponseTO workoutResponse = new WorkoutResponseTO();
		 
		WorkoutRequestTO requestTO  = new WorkoutRequestTO();
		requestTO.setWorkoutId(workoutId);
		
		workoutResponse= workoutService.deleteWorkout(requestTO);
		if(workoutResponse.isSuccess()){ 
			List<Workout> workouts = workoutService.getWorkouts();
			workoutResponse.setWorkouts(workouts);
		}
		return workoutResponse;
	}
	
}
