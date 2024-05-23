package br.com.gian.desafioluizalabs.adapters.gateway;

import br.com.gian.desafioluizalabs.domain.wishlist.WishlistItem;

import java.util.List;
import java.util.Optional;

public interface WishlistGateway {

    WishlistItem save(WishlistItem item);

    void deleteByUserIdAndProductId(String userId, String productId);

    List<WishlistItem> findByUserId(String userId);

    Optional<WishlistItem> findByUserIdAndProductId(String userId, String productId);
}