package com.proj.cs309.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Entity;
import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {
	
	  @Autowired
	  private UsersService userService;
		
	  @Autowired
	  private EventsService eventService;
	  
	  @Autowired
	  private SignUpsService signupService;
	  
	  @Autowired
	  private CouponsService couponsService;

	  @GetMapping("/users")
		public List<Users> getAllUsers() {
		  return userService.getAllUsers();
		}
	  /**
	   * Unconditionally displays all event planners in the Users repository
	   * @return list of event planners from the Users repository
	   */
	  @GetMapping("/users/eventplanners")
		public List<Users> getPlanners() {
		  List<Users> PlannerList = new ArrayList<Users>();
		  for(Users u:userService.getAllUsers())
		  {
			  if(u.getTypeOfUser() != null && u.getTypeOfUser().equals("Event Planner")) {
				  PlannerList.add(u);
			  }
		  }
		  return PlannerList;
		}
	  /**
	   * Unconditionally displays all businesses in the Users repository
	   * @return
	   */
	  @GetMapping("/users/businesses")
		public List<Users> getBusinesses() {
		  List<Users> PlannerList = new ArrayList<Users>();
		  for(Users u:userService.getAllUsers())
		  {
			  if(u.getTypeOfUser() != null && u.getTypeOfUser().equals("Business")) {
				  PlannerList.add(u);
			  }
		  }
		  return PlannerList;
		}
	  /**
	   * Unconditionally displays all userParking users in the Users repository.
	   * @return
	   */
	  @GetMapping("/users/userParking")
		public List<Users> getUserParking() {
			  List<Users> PlannerList = new ArrayList<Users>();
			  for(Users u:userService.getAllUsers())
			  {
				  if(u.getTypeOfUser() != null && u.getTypeOfUser().equals("userParking")) {
					  PlannerList.add(u);
				  }
			  }
			  return PlannerList;
			}
		
		@GetMapping("/users/{id}")
		public Optional<Users> getUser(@PathVariable Integer id) {
			return userService.getUser(id);
		}
		
		@PostMapping("/addUser")
		public String addUser(@RequestBody Users user) {
			userService.addUser(user.getUsername(),user.getPassword(),user.getCity(),user.getState(),user.getTypeOfUser());
			return "Success";
		}
		
		@PostMapping("/login")
		public Users loginUser(@RequestBody Users user) {
			return userService.login(user);
		}
		
		@PostMapping("/businessQuestions")
		public String businessQs(@RequestBody Users user) {
			userService.bq1(user.getUsername(), user.getBusinessQ1());
			userService.bq2(user.getUsername(), user.getBusinessQ2());
			userService.bq3(user.getUsername(), user.getBusinessQ3());
			userService.bq4(user.getUsername(), user.getBusinessQ4());
			userService.bq5(user.getUsername(), user.getBusinessQ5());
			return "Success";
		}
		
		@GetMapping("/listOfBusinesses")
		public List<List<String>> listOfBusinesses() {
			  List<List<String>> BusinessInfoList = new ArrayList<List<String>>();
			  for(Users u:userService.getAllUsers())
			  {
				  if(u.getTypeOfUser() != null && u.getTypeOfUser().equals("Business")) {
				  List<String> businessInfo = new ArrayList<String>();
				  businessInfo.add(u.getBusinessQ1());
				  businessInfo.add(u.getBusinessQ2());
				  businessInfo.add(u.getBusinessQ3());
				  businessInfo.add(u.getBusinessQ4());
				  businessInfo.add(u.getBusinessQ5());
				  BusinessInfoList.add(businessInfo);
				  }
			  }
			  return BusinessInfoList;
			}
		
		/**
		 * Finds all Event planners in the Users repository
		 * @param user the current user that is logged in
		 * @return list of event planners, only shown if the user is itself an eventplanner or business
		 */
		@PostMapping("/showEventplanners")
		public List<Users> conditionalEventplanner(@RequestBody Users user) {
			List<Users> retList = new ArrayList<>();
			if(user.getTypeOfUser() != null && user.getTypeOfUser().equals("Event Planner") || user.getTypeOfUser().equals("Business")) {
				for(Users u:userService.getAllUsers())
				  {
					  if(u.getTypeOfUser() != null && u.getTypeOfUser().equals("Event Planner")) {
						  retList.add(u);
					  }
				  }
				return retList;
			}
			else if(user.getTypeOfUser() != null && user.getTypeOfUser().equals("userParking")) {
				return retList;
			}
			Users dummyUser = new Users("Invalid type of user", null, null, null, user.getTypeOfUser());
			retList.add(dummyUser);
			return retList;
		}
		
		/**
		 * Get method to retrieve all objects in the Events repository
		 * @return All objects in the Events repo.
		 */
		@GetMapping("/events")
		public List<Events> getAllEvents() {
		  return eventService.getAllEvents();
		}
		
		/**
		 * Adds an event object to the Events repository
		 * @param event Event information to be made into an Event object and stored in the Events repo.
		 * @return "Success" message.
		 */
		@PostMapping("/addEvent")
		public String addEvent(@RequestBody Events event) {
			eventService.addEvent(event.getEventName(), event.getEventDescription(), event.getCity(), event.getState(), event.getEventTime(), event.getMadeBy());
			return "Success";
		}
		
		/**
		 * Helper method for testing purposes only, used to remove all objects in the Events repository
		 */
		@GetMapping("/removeEvents")
		public void removeEvents() {
			eventService.removeEvents();
		}
		
		/**
		 * Gets all events in the Events repository with specified maker
		 * @param id ID of EventPlanner that created event
		 * @return all events in the Event repository made by the eventplanner with input ID
		 */
		@GetMapping("/events/madeBy/{id}")
		public List<Events> getEventsByMaker(@PathVariable Integer id){
			List<Events> madeByList = new ArrayList<>();
			  for(Events e:eventService.getAllEvents())
			  {
				  if(e.getMadeBy() != null && e.getMadeBy().equals(id)) {
					  madeByList.add(e);
				  }
			  }
			  return madeByList;
		}
		
		/**
		 * Gets all events in the Events Repository with specified State.
		 * @param state State for desired events
		 * @return list of events held in a specific state equal to that of the input.
		 */
		@GetMapping("/events/state/{state}")
		public List<Events> getEventsByState(@PathVariable String state){
			List<Events> stateList = new ArrayList<>();
			  for(Events e:eventService.getAllEvents())
			  {
				  if(e.getState() != null && e.getState().equals(state)) {
					  stateList.add(e);
				  }
			  }
			  return stateList;
		}
		
		/**
		 * Gets all events in the Events Repository with specified City.
		 * @param city City for desired events
		 * @return list of events held in a specific city equal to that of the input.
		 */
		@GetMapping("/events/city/{city}")
		public List<Events> getEventsByCity(@PathVariable String city){
			List<Events> cityList = new ArrayList<>();
			  for(Events e:eventService.getAllEvents())
			  {
				  if(e.getCity() != null && e.getCity().equals(city)) {
					  cityList.add(e);
				  }
			  }
			  return cityList;
		}
		
		/**
		 * Gets all events in the Events repository with specified event ID
		 * @param id Event ID for the desired event
		 * @return list of Events with ID equal to input ID
		 */
		@GetMapping("/events/id/{id}")
		public List<Events> getEventsById(@PathVariable Integer id){
			List<Events> idList = new ArrayList<>();
			  for(Events e:eventService.getAllEvents())
			  {
				  if(e.getId() != null && e.getId().equals(id)) {
					  idList.add(e);
				  }
			  }
			  return idList;
		}
		
		/**
		 * Get method to return the contents of the SignUps table.
		 * @return A list of all SignUp objects currently in the SignUps repository.
		 */
		@GetMapping("/signUps")
		public List<SignUps> getAllSignUps(){
			return signupService.getAllSignUps();
		}
		
		/**
		 * Uses the input to create a SignUp object to be stored in the SignUp repository.
		 * @param signup Inputs to be used in making of a new SignUp object stored in a repository.
		 * @return "Success" message.
		 */
		@PostMapping("/addSignUp")
		public String addSignUp(@RequestBody SignUps signup) {
			signupService.addSignUps(signup.getEventId(), signup.getBusinessId(), signup.getSpotsTotal());
			Events tmp = eventService.getEvent(signup.getEventId()).get();
			tmp.incrementSigned();
			eventService.updateEvents(tmp);
			return "Success";
		}
		
		/**
		 * Helper method for testing purposes only, used to clear the SignUps repository.
		 */
		@GetMapping("/removeSignUps")
		public void removeSignUps() {
			signupService.removeSignUps();
		}
		

		@PostMapping("/eventPlannerQuestions")
		public String eventPlannerQs(@RequestBody Users user) {
			userService.epq1(user.getUsername(), user.getEventPlannerQ1());
			userService.epq2(user.getUsername(), user.getEventPlannerQ2());
			userService.epq3(user.getUsername(), user.getEventPlannerQ3());
			userService.epq4(user.getUsername(), user.getEventPlannerQ4());
			userService.epq5(user.getUsername(), user.getEventPlannerQ5());
			userService.epq6(user.getUsername(), user.getEventPlannerQ6());
			userService.epq7(user.getUsername(), user.getEventPlannerQ7());
			userService.epq8(user.getUsername(), user.getEventPlannerQ8());
			userService.epq9(user.getUsername(), user.getEventPlannerQ9());
			userService.epq10(user.getUsername(), user.getEventPlannerQ10());
			userService.epq11(user.getUsername(), user.getEventPlannerQ11());
			return "Success";
		}
		
		/**
		 * Uses an event ID input to search for the IDs of businesses signed up for the specified event.
		 * @param id Event ID that represents an event.
		 * @return list of sign up pages for each business signed up for the event.
		 */
		@GetMapping("/Businesses/for/{id}")
		public List<SignUps> getBusinessesFor(@PathVariable Integer id){
			return signupService.getByEvent(id);
		}
		
		/**
		 * @author Eric Reusch
		 */
		@GetMapping("/Businesses/for/eventBy/{id}")
		public List<SignUps> getBusinessesForEventsBy(@PathVariable Integer id){
			List<Integer> events = eventService.getEventsBy(id);
			List<SignUps> ret = new ArrayList<>();
			
			for(int i = 0; i < events.size(); i++) {
				ret.addAll(signupService.getByEvent(events.get(i)));
			}
			
			return ret;
		}
		
		@GetMapping("/allCoupons")
		public List<Coupons> getAllCoupons(){
			return couponsService.getAllCoupons();
		}
		
		@PostMapping("/addCoupon")
		public String addCoupon(@RequestBody Coupons newCoupon) {
			couponsService.addCoupons(newCoupon.getBusinessUserId(), newCoupon.getParkingUserId(), newCoupon.getCouponDescription());
			return "Success";
		}
		
		@GetMapping("/removeCoupons")
		public void removeCoupon() {
			couponsService.removeCoupons();
		}
		
		@GetMapping("/coupons/forGivenUser/{parkingUserId}")
		public List<Coupons> getAllCouponsForGivenParkingUserId(@PathVariable Integer parkingUserId){
			return couponsService.getListOfCouponsByParkingUser(parkingUserId);
		}
}