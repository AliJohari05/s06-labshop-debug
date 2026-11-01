package labshop;

import java.util.List;
import java.util.Map;

/** Pricing rules for LabShop orders. All amounts are in toman. */
public final class Pricing {
    static final long BULK_MIN_QTY = 10;
    static final long BULK_PERCENT = 10;

    private static final Map<String, Coupon> COUPONS = Map.of(
            "OFF10", new Coupon("percent", 10),
            "OFF20", new Coupon("percent", 20),
            "FIX50", new Coupon("fixed", 50_000));

    private Pricing() {
    }

    public static Coupon findCoupon(String code) {
        return COUPONS.get(code);
    }

    public static Bill priceOrder(Order order) {
        long subtotal = 0;
        for (Item item : order.getItems()) {
            subtotal += item.getUnitPrice() * item.getQty();
        }
        long bulk = bulkDiscount(order.getItems());
        long coupon = couponDiscount(order, subtotal - bulk);
        long taxable = subtotal - bulk - coupon;
        long tax = taxFor(taxable);
        long shipping = shippingFor(taxable);
        return new Bill(subtotal, bulk + coupon, tax, shipping, taxable + tax + shipping);
    }

    // 10% off every line that has BULK_MIN_QTY units or more
    static long bulkDiscount(List<Item> items) {
        long discount = 0;
        for (Item item : items) {
            if (item.getQty() >= BULK_MIN_QTY) {
                discount += item.getUnitPrice() * item.getQty() * BULK_PERCENT / 100;
            }
        }
        return discount;
    }

    static long couponDiscount(Order order, long amount) {
        if (order.getCouponCode().isEmpty()) {
            return 0;
        }
        Coupon coupon = findCoupon(order.getCouponCode());
        if (coupon.kind().equals("fixed")) {
            return Math.min(coupon.value(), amount);
        }
        return applyPercent(order.getItems(), coupon.value());
    }

    // lower each unit price so the invoice shows the discounted prices
    static long applyPercent(List<Item> items, long percent) {
        long discount = 0;
        for (Item item : items) {
            long cut = item.getUnitPrice() * percent / 100;
            item.setUnitPrice(item.getUnitPrice() - cut);
            discount += cut * item.getQty();
        }
        return discount;
    }

    // 9% VAT, rounded to the nearest toman
    public static long taxFor(long amount) {
        return (amount * 9 + 50) / 100;
    }

    public static long shippingFor(long amount) {
        if (amount >= 1_000_000) {
            return 0;
        }
        return 40_000;
    }
}
