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
import org.springframework.web.bind.annotation.RequestParam;

import com.org.mvc.dto.ClubDto;
import com.org.mvc.models.Club;
import com.org.mvc.models.UserEntity;
import com.org.mvc.security.SecurityUtil;
import com.org.mvc.service.ClubService;
import com.org.mvc.service.UserService;

@Controller
public class ClubController 
{
	@Autowired
	private ClubService clubService;
	@Autowired
	private UserService userService;
	
	
	
	 @GetMapping("/clubs")
	    public String listClubs(Model model) {
	        UserEntity user = new UserEntity();
	        List<ClubDto> clubs = clubService.findAllClubs();
	        String username = SecurityUtil.getSessionUser();
	        
	        if(username != null) {
	            user = userService.findbyUsername(username);
	            model.addAttribute("user", user);
	        }
	        model.addAttribute("user", user);
	        model.addAttribute("clubs", clubs);
	        return "clubs-list";
	    }
	 
	 
	@GetMapping("/clubs/new")
	public String createClubForm(Model model)
	{
		Club club= new Club();
		model.addAttribute("club", club);
		return "clubs-create";
	} 
	@GetMapping("/clubs/{clubId}")
	public String clubDetail(@PathVariable ("clubId") long clubId, Model model)
	{
		 UserEntity user = new UserEntity();
		
		
		ClubDto clubDto= clubService.findClubById(clubId);
		String username = SecurityUtil.getSessionUser();
        
        if(username != null) {
            user = userService.findbyUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
		
		
		
		model.addAttribute("club", clubDto);
		return "clubs-detail";
	}
	
	@GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId")Long clubId) {
		
        clubService.delete(clubId);
        return "redirect:/clubs";
    }
	
	 @GetMapping("/clubs/search")
	    public String searchClub(@RequestParam(value = "query") String query, Model model) {
		 
		 UserEntity user = new UserEntity();
	        List<ClubDto> clubs = clubService.searchClubs(query);
	        
	        String username = SecurityUtil.getSessionUser();
	        if(username != null) {
	            user = userService.findbyUsername(username);
	            model.addAttribute("user", user);
	        }
	        model.addAttribute("user", user);
	        
	        
	        
	        model.addAttribute("clubs", clubs);
	        return "clubs-list";
	    }
	
	@PostMapping("/clubs/new")
   public String saveClub(@Valid @ModelAttribute("club") ClubDto clubDto,
		   BindingResult result, Model model)
   {
		if(result.hasErrors()) {
			model.addAttribute("club",clubDto);
			return "clubs-create";
		}
		clubService.saveClub(clubDto);
		return "redirect:/clubs";
   }
	
	@GetMapping("clubs/{clubId}/edit")
	public String editClubForm(@PathVariable("clubId") long clubId, Model model)
{
		ClubDto  club = clubService.findClubById(clubId);
		
		model.addAttribute("club",club);
		return "clubs-edit";
		}
	
	@PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId,
                             @Valid @ModelAttribute("club") ClubDto club,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("club", club);
            return "clubs-edit";
        }
        Club club1= clubService.findClubByIdclub(clubId);
        club.setCreatedOn(club1.getCreatedOn());
        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }
	
	
}

