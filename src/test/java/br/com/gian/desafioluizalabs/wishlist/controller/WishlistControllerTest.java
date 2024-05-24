package br.com.gian.desafioluizalabs.wishlist.controller;

import br.com.gian.desafioluizalabs.adapters.controller.WishlistController;
import br.com.gian.desafioluizalabs.domain.wishlist.WishlistItem;
import br.com.gian.desafioluizalabs.usecases.AddProductToWishlistUseCase;
import br.com.gian.desafioluizalabs.usecases.GetWishlistUseCase;
import br.com.gian.desafioluizalabs.usecases.IsProductInWishlistUseCase;
import br.com.gian.desafioluizalabs.usecases.RemoveProductFromWishlistUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WishlistControllerTest {

    @Mock
    private AddProductToWishlistUseCase addProductToWishlistUseCase;

    @Mock
    private RemoveProductFromWishlistUseCase removeProductFromWishlistUseCase;

    @Mock
    private GetWishlistUseCase getWishlistUseCase;

    @Mock
    private IsProductInWishlistUseCase isProductInWishlistUseCase;

    @InjectMocks
    private WishlistController wishlistController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addProductToWishlistReturnsAddedItem() {
        when(addProductToWishlistUseCase.execute(anyString(), anyString()))
                .thenReturn(new WishlistItem());

        WishlistItem result = wishlistController.addProductToWishlist("userId", "productId").getBody();
        assertNotNull(result);
    }

    @Test
    public void removeProductFromWishlistReturnsNoContent() {
        doNothing().when(removeProductFromWishlistUseCase).execute(anyString(), anyString());

        ResponseEntity<Void> response = wishlistController.removeProductFromWishlist("userId", "productId");

        assertEquals(ResponseEntity.noContent().build(), response);
    }

    @Test
    public void getWishlistReturnsListOfItems() {
        List<WishlistItem> items = Collections.singletonList(new WishlistItem());
        when(getWishlistUseCase.execute(anyString())).thenReturn(items);

        ResponseEntity<List<WishlistItem>> response = wishlistController.getWishlist("userId");

        assertEquals(ResponseEntity.ok(items), response);
    }

    @Test
    public void isProductInWishlistReturnsTrueWhenProductIsInWishlist() {
        when(isProductInWishlistUseCase.execute(anyString(), anyString())).thenReturn(true);

        ResponseEntity<Boolean> response = wishlistController.isProductInWishlist("userId", "productId");

        assertEquals(ResponseEntity.ok(true), response);
    }

    @Test
    public void isProductInWishlistReturnsFalseWhenProductIsNotInWishlist() {
        when(isProductInWishlistUseCase.execute(anyString(), anyString())).thenReturn(false);

        ResponseEntity<Boolean> response = wishlistController.isProductInWishlist("userId", "productId");

        assertEquals(ResponseEntity.ok(false), response);
    }
}
