package br.com.gian.desafioluizalabs.usecases;

import br.com.gian.desafioluizalabs.domain.wishlist.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IsProductInWishlistUseCase {
    @Autowired
    private WishlistService wishlistService;

    public boolean execute(String userId, String productId) {
        return wishlistService.isProductInWishlist(userId, productId);
    }
}