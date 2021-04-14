package com.proj.cs309.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

@Entity
//@Table(name="users")
public class Users {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String city;
    
    private String state;
 
    private String typeOfUser;
    
    private String businessQ1,businessQ2,businessQ3, businessQ4,businessQ5;
    
    private String eventPlannerQ1,eventPlannerQ2,eventPlannerQ3, eventPlannerQ4,eventPlannerQ5,eventPlannerQ6,eventPlannerQ7,eventPlannerQ8, eventPlannerQ9,eventPlannerQ10,eventPlannerQ11;
    
    public Users()
    {
    	
    	
    }

    public Users(String username, String password, String city, String state, String typeOfUser,String businessQ1,String businessQ2,String businessQ3,String businessQ4,String businessQ5) {
		super();
		this.username = username;
		this.password = password;
		this.city = city;
		this.state = state;
		this.typeOfUser = typeOfUser;
		this.businessQ1 = businessQ1;
		this.businessQ2 = businessQ2;
		this.businessQ3 = businessQ3;
		this.businessQ4 = businessQ4;
		this.businessQ5 = businessQ5;
	}
    
    public Users(String username, String password, String city, String state, String typeOfUser,String eventPlannerQ1,String eventPlannerQ2,String eventPlannerQ3,String eventPlannerQ4,String eventPlannerQ5, String eventPlannerQ6,String eventPlannerQ7,String eventPlannerQ8,String eventPlannerQ9,String eventPlannerQ10, String eventPlannerQ11) {
		super();
		this.username = username;
		this.password = password;
		this.city = city;
		this.state = state;
		this.typeOfUser = typeOfUser;
		this.eventPlannerQ1 = eventPlannerQ1;
		this.eventPlannerQ2 = eventPlannerQ2;
		this.eventPlannerQ3 = eventPlannerQ3;
		this.eventPlannerQ4 = eventPlannerQ4;
		this.eventPlannerQ5 = eventPlannerQ5;
		this.eventPlannerQ6 = eventPlannerQ6;
		this.eventPlannerQ7 = eventPlannerQ7;
		this.eventPlannerQ8 = eventPlannerQ8;
		this.eventPlannerQ9 = eventPlannerQ9;
		this.eventPlannerQ10 = eventPlannerQ10;
		this.eventPlannerQ11 = eventPlannerQ11;
	}
    
    public Users(String username, String password, String city, String state, String typeOfUser) {
		super();
		this.username = username;
		this.password = password;
		this.city = city;
		this.state = state;
		this.typeOfUser = typeOfUser;
	}
    
    public Users(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public String getTypeOfUser() {
        return this.typeOfUser;
    }

    public void setTypeOfUser(String typeOfUser) {
        this.typeOfUser = typeOfUser;
    }
    
    public String getBusinessQ1() {
        return this.businessQ1;
    }

    public void setBusinessQ1(String businessQ1) {
        this.businessQ1 = businessQ1;
    }
    
    public String getBusinessQ2() {
        return this.businessQ2;
    }

    public void setBusinessQ2(String businessQ2) {
        this.businessQ2 = businessQ2;
    }
    
    public String getBusinessQ3() {
        return this.businessQ3;
    }

    public void setBusinessQ3(String businessQ3) {
        this.businessQ3 = businessQ3;
    }
    
    public String getBusinessQ4() {
        return this.businessQ4;
    }

    public void setBusinessQ4(String businessQ4) {
        this.businessQ4 = businessQ4;
    }
    
    public String getBusinessQ5() {
        return this.businessQ5;
    }

    public void setBusinessQ5(String businessQ5) {
        this.businessQ5 = businessQ5;
    }
    
    public String getEventPlannerQ1() {
        return this.eventPlannerQ1;
    }

    public void setEventPlannerQ1(String eventPlannerQ1) {
        this.eventPlannerQ1 = eventPlannerQ1;
    }
    
    public String getEventPlannerQ2() {
        return this.eventPlannerQ2;
    }

    public void setEventPlannerQ2(String eventPlannerQ2) {
        this.eventPlannerQ2 = eventPlannerQ2;
    }
    
    public String getEventPlannerQ3() {
        return this.eventPlannerQ3;
    }

    public void setEventPlannerQ3(String eventPlannerQ3) {
        this.eventPlannerQ3 = eventPlannerQ3;
    }
    
    public String getEventPlannerQ4() {
        return this.eventPlannerQ4;
    }

    public void setEventPlannerQ4(String eventPlannerQ4) {
        this.eventPlannerQ4 = eventPlannerQ4;
    }
    
    public String getEventPlannerQ5() {
        return this.eventPlannerQ5;
    }

    public void setEventPlannerQ5(String eventPlannerQ5) {
        this.eventPlannerQ5 = eventPlannerQ5;
    }
    
    public String getEventPlannerQ6() {
        return this.eventPlannerQ6;
    }

    public void setEventPlannerQ6(String eventPlannerQ6) {
        this.eventPlannerQ6 = eventPlannerQ6;
    }
    
    public String getEventPlannerQ7() {
        return this.eventPlannerQ7;
    }

    public void setEventPlannerQ7(String eventPlannerQ7) {
        this.eventPlannerQ7 = eventPlannerQ7;
    }
    
    public String getEventPlannerQ8() {
        return this.eventPlannerQ8;
    }

    public void setEventPlannerQ8(String eventPlannerQ8) {
        this.eventPlannerQ8 = eventPlannerQ8;
    }
    
    public String getEventPlannerQ9() {
        return this.eventPlannerQ9;
    }

    public void setEventPlannerQ9(String eventPlannerQ9) {
        this.eventPlannerQ9 = eventPlannerQ9;
    }
    
    public String getEventPlannerQ10() {
        return this.eventPlannerQ10;
    }

    public void setEventPlannerQ10(String eventPlannerQ10) {
        this.eventPlannerQ10 = eventPlannerQ10;
    }
    
    public String getEventPlannerQ11() {
        return this.eventPlannerQ11;
    }

    public void setEventPlannerQ11(String eventPlannerQ11) {
        this.eventPlannerQ11 = eventPlannerQ11;
    }
    
}

