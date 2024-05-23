package br.com.gian.desafioluizalabs.adapters.controller;

import br.com.gian.desafioluizalabs.domain.wishlist.WishlistItem;
import br.com.gian.desafioluizalabs.usecases.AddProductToWishlistUseCase;
import br.com.gian.desafioluizalabs.usecases.RemoveProductFromWishlistUseCase;
import br.com.gian.desafioluizalabs.usecases.GetWishlistUseCase;
import br.com.gian.desafioluizalabs.usecases.IsProductInWishlistUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private AddProductToWishlistUseCase addProductToWishlistUseCase;

    @Autowired
    private RemoveProductFromWishlistUseCase removeProductFromWishlistUseCase;

    @Autowired
    private GetWishlistUseCase getWishlistUseCase;

    @Autowired
    private IsProductInWishlistUseCase isProductInWishlistUseCase;

    @PostMapping("/{userId}/add/{productId}")
    public ResponseEntity<WishlistItem> addProductToWishlist(@PathVariable String userId, @PathVariable String productId) {
        WishlistItem item = addProductToWishlistUseCase.execute(userId, productId);
        return ResponseEntity.ok(item);
    }

    @DeleteMapping("/{userId}/remove/{productId}")
    public ResponseEntity<Void> removeProductFromWishlist(@PathVariable String userId, @PathVariable String productId) {
        removeProductFromWishlistUseCase.execute(userId, productId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<WishlistItem>> getWishlist(@PathVariable String userId) {
        List<WishlistItem> wishlist = getWishlistUseCase.execute(userId);
        return ResponseEntity.ok(wishlist);
    }

    @GetMapping("/{userId}/contains/{productId}")
    public ResponseEntity<Boolean> isProductInWishlist(@PathVariable String userId, @PathVariable String productId) {
        boolean isInWishlist = isProductInWishlistUseCase.execute(userId, productId);
        return ResponseEntity.ok(isInWishlist);
    }
}