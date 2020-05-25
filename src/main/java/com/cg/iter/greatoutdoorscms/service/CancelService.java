package com.cg.iter.greatoutdoorscms.service;


import java.util.List;

import com.cg.iter.greatoutdoorscms.beans.ProductResponse;


public interface CancelService 
{
	/*
	 * 
	 */
	String cancelOrder(String orderId , String userId) ;
    /*
     * 
     */
	String cancelProduct(String orderId, String userId, String productId) ;
	List<ProductResponse> getResponseProducts();

}
