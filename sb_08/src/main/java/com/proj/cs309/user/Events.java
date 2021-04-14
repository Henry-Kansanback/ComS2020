package com.proj.cs309.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;
import org.springframework.stereotype.Component;

@Entity
//@Table(name="events")
public class Events {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String eventName;

    private String eventDescription;

    private String city;
    
    private String state;
 
    private String eventTime;
    
    private int signedUp;
    
    private Integer madeBy;
    
    public Events()
    {
    	signedUp = 0;
    }
    public Events(Integer madeBy) {
    	super();
    	this.eventName = null;
    	this.eventDescription = null;
    	this.city = null;
    	this.state = null;
    	this.eventTime = null;
    	this.madeBy = madeBy;
    }
    public Events(String name, String description, String city, String state, String eventTime, Integer madeBy) {
    	super();
    	this.eventName = name;
    	this.eventDescription = description;
    	this.city = city;
    	this.state = state;
    	this.eventTime = eventTime;
    	this.madeBy = madeBy;
    }
    
    public String getEventName() {
    	return this.eventName;
    }
    public String getEventDescription() {
    	return this.eventDescription;
    }
    public String getCity() {
    	return this.city;
    }
    public String getState() {
    	return this.state;
    }
    public String getEventTime() {
    	return this.eventTime;
    }
    public Integer getMadeBy() {
    	return this.madeBy;
    }
    public void setEventName(String name) {
    	this.eventName = name;
    }
    public void setEventDescription(String desc) {
    	this.eventDescription = desc;
    }
    public void setCity(String city) {
    	this.city = city;
    }
    public void setState(String state) {
    	this.state = state;
    }
    public void setEventTime(String time) {
    	this.eventTime = time;
    }
    
    public void setMadeBy(Integer madeBy) {
    	this.madeBy = madeBy;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getSigned() {
    	return this.signedUp;
    }
    
    public void incrementSigned() {
    	signedUp ++;
    }
}
