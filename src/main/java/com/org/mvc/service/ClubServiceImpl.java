package com.org.mvc.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.mvc.dto.ClubDto;
import com.org.mvc.mapper.ClubMapper;
import com.org.mvc.models.Club;
import com.org.mvc.models.UserEntity;
import com.org.mvc.repository.ClubRepository;
import com.org.mvc.repository.UserRepository;
import com.org.mvc.security.SecurityUtil;

@Service
public class ClubServiceImpl implements ClubService {

	@Autowired
	private ClubRepository clubRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<ClubDto> findAllClubs() {
		List<Club> clubs= clubRepository.findAll();
		return  clubs.stream().map((club)->ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
	}

	@Override
	public Club saveClub(ClubDto clubDto) {
		
		String username= SecurityUtil.getSessionUser();
		UserEntity user = userRepository.findByUsername(username);
	Club club=	ClubMapper.mapToClub(clubDto);
		club.setCreatedBy(user);
		return clubRepository.save(club);
	}

	@Override
	public ClubDto findClubById(long clubId) {
		Club club= clubRepository.findById(clubId).get();
		return ClubMapper.mapToClubDto(club);
	}
	
	@Override
	public Club findClubByIdclub(long clubId) {
		Club club= clubRepository.findById(clubId).get();
		return club;
	}

	@Override
	public void updateClub(ClubDto clubDto) {
		String username= SecurityUtil.getSessionUser();
		UserEntity user = userRepository.findByUsername(username);
		Club club= ClubMapper.mapToClub(clubDto);
		club.setCreatedBy(user);
		clubRepository.save(club);
		
	}


	@Override
	public void delete(long clubId) {
		clubRepository.deleteById(clubId);
		
	}

	@Override
	public List<ClubDto> searchClubs(String query) {
		List<Club> clubs= clubRepository.searchClubs(query);
		return clubs.stream().map(club->ClubMapper.mapToClubDto(club)).collect(Collectors.toList());
	}
	
	

}
