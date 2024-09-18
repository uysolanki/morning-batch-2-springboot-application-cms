package com.excelr.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.excelr.cms.entity.Customer;
import com.excelr.cms.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@RequestMapping("/showcustomerlist")
	public String showCustomerList(Model model)
	{
		List<Customer> customers=customerService.showCustomerList();
		model.addAttribute("customers",customers);
		return "customer-list";
	}
	
	
	@RequestMapping("/register")
	public String registerCustomer(Model model)
	{
		Customer c1=new Customer();
		model.addAttribute("customer",c1);
		return "customer-registration-form";
		
	}
	
	@PostMapping("/addcustomer")
	public String addCustomer(@ModelAttribute Customer customer)
	{
		customerService.addCustomer(customer);
		return "redirect:/showcustomerlist";
		
	}
}
