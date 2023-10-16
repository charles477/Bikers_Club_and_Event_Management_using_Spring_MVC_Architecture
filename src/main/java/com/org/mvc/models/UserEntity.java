package com.org.mvc.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name ="users")
public class UserEntity 
{
  @Id
  @GeneratedValue( strategy=GenerationType.IDENTITY)
  private Long id;
  private String username;
  private String email;
  private String password;
  @ManyToMany(fetch= FetchType.EAGER, cascade=CascadeType.ALL)
  @JoinTable(
		  name="user_roles",
		  joinColumns= {@JoinColumn(name="user_id", referencedColumnName = "id")},
		  inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName = "id")})
      private List<Role> roles= new ArrayList<>();
		 
  
  
  
}
