package com.org.mvc.mapper;

import java.util.stream.Collectors;

import static com.org.mvc.mapper.EventMapper.mapToEventDto;
import com.org.mvc.dto.ClubDto;
import com.org.mvc.models.Club;

public class ClubMapper {
	
	public static ClubDto mapToClubDto(Club club)
	{
		ClubDto clubDto=ClubDto.builder()
				.id(club.getId())
				.title(club.getTitle())
				.photoUrl(club.getPhotoUrl())
				.content(club.getContent())
				.createdOn(club.getCreatedOn())
				.updatedOn(club.getUpdatedOn())
				.createdBy(club.getCreatedBy())
				 .events(club.getEvents().stream().map((event) -> mapToEventDto(event)).collect(Collectors.toList()))
				.build();
		return clubDto;
		
	}
	
	
	public static Club mapToClub(ClubDto clubDto) {
		Club club=Club.builder()
				.id(clubDto.getId())
				.title(clubDto.getTitle())
				.photoUrl(clubDto.getPhotoUrl())
				.createdBy(clubDto.getCreatedBy())
				.content(clubDto.getContent())
				.createdOn(clubDto.getCreatedOn())
				.updatedOn(clubDto.getUpdatedOn())
				.build();
		return club;
	}

}
