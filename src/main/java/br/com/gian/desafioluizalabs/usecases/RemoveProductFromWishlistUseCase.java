package br.com.gian.desafioluizalabs.usecases;

import br.com.gian.desafioluizalabs.domain.wishlist.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RemoveProductFromWishlistUseCase {
    @Autowired
    private WishlistService wishlistService;

    public void execute(String userId, String productId) {
        wishlistService.removeProductFromWishlist(userId, productId);
    }
}