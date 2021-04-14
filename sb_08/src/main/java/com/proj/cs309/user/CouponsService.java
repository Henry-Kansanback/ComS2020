package com.proj.cs309.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class CouponsService 
{
	@Autowired
	private CouponsRepo CouponRepository;
	
	public List<Coupons> getAllCoupons() {
		List<Coupons> Coupons = new ArrayList<>();
		CouponRepository.findAll().forEach(Coupons::add);
		return Coupons;
	}
	public void addCoupons(Integer businessUserId, Integer parkingUserId, String couponTextDescription) {
		Coupons newCoupon = new Coupons(businessUserId, parkingUserId, couponTextDescription);
		CouponRepository.save(newCoupon);
	}
	public Optional<Coupons> getCoupon(Integer id) {
		return CouponRepository.findById(id);
	}
	
	public void removeCoupons() {
		CouponRepository.deleteAll();
	}
	
	public void updateCoupon(Coupons coupon) {
		CouponRepository.save(coupon);
	}
	
	public void deleteCoupon(int id) {
		CouponRepository.deleteById(id);
	}
	
	public List<Coupons> getListOfCouponsByParkingUser(Integer parkingUserId){
		List<Coupons> l = new ArrayList<>();
		List<Coupons> ret = new ArrayList<>();
		CouponRepository.findAll().forEach(l::add);
		for(int i = 0; i < l.size(); i++) {
			if(l.get(i).getParkingUserId().equals(parkingUserId)) {
				ret.add(l.get(i));
			}
		}
		return ret;
	}

}
