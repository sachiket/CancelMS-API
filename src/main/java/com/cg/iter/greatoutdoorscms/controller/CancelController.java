package com.cg.iter.greatoutdoorscms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.iter.greatoutdoorscms.beans.ProductResponse;

import com.cg.iter.greatoutdoorscms.service.CancelService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/cancel")
public class CancelController 
{
	@Autowired
	private CancelService cancelService;
	
	@PostMapping("/cancelOrder")
	public String cancelOrder(@RequestParam String orderId , @RequestParam String userId) throws Exception {
		return cancelService.cancelOrder(orderId,userId);
	}

	
	@PostMapping("/cancelProduct")
	String cancelProduct(@RequestParam String orderId, @RequestParam String userId,@RequestParam String productId)
	{
		return cancelService.cancelProduct(orderId, userId, productId);
		
	}
	
	@GetMapping("/getCancelProducts")
	List<ProductResponse> getResponseProducts(){
		return cancelService.getResponseProducts();
	}
   
}
