package com.proj.cs309.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersService 
{
	@Autowired
	private UserRepo userRepository;
	
	public List<Users> getAllUsers() {
		List<Users> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}
	
	public Optional<Users> getUser(Integer id) {
		return userRepository.findById(id);
	}
	
	public Users getUserByName(String name) {
		return userRepository.findByUsername(name);
	}
	
	public void addUser(String username, String password, String city, String state, String typeOfUser) {
		Users u = new Users(username,password,city,state,typeOfUser);
		userRepository.save(u);
	}
	
	public void updateUser(Users user) {
		userRepository.save(user);
	}
	
	public void deleteUser(Integer id) {
		userRepository.deleteById(id);
	}

	public Users login(Users user) {
		String username = user.getUsername();
		String pword = user.getPassword();
		try {
			Users user1 = userRepository.findByUsername(username);
			if(user1.getPassword() != null && user1.getPassword().equals(pword)) {
				return user1;
			}
			else {
				return user;
			}
		}
		catch (Exception e) {
			return user;
		}
	}
	
	
	public String bq1(String uname, String q1) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setBusinessQ1(q1);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}
	
	public String bq2(String uname, String q2) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setBusinessQ2(q2);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}

	public String bq3(String uname, String q3) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setBusinessQ3(q3);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}
	
	public String bq4(String uname, String q4) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setBusinessQ4(q4);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}

	public String bq5(String username, String q5) {
			try {
				Users u = userRepository.findByUsername(username);
				u.setBusinessQ5(q5);
				userRepository.save(u);
				return "Success";
			}
			
			catch(Exception e) {
				return "Failed";
			}
		}
	
	public String epq1(String uname, String eventPlannerQ1) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setEventPlannerQ1(eventPlannerQ1);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}
	
	public String epq2(String uname, String q2) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setEventPlannerQ2(q2);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}

	public String epq3(String uname, String q3) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setEventPlannerQ3(q3);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}
	
	public String epq4(String uname, String q4) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setEventPlannerQ4(q4);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}

	public String epq5(String username, String q5) {
		try {
			try {
				Users u = userRepository.findByUsername(username);
				u.setEventPlannerQ5(q5);
				userRepository.save(u);
				return "Success";
			}
			
			catch(Exception e) {
				return "Failed";
			}
		}
		
		catch(Exception e) {
			return e.getMessage() + e.getLocalizedMessage() + e.toString();
		}
	}
	
	public String epq6(String uname, String q6) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setEventPlannerQ6(q6);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
		
	}
	
	public String epq7(String uname, String q7) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setEventPlannerQ7(q7);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}

	public String epq8(String uname, String q8) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setEventPlannerQ8(q8);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}
	
	public String epq9(String uname, String q9) {
		try {
			Users u = userRepository.findByUsername(uname);
			u.setEventPlannerQ9(q9);
			userRepository.save(u);
			return "Success";
		}
		
		catch(Exception e) {
			return "Failed";
		}
	}

	public String epq10(String username, String q10) {
		try {
			try {
				Users u = userRepository.findByUsername(username);
				u.setEventPlannerQ10(q10);
				userRepository.save(u);
				return "Success";
			}
			
			catch(Exception e) {
				return "Failed";
			}
		}
		
		catch(Exception e) {
			return e.getMessage() + e.getLocalizedMessage() + e.toString();
		}
	}
	
	public String epq11(String username, String q11) {
		try {
			try {
				Users u = userRepository.findByUsername(username);
				u.setEventPlannerQ11(q11);
				userRepository.save(u);
				return "Success";
			}
			
			catch(Exception e) {
				return "Failed";
			}
		}
		
		catch(Exception e) {
			return e.getMessage() + e.getLocalizedMessage() + e.toString();
		}
	}
}
