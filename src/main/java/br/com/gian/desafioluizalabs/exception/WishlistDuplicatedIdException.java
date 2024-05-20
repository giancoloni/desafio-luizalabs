package br.com.gian.desafioluizalabs.exception;

public class WishlistDuplicatedIdException extends WishlistBadRequestException {
    public WishlistDuplicatedIdException() {
        super("O produto já está na lista de desejos");
    }
}
