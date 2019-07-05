package com.example.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/product-service")
@CrossOrigin
public class ProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}
	List<Product> products=new ArrayList<>(Arrays.asList(new Product("1","Rexona",20,200)));
	@RequestMapping("/all")
	public List<Product> getProducts(){
		return products;
	}
    @RequestMapping(value = "/save",produces = "application/json",consumes = "application/json",method = RequestMethod.POST)
	public ResponseEntity<?> save(Product product){
		products.add(product);
		return ResponseEntity.ok(new AbstractMap.SimpleEntry("message","product saved successfully"));
	}
	@RequestMapping("/find/product/{productId}")
	public Optional<Product> getProducts(@PathVariable("productId") String productId){
		return products.stream().filter(p->p.id==productId).findAny();
	}

	private static class Product{
         private String id;
         private String title;
         private Integer price;
         private Integer stock;

		public Product(String id, String title, Integer price, Integer stock) {
			this.id = id;
			this.title = title;
			this.price = price;
			this.stock = stock;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public Integer getPrice() {
			return price;
		}

		public void setPrice(Integer price) {
			this.price = price;
		}

		public Integer getStock() {
			return stock;
		}

		public void setStock(Integer stock) {
			this.stock = stock;
		}
	}
}
