package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Pattern;
import com.example.demo.service.DateCalcService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/date-calculation/input")
@RequiredArgsConstructor
@Controller
public class InputController {
	
	private final DateCalcService dateCalcService;
	
	@GetMapping
	public String getAdd(Model model, Pattern pattern) {
		
		model.addAttribute("pattern", new Pattern());
		
		return "input";
	}
	
	@PostMapping
	public String postAdd(@Validated Pattern pattern,
			BindingResult bindingResult, Model model) {
		
		if(bindingResult.hasErrors()) {
			List<String> errorList = new ArrayList<String>();
			for(ObjectError error: bindingResult.getAllErrors()) {
				errorList.add(error.getDefaultMessage());
			}
			model.addAttribute("validationError", errorList);
			return "add";
		}
		
		dateCalcService.addPattern(pattern);
		
		return "index";
	}
}
