package br.com.gian.desafioluizalabs.usecases;

import br.com.gian.desafioluizalabs.domain.wishlist.WishlistItem;
import br.com.gian.desafioluizalabs.domain.wishlist.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetWishlistUseCase {

    @Autowired
    private WishlistService wishlistService;

    public List<WishlistItem> execute(String userId) {
        return wishlistService.getWishlist(userId);
    }
}