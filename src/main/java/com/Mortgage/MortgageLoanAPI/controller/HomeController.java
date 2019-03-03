package com.Mortgage.MortgageLoanAPI.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@RequestMapping("/")
	public String home() {
		return "<h1>Mortgage Loan Demo App</h1>";
	}
}
