package br.com.gian.desafioluizalabs.wishlist.controller;

import br.com.gian.desafioluizalabs.wishlist.model.WishlistItem;
import br.com.gian.desafioluizalabs.wishlist.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<WishlistItem> addProductToWishlist(@PathVariable String userId, @PathVariable String productId) {
        WishlistItem item = wishlistService.addProductToWishlist(userId, productId);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Void> removeProductFromWishlist(@PathVariable String userId, @PathVariable String productId) {
        wishlistService.removeProductFromWishlist(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistItem>> getWishlist(@PathVariable String userId) {
        List<WishlistItem> wishlist = wishlistService.getWishlist(userId);
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping("/{userId}/contains/{productId}")
    public ResponseEntity<Boolean> isProductInWishlist(@PathVariable String userId, @PathVariable String productId) {
        boolean isInWishlist = wishlistService.isProductInWishlist(userId, productId);
        return ResponseEntity.ok(isInWishlist);
    }
}

