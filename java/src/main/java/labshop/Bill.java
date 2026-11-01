package labshop;

public record Bill(long subtotal, long discount, long tax, long shipping, long total) {
}
