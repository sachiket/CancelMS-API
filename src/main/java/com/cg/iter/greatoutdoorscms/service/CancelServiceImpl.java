package com.cg.iter.greatoutdoorscms.service;


import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;


import com.cg.iter.greatoutdoorscms.beans.Orders;
import com.cg.iter.greatoutdoorscms.beans.ProductResponse;
import com.cg.iter.greatoutdoorscms.dto.CancelDTO;
import com.cg.iter.greatoutdoorscms.dto.OrderProductMapDTO;
import com.cg.iter.greatoutdoorscms.dto.ProductDTO;
import com.cg.iter.greatoutdoorscms.repository.CancelRepository;


@Service
public class CancelServiceImpl  implements CancelService{
	
	
	
	@Autowired
	RestTemplate rest;
	
	@Autowired
	CancelRepository cancelRepositoty;
   
    
    private String orderProductURL = "http://add-to-cart-service/order";
    private String productURL = "http://product-ms/product";

    
	@Override
	public String cancelOrder(String orderId , String userId) {
		
		long millis=System.currentTimeMillis();  
		Date currentDate = new java.util.Date(millis);
		
		Orders orders = rest.getForObject(orderProductURL+"/getOrders/byOrderId?orderId="+orderId, Orders.class);
		List<OrderProductMapDTO> list = orders.getOrders();
		Iterator<OrderProductMapDTO> itr = list.iterator();
		int index = 0;
		
		
		while (itr.hasNext()) {
			
			CancelDTO cancelOrder = new CancelDTO( orderId,userId, list.get(index).getProductId(),
					list.get(index).getProductUIN(), currentDate, 0);
			cancelRepositoty.save(cancelOrder);
			index++;
			itr.next();
		}
		
		
		MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
		parametersMap.add("orderId", orderId);

		
		return rest.postForObject(orderProductURL+"/cancelOrder", parametersMap, String.class);

		
	}

	@Override
	public String cancelProduct(String orderId, String userId, String productId) {
		
		long millis=System.currentTimeMillis();  
		Date currentDate = new java.util.Date(millis);
		
		Orders orders = rest.getForObject(orderProductURL+"/getOrders/byOrderIdProductId?orderId="+orderId+"&productId="+productId, Orders.class);
		List<OrderProductMapDTO> list = orders.getOrders();
		Iterator<OrderProductMapDTO> itr = list.iterator();
		int index = 0;
		
		
		while (itr.hasNext()) {
			
			CancelDTO cancelProduct = new CancelDTO( orderId,userId, list.get(index).getProductId(),
					list.get(index).getProductUIN(), currentDate, 0);
			cancelRepositoty.save(cancelProduct);
			index++;
			itr.next();
		}
		
		
		MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
		parametersMap.add("orderId", orderId);
		parametersMap.add("productId", productId);

		
		return rest.postForObject(orderProductURL+"/cancelProduct", parametersMap, String.class);
	}

	
	@Override
	public List<ProductResponse> getResponseProducts() {
		List<CancelDTO> cancelOrders = (List<CancelDTO>) cancelRepositoty.findAll();
		List<ProductResponse> response = new ArrayList<>();
		Iterator<CancelDTO> itr = cancelOrders.iterator();
		int index = 0;
		while (itr.hasNext()) {
			ProductDTO product = rest.getForObject(productURL+"/getProductById?productId="+cancelOrders.get(index).getProductid(),
					ProductDTO.class);
			ProductResponse productResponse = new ProductResponse(cancelOrders.get(index).getUserId(),
					cancelOrders.get(index).getOrderid(),cancelOrders.get(index).getProductid(), product.getProductName(),
					product.getProductURL() , product.getPrice());
			response.add(productResponse);
			index++;
			itr.next();
		}

		
		
		return response;
	}

}
