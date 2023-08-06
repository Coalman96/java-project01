package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class PurchaseServiceImpl implements PurchaseService{
	
	private ProductDAO productDAO;
	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		productDAO=new ProductDAO();
		purchaseDAO=new PurchaseDAO();
	}

	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception {
		productDAO.insertProduct(productVO);
		return productVO;
	}


	public PurchaseVO getPurchase(int prodNo) throws Exception {
		return productDAO.findProduct(prodNo);
	}

	public HashMap<String, Object> getPurchaseList(SearchVO searchVO, String str) throws Exception {
		return productDAO.getProductList(searchVO);
	}
	
	public HashMap<String, Object> getSaleList(SearchVO searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}

	public PurchaseVO updatePurchase(PurchaseVO purchaseVO) throws Exception {
		productDAO.updateProduct(productVO);
		System.out.println("6단계 임플시작");
		return productVO;
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		productDAO.updateProduct(productVO);
		System.out.println("6단계 임플시작");
		return productVO;
	}

}