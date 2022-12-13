package com.spark.lms.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.spark.lms.common.coonst;
import com.spark.lms.mdl.Member;
import com.spark.lms.service.membserv;

@Controller
@RequestMapping(value = "/member")
public class mambcntrl {

	@Autowired
	private membserv mambservc;
	
	@ModelAttribute(name = "memberTypes")
	public List<String> mamtyp() {
		return coonst.memtyp;
	}
	
	@RequestMapping(value = {"/", "/list"},  method = RequestMethod.GET)
	public String shomembpag(Model mdl) {
		mdl.addAttribute("members", mambservc.getal());
		return "/member/list";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addmembpag(Model mdl) {
		mdl.addAttribute("member", new Member());
		return "/member/form";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String edimembpag(@PathVariable(name = "id") Long id, Model mdl) {
		Member member = mambservc.get( id );
		if( member != null ) {
			mdl.addAttribute("member", member);
			return "/member/form";
		} else {
			return "redirect:/member/add";
		}
	}
	
	@RequestMapping(value = "/sve", method = RequestMethod.POST)
	public String savmemb(@Valid Member member, BindingResult bindingResult, final RedirectAttributes redirectAttributes) {
		if( bindingResult.hasErrors() ) {
			return "/member/form";
		}
		
		if( member.getId() == null ) {
			mambservc.adnw(member);
			redirectAttributes.addFlashAttribute("successMsg", "'" + member.getFirstName()+" "+member.getMiddleName() + "' is added as a new member.");
			return "redirect:/member/add";
		} else {
			Member updatedMember = mambservc.sve( member );
			redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + member.getFirstName()+" "+member.getMiddleName() + "' are saved successfully. ");
			return "redirect:/member/edit/" + updatedMember.getId();
		}
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String remmemb(@PathVariable(name = "id") Long id, Model mdl) {
		Member member = mambservc.get( id );
		if( member != null ) {
			if( mambservc.hasusg(member) ) {
				mdl.addAttribute("memberInUse", true);
				return shomembpag(mdl);
			} else {
				mambservc.del(id);
			}
		}
		return "redirect:/member/list";
	}
	
	
	
	
}
