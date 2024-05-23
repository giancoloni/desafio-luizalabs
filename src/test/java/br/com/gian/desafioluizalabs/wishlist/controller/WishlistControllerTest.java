package br.com.gian.desafioluizalabs.wishlist.controller;

import br.com.gian.desafioluizalabs.adapters.controller.WishlistController;
import br.com.gian.desafioluizalabs.domain.wishlist.WishlistItem;
import br.com.gian.desafioluizalabs.domain.wishlist.WishlistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class WishlistControllerTest {

    @InjectMocks
    private WishlistController wishlistController;

    @Mock
    private WishlistService wishlistService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addProductToWishlistReturnsAddedItem() {
        WishlistItem item = new WishlistItem();
        when(wishlistService.addProductToWishlist(anyString(), anyString())).thenReturn(item);

        ResponseEntity<WishlistItem> response = wishlistController.addProductToWishlist("userId", "productId");

        assertEquals(ResponseEntity.ok(item), response);
    }

    @Test
    public void removeProductFromWishlistReturnsNoContent() {
        doNothing().when(wishlistService).removeProductFromWishlist(anyString(), anyString());

        ResponseEntity<Void> response = wishlistController.removeProductFromWishlist("userId", "productId");

        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    public void getWishlistReturnsListOfItems() {
        List<WishlistItem> items = Collections.singletonList(new WishlistItem());
        when(wishlistService.getWishlist(anyString())).thenReturn(items);

        ResponseEntity<List<WishlistItem>> response = wishlistController.getWishlist("userId");

        assertEquals(ResponseEntity.ok(items), response);
    }

    @Test
    public void isProductInWishlistReturnsTrueWhenProductIsInWishlist() {
        when(wishlistService.isProductInWishlist(anyString(), anyString())).thenReturn(true);

        ResponseEntity<Boolean> response = wishlistController.isProductInWishlist("userId", "productId");

        assertEquals(ResponseEntity.ok(true), response);
    }

    @Test
    public void isProductInWishlistReturnsFalseWhenProductIsNotInWishlist() {
        when(wishlistService.isProductInWishlist(anyString(), anyString())).thenReturn(false);

        ResponseEntity<Boolean> response = wishlistController.isProductInWishlist("userId", "productId");

        assertEquals(ResponseEntity.ok(false), response);
    }
}