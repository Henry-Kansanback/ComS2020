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
public class SignUps {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private Integer eventId;

    private Integer businessId;

    private Integer spotsTotal;
    
    private Integer spotsLeft;
    
    public SignUps()
    {
    	
    }
    public SignUps(Integer event, Integer busi, Integer spots) {
    	super();
    	eventId = event;
    	businessId = busi;
    	spotsTotal = spots;
    	spotsLeft = spotsTotal;
    }
    
    public Integer getEventId() {
    	return eventId;
    }
    
    public Integer getId() {
    	return id;
    }
    public Integer getBusinessId() {
    	return businessId;
    }
    public Integer getSpotsTotal() {
    	return spotsTotal;
    }
    public Integer getSpotsLeft() {
    	return spotsLeft;
    }
    public void setEventId(Integer event) {
    	eventId = event;
    }
    public void setBusinessId(Integer busi) {
    	businessId = busi;
    }
    public void setId(Integer id) {
    	this.id = id;
    }
    public void setSpotsTotal(Integer total) {
    	spotsTotal = total;
    }
    public void setSpotsLeft(Integer left) {
    	spotsLeft = left;
    }
    
}
