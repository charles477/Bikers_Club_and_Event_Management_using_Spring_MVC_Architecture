package com.org.mvc.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.org.mvc.models.UserEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClubDto 
{
	
	private Long id;
	@NotEmpty(message="club title should not be empty")
	private String title;
	@NotEmpty(message="photoLink should not be empty")
	private String photoUrl;
	@NotEmpty(message="content  should not be empty")
	private String content;
	private LocalDateTime createdOn;
	private LocalDateTime updatedOn;
	private UserEntity createdBy;
   private List<EventDto> events;
}
