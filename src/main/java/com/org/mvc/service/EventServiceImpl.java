package com.org.mvc.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.mvc.dto.EventDto;
import com.org.mvc.mapper.EventMapper;
import com.org.mvc.models.Club;
import com.org.mvc.models.Event;
import com.org.mvc.repository.ClubRepository;
import com.org.mvc.repository.EventRepository;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private ClubRepository clubRepository;
	
	
	
	@Override
	public void createEvent(Long clubID, EventDto eventDto) 
	{
		Club club= clubRepository.findById(clubID).get() ;
		Event event= EventMapper.mapToEvent(eventDto);
		event.setClub(club);
		eventRepository.save(event);
		
	}



	@Override
	public List<EventDto> findAllEvents() {
		List<Event> events= eventRepository.findAll();
		return events.stream().map(event->EventMapper.mapToEventDto(event)).collect(Collectors.toList());
	}



	@Override
	public EventDto findByEventID(Long eventId) {
		Event event =eventRepository.findById(eventId).get();
		return EventMapper.mapToEventDto(event);
		
	}
	@Override
	public Event findByEventIDevent(Long eventId) {
		Event event =eventRepository.findById(eventId).get();
		return event;
		
	}



	@Override
	public void updateEvent(@Valid EventDto eventDto) {
		Event event= EventMapper.mapToEvent(eventDto);
		eventRepository.save(event);
	}



	@Override
	public void deleteEvent(Long eventId) {
		
		eventRepository.deleteById(eventId);
		
	}



	
}
