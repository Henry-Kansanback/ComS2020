package com.proj.cs309.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Coupons 
{
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    private Integer businessUserId;

    private Integer parkingUserId;

    private String couponDescription;
    
    public Coupons()
    {
    	
    }
    
    public Coupons(Integer businessId, Integer userId, String couponText) {
    	super();
    	businessUserId = businessId;
    	parkingUserId = userId;
    	couponDescription = couponText;
    }
	
    public void setId(Integer id) 
    {
    	this.id=id;
    }
	
    public Integer getId()
    {
    	return id;
    }
    
    public void setBusinessUserId(Integer businessUserId) 
    {
    	this.businessUserId=businessUserId;
    }
	
    public Integer getBusinessUserId()
    {
    	return businessUserId;
    }
    
    public void setParkingUserId(Integer parkingUserId) 
    {
    	this.parkingUserId=parkingUserId;
    }
	
    public Integer getParkingUserId()
    {
    	return parkingUserId;
    }
    
    public void setCouponDescription(String couponText)
    {
    	couponDescription = couponText;
    }
    
    public String getCouponDescription()
    {
    	return couponDescription;
    }
}
