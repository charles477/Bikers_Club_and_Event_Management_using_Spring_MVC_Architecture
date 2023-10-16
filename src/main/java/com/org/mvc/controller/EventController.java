package com.org.mvc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.org.mvc.dto.ClubDto;
import com.org.mvc.dto.EventDto;
import com.org.mvc.models.Event;
import com.org.mvc.models.UserEntity;
import com.org.mvc.security.SecurityUtil;
import com.org.mvc.service.EventService;
import com.org.mvc.service.UserService;

@Controller
public class EventController
{
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	
	
	@GetMapping("/events")
	public String eventList(Model model) {
		
		UserEntity user = new UserEntity();
		
		List<EventDto> events= eventService.findAllEvents();
		
		String username = SecurityUtil.getSessionUser();
        
        if(username != null) {
            user = userService.findbyUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
		
		model.addAttribute("events", events);
		return "events-list";
	}
	
	
	
	@GetMapping("events/{clubId}/new")
	public String createEventForm(@PathVariable("clubId") Long clubID , Model model)
	{
		Event event= new Event();
		model.addAttribute("clubId", clubID);
		model.addAttribute("event", event);
		return "events-create";
		
	}
	@PostMapping("events/{clubId}")
	public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("event") EventDto  eventDto, Model model,BindingResult result)
	
	
	
	{
		if(result.hasErrors()) {
			model.addAttribute("event", eventDto);
			return "clubs-create";
		}
		eventService.createEvent(clubId, eventDto);
		return "redirect:/clubs/" +clubId;
		
	}
	@GetMapping("events/{eventId}/edit")
	public String editEventForm(@PathVariable("eventId") long eventId, Model model)
{
		EventDto  event = eventService.findByEventID(eventId);
		
		model.addAttribute("event",event);
		return "events-edit";
		}
	
	@GetMapping("/events/{eventId}")
public String viewEvent(@PathVariable("eventId") Long eventId, Model model)
{
		UserEntity user = new UserEntity();
		
		
		EventDto eventDto= eventService.findByEventID(eventId);
		
	String username = SecurityUtil.getSessionUser();
        
        if(username != null) {
            user = userService.findbyUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("club", eventDto);
        model.addAttribute("user", user);
		model.addAttribute("event", eventDto);
		return "events-detail";
		
}
	@PostMapping("/events/{eventId}/edit")
	public String updateEvent(@PathVariable("eventId") Long eventId,
			
			@Valid @ModelAttribute("event") EventDto event,
			BindingResult result, Model model)
	{
		if(result.hasErrors()) {
			model.addAttribute("event", event);
			return "evebts-edit";
		}
		Event event1= eventService.findByEventIDevent(eventId);
		EventDto eventDto= eventService.findByEventID(eventId);
		event.setId(eventId);
		event.setCreatedOn(event1.getCreatedOn());
	   event.setClub(eventDto.getClub());
	   eventService.updateEvent(event);
		return "redirect:/events";
	}
	
	@GetMapping("/events/{eventId}/delete")
	public String deleteEvent(@PathVariable("eventId") Long eventId)
	{
		eventService.deleteEvent(eventId);
		return "redirect:/events";
	}
}
