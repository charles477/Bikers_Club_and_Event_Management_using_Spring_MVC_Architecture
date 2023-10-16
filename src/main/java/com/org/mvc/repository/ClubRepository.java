package com.org.mvc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.org.mvc.models.Club;


@Repository
public interface ClubRepository extends JpaRepository<Club, Long>
{
	
	Optional<Club> findByTitle(String url);
	 
	 @Query("SELECT c from Club c WHERE c.title LIKE CONCAT('%', :query, '%')")
	List<Club> searchClubs(String query);
	

}
