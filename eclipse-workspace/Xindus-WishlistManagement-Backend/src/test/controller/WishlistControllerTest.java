package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.example.demo.entity.Wishlist;
import com.example.demo.service.WishlistServiceImpl;

@ExtendWith(MockitoExtension.class)
public class WishlistControllerTest {
	
	@Mock
	private WishlistServiceImpl wishlistService;
	
	@InjectMocks
	private WishlistController wishlistCOntroller;
	
	@Test
	void add_Wishlist() {
		
		//Mock Authentication 
		Authentication authUser= new UsernamePasswordAuthenticationToken("RickyPonting@123.com", "123");
		
		 Mockito.when(wishlistService.createItemWishlist(anyLong()).thenReturn("Item added sucessfully");
		 
		 ResponseEntity<String> response = wishlistCOntroller.createWishlist(1);
		 
		 assertEquals(HttpStatus.CREATED,response.getStatusCode());
		 assertEquals("Item added sucessfully",response.getBody());
		 
		 verify(wishlistService,times(1)).createItemWishlist(1,authUser);
		 
	}

	
	 @Test
	   void get_UserWishlist() { 
		   Authentication authUser = new UsernamePasswordAuthenticationToken("RickyPonting@123.com", "123");
		    Wishlist ob = new Wishlist();
		    ob.setWishlistId(1);

		    Mockito.when(wishlistService.getItemWishlist(anyString())).thenReturn(ob);

		    ResponseEntity<Wishlist> response = wishlistCOntroller.getLoggedInUserWishlistHandler(authUser);

		    assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		    assertEquals(1, response.getBody().getWishlistId());

		    verify(wishlistService, times(1)).getItemWishlist("RickyPonting@123.com");
		   
	   } 
	 
	 @Test
	   void  remove_WishlistById() {
		   Authentication authUser= new UsernamePasswordAuthenticationToken("RickyPonting@123.com", "123");
		   
		   Mockito.when(wishlistService.deleteWishlistItemById(anyLong())).thenReturn("Item removed sucessfully");
		   ResponseEntity<String> response = wishlistCOntroller.deleteProductFromWishlistHandler(1,authUser);
		   
		   assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
		   assertEquals("Item removed sucessfully", response.getBody());
		   
		   verify(wishlistService,times(1)).removeItem(1, "RickyPonting@123.com");
	   }
	
	

}
