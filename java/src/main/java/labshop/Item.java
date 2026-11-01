package labshop;

public class Item {
    private final String sku;
    private long unitPrice;
    private final long qty;

    public Item(String sku, long unitPrice, long qty) {
        this.sku = sku;
        this.unitPrice = unitPrice;
        this.qty = qty;
    }

    public String getSku() {
        return sku;
    }

    public long getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(long unitPrice) {
        this.unitPrice = unitPrice;
    }

    public long getQty() {
        return qty;
    }
}
