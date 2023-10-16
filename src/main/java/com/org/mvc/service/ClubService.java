package com.org.mvc.service;

import java.util.List;

import com.org.mvc.dto.ClubDto;
import com.org.mvc.models.Club;

public interface ClubService 
{
	List<ClubDto> findAllClubs();

	Club saveClub(ClubDto clubDto);

	ClubDto findClubById(long clubId);

	void updateClub(ClubDto club);

	Club findClubByIdclub(long clubId);
	
	void delete(long clubId);

	List<ClubDto> searchClubs(String query);

	
}