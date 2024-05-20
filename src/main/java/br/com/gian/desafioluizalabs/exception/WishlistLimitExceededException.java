package br.com.gian.desafioluizalabs.exception;

public class WishlistLimitExceededException extends WishlistBadRequestException {
    public WishlistLimitExceededException() {
        super("A lista de desejos ultrapassou o limite de 20 produtos");
    }
}
