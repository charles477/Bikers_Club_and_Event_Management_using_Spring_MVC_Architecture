package com.org.mvc.service;

import java.util.List;

import javax.validation.Valid;

import com.org.mvc.dto.EventDto;
import com.org.mvc.models.Event;

public interface EventService {
	
	void createEvent(Long clubID, EventDto eventDto);

	List<EventDto> findAllEvents();

	EventDto findByEventID(Long eventId);

	void updateEvent(@Valid EventDto eventDto);

	void deleteEvent(Long eventId);
	
	Event findByEventIDevent(Long eventId);

}
