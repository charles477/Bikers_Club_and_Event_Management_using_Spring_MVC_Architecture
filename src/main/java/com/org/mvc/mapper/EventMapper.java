package com.org.mvc.mapper;

import com.org.mvc.dto.EventDto;
import com.org.mvc.models.Event;

public class EventMapper {

	
	
	public static Event mapToEvent(EventDto eventDto) 
	{
		return Event.builder()
				.id(eventDto.getId())
				.name(eventDto.getName())
				.startTime(eventDto.getStartTime())
				.endTime(eventDto.getEndTime())
				.photoUrl(eventDto.getPhotoUrl())
				.type(eventDto.getType())
				.createdOn(eventDto.getCreatedOn())
				.updatedOn(eventDto.getUpdatedOn())
				.location(eventDto.getLocation())
				.content(eventDto.getContent())
				.club(eventDto.getClub())
				.build();
		
	}
	
	public static EventDto mapToEventDto(Event event) 
	{
		return EventDto.builder()
				.id(event.getId())
				.name(event.getName())
				.startTime(event.getStartTime())
				.endTime(event.getEndTime())
				.photoUrl(event.getPhotoUrl())
				.type(event.getType())
				.content(event.getContent())
				.location(event.getLocation())
				.createdOn(event.getCreatedOn())
				.updatedOn(event.getUpdatedOn())
				.club(event.getClub())
				.build();
		
	}

}
