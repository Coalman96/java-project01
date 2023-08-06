package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;


public class ProductServiceImpl implements ProductService{
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		productDAO=new ProductDAO();
	}

	public ProductVO addProduct(ProductVO productVO) throws Exception {
		productDAO.insertProduct(productVO);
		return productVO;
	}


	public ProductVO getProduct(int prodNo) throws Exception {
		return productDAO.findProduct(prodNo);
	}

	public HashMap<String,Object> getProductList(SearchVO searchVO) throws Exception {
		return productDAO.getProductList(searchVO);
	}

	public ProductVO updateProduct(ProductVO productVO) throws Exception {
		productDAO.updateProduct(productVO);
		System.out.println("6단계 임플시작");
		return productVO;
	}

}