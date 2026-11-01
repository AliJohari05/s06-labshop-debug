namespace LabShop;

public class Item
{
    public Item(string sku, long unitPrice, long qty)
    {
        Sku = sku;
        UnitPrice = unitPrice;
        Qty = qty;
    }

    public string Sku { get; }
    public long UnitPrice { get; set; }
    public long Qty { get; }
}

public class Order
{
    public Order(int id, string customer, string couponCode)
    {
        Id = id;
        Customer = customer;
        CouponCode = couponCode;
    }

    public int Id { get; }
    public string Customer { get; }
    public string CouponCode { get; }
    public List<Item> Items { get; } = new();
}

public record Bill(long Subtotal, long Discount, long Tax, long Shipping, long Total);

// Kind is "percent" or "fixed".
public record Coupon(string Kind, long Value);
