package com.proj.cs309.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class EventsService{
	@Autowired
	private EventRepo eventRepository;
	
	public List<Events> getAllEvents() {
		List<Events> events = new ArrayList<>();
		eventRepository.findAll().forEach(events::add);
		return events;
	}
	public void addEvent(String name, String description, String city, String state, String eventTime, Integer madeBy) {
		Events e = new Events(name, description, city, state, eventTime, madeBy);
		eventRepository.save(e);
	}
	
	public Optional<Events> getEvent(Integer id) {
		return eventRepository.findById(id);
	}
	
	public void removeEvents() {
		eventRepository.deleteAll();
	}
	
	public void updateEvents(Events event) {
		eventRepository.save(event);
	}
	
	public void deleteEvent(int id) {
		eventRepository.deleteById(id);
	}
	
	public List<Integer> getEventsBy(int id){
		List<Events> eventList = new ArrayList<>();
		List<Integer> ret = new ArrayList<>();
		eventRepository.findAll().forEach(eventList::add);
		for(int i = 0; i < eventList.size(); i++) {
			if(eventList.get(i).getMadeBy().equals(id)) {
				ret.add(eventList.get(i).getId());
			}
		}
		return ret;
	}
	
}
