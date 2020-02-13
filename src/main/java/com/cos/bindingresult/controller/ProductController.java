package com.cos.bindingresult.controller;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.bindingresult.dto.ResponseCM;
import com.cos.bindingresult.model.Product;
import com.cos.bindingresult.repository.ProductRepository;
/**
 * 
 * @author it
 * Logger File 남기고
 * 모든 메소드마다 자동화 시키고 싶다!
 * 
 */
@RestController
public class ProductController {
	@Autowired
	private ProductRepository productRepo;
	//@RequestBody = BufferedReader
	// mime 타입을 확인해서 json이면 키밸류형태로 변경해서 주입시켜주고
	// 잭슨이 발동해서 처리해줌
	
	//@Valid를 붙여야 개발자가 제어할 수 있다. 모델에 걸어준 제약중에 하나라도 오류 있으면 검사해라 임
	//검사해서 오류가 터지면 BindingResult로 모인다.
	@PostMapping("api/product")
	public ResponseEntity<?> 상품등록(@Valid @RequestBody Product product, BindingResult bindingResult) {
		
		productRepo.save(product);
		
		return new ResponseEntity<ResponseCM>(new ResponseCM(200,"ok"),HttpStatus.OK);
			
	}
	
	@Transactional
	@PutMapping("api/product/{id}")
	public ResponseEntity<?> 상품수정(@PathVariable int id,
			@Valid @RequestBody Product product, BindingResult bindingResult){
		Product persistenceProduct = productRepo.findById(id).get();
		persistenceProduct.setName(product.getName());
		persistenceProduct.setPrice(product.getPrice());
		persistenceProduct.setOrdering(product.getOrdering());
		
			return new ResponseEntity<ResponseCM>(new ResponseCM(200,"ok"),HttpStatus.OK);
		
	}
}
