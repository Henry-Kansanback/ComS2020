package com.proj.cs309.user;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository("UserRepo")
public interface UserRepo extends CrudRepository<Users, Integer> {
	 
	 public Users findByUsername(String username);

}