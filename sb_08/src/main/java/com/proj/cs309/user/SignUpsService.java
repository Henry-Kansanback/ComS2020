package com.proj.cs309.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class SignUpsService{
	@Autowired
	private SignedRepo signRepository;
	
	public List<SignUps> getAllSignUps() {
		List<SignUps> signUps = new ArrayList<>();
		signRepository.findAll().forEach(signUps::add);
		return signUps;
	}
	public void addSignUps(Integer eventId, Integer businessId, Integer spots) {
		SignUps s = new SignUps(eventId, businessId, spots);
		signRepository.save(s);
	}
	public Optional<SignUps> getSignUps(Integer id) {
		return signRepository.findById(id);
	}
	
	public void removeSignUps() {
		signRepository.deleteAll();
	}
	
	public void updateSignUps(SignUps signup) {
		signRepository.save(signup);
	}
	
	public void deleteSignUps(int id) {
		signRepository.deleteById(id);
	}
	
	public List<SignUps> getByEvent(Integer Id){
		List<SignUps> l = new ArrayList<>();
		List<SignUps> ret = new ArrayList<>();
		signRepository.findAll().forEach(l::add);
		for(int i = 0; i < l.size(); i++) {
			if(l.get(i).getEventId().equals(Id)) {
				ret.add(l.get(i));
			}
		}
		return ret;
	}
}