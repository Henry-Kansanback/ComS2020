package com.proj.cs309.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("CouponsRepo")
public interface CouponsRepo extends CrudRepository<Coupons, Integer> {
	 public Coupons findByparkingUserId(Integer parkingUserId);
}