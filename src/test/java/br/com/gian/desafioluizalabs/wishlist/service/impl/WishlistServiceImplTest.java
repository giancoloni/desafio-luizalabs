package br.com.gian.desafioluizalabs.wishlist.service.impl;

import br.com.gian.desafioluizalabs.application.WishlistServiceImpl;
import br.com.gian.desafioluizalabs.exception.WishlistDuplicatedIdException;
import br.com.gian.desafioluizalabs.exception.WishlistLimitExceededException;
import br.com.gian.desafioluizalabs.exception.WishlistProductNotFoundException;
import br.com.gian.desafioluizalabs.domain.wishlist.WishlistItem;
import br.com.gian.desafioluizalabs.infrastructure.repository.WishlistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class WishlistServiceImplTest {

    @InjectMocks
    private WishlistServiceImpl wishlistService;

    @Mock
    private WishlistRepository wishlistRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addProductToWishlistSuccessfully() {
        when(wishlistRepository.findByUserId(anyString())).thenReturn(Collections.emptyList());
        when(wishlistRepository.findByUserIdAndProductId(anyString(), anyString())).thenReturn(Optional.empty());
        when(wishlistRepository.save(any())).thenReturn(new WishlistItem());

        WishlistItem item = wishlistService.addProductToWishlist("userId", "productId");

        assertNotNull(item);
    }

    @Test
    public void addProductToWishlistThrowsLimitExceeded() {
        List<WishlistItem> items = Collections.nCopies(20, new WishlistItem());
        when(wishlistRepository.findByUserId(anyString())).thenReturn(items);

        assertThrows(WishlistLimitExceededException.class, () -> wishlistService.addProductToWishlist("userId", "productId"));
    }

    @Test
    public void addProductToWishlistThrowsDuplicatedId() {
        when(wishlistRepository.findByUserIdAndProductId(anyString(), anyString())).thenReturn(Optional.of(new WishlistItem()));

        assertThrows(WishlistDuplicatedIdException.class, () -> wishlistService.addProductToWishlist("userId", "productId"));
    }

    @Test
    public void removeProductFromWishlistSuccessfully() {
        when(wishlistRepository.findByUserIdAndProductId(anyString(), anyString())).thenReturn(Optional.of(new WishlistItem()));

        wishlistService.removeProductFromWishlist("userId", "productId");

        verify(wishlistRepository, times(1)).deleteByUserIdAndProductId(anyString(), anyString());
    }

    @Test
    public void removeProductFromWishlistThrowsProductNotFound() {
        when(wishlistRepository.findByUserIdAndProductId(anyString(), anyString())).thenReturn(Optional.empty());

        assertThrows(WishlistProductNotFoundException.class, () -> wishlistService.removeProductFromWishlist("userId", "productId"));
    }

    @Test
    public void getWishlistReturnsItems() {
        List<WishlistItem> items = Collections.singletonList(new WishlistItem());
        when(wishlistRepository.findByUserId(anyString())).thenReturn(items);

        List<WishlistItem> result = wishlistService.getWishlist("userId");

        assertEquals(items, result);
    }

    @Test
    public void isProductInWishlistReturnsTrue() {
        when(wishlistRepository.findByUserIdAndProductId(anyString(), anyString())).thenReturn(Optional.of(new WishlistItem()));

        boolean result = wishlistService.isProductInWishlist("userId", "productId");

        assertTrue(result);
    }

    @Test
    public void isProductInWishlistReturnsFalse() {
        when(wishlistRepository.findByUserIdAndProductId(anyString(), anyString())).thenReturn(Optional.empty());

        boolean result = wishlistService.isProductInWishlist("userId", "productId");

        assertFalse(result);
    }
}
