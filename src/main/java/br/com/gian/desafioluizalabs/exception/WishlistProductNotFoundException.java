package br.com.gian.desafioluizalabs.exception;

public class WishlistProductNotFoundException extends WishlistException {
    public WishlistProductNotFoundException(String productId) {
        super("O productId " + productId + " não foi encontrado na lista de desejos desse usuário");
    }
}
