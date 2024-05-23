package br.com.gian.desafioluizalabs.application;

import br.com.gian.desafioluizalabs.adapters.gateway.WishlistGateway;
import br.com.gian.desafioluizalabs.exception.WishlistDuplicatedIdException;
import br.com.gian.desafioluizalabs.exception.WishlistLimitExceededException;
import br.com.gian.desafioluizalabs.exception.WishlistProductNotFoundException;
import br.com.gian.desafioluizalabs.domain.wishlist.WishlistItem;
import br.com.gian.desafioluizalabs.domain.wishlist.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistServiceImpl implements WishlistService {

    private static final int MAX_WISHLIST_ITEMS = 20;

    @Autowired
    private WishlistGateway wishlistGateway;

    @Override
    public WishlistItem addProductToWishlist(String userId, String productId) {

        List<WishlistItem> wishlist = wishlistGateway.findByUserId(userId);

        if(wishlist.size() >= MAX_WISHLIST_ITEMS) {
            throw new WishlistLimitExceededException();
        }

        if(isProductInWishlist(userId, productId)) {
            throw new WishlistDuplicatedIdException();
        }

        WishlistItem item = new WishlistItem();
        item.setUserId(userId);
        item.setProductId(productId);
        return wishlistGateway.save(item);
    }

    @Override
    public void removeProductFromWishlist(String userId, String productId) {
        if(!isProductInWishlist(userId, productId)) {
            throw new WishlistProductNotFoundException(productId);
        }
        wishlistGateway.deleteByUserIdAndProductId(userId, productId);
    }

    @Override
    public List<WishlistItem> getWishlist(String userId) {
        return wishlistGateway.findByUserId(userId);
    }

    @Override
    public boolean isProductInWishlist(String userId, String productId) {
        return wishlistGateway.findByUserIdAndProductId(userId, productId).isPresent();
    }
}
