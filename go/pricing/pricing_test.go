package pricing

import "testing"

func TestPriceOrder_WithoutCoupon(t *testing.T) {
	o := Order{ID: 1, Customer: "test", Items: []Item{{"A01", 100_000, 2}, {"B01", 50_000, 1}}}
	got := PriceOrder(o)
	want := Bill{Subtotal: 250_000, Discount: 0, Tax: 22_500, Shipping: 40_000, Total: 312_500}
	if got != want {
		t.Errorf("PriceOrder() = %+v, want %+v", got, want)
	}
}

func TestPriceOrder_FixedCoupon(t *testing.T) {
	o := Order{ID: 2, Customer: "test", CouponCode: "FIX50", Items: []Item{{"A01", 300_000, 1}}}
	got := PriceOrder(o)
	if got.Discount != 50_000 || got.Total != 312_500 {
		t.Errorf("PriceOrder() = %+v, want discount 50000 and total 312500", got)
	}
}

func TestPriceOrder_PercentCoupon(t *testing.T) {
	o := Order{ID: 3, Customer: "test", CouponCode: "OFF10", Items: []Item{{"A01", 200_000, 1}}}
	got := PriceOrder(o)
	if got.Discount != 20_000 || got.Total != 236_200 {
		t.Errorf("PriceOrder() = %+v, want discount 20000 and total 236200", got)
	}
}

func TestTax_NinePercent(t *testing.T) {
	if got := TaxFor(1_000); got != 90 {
		t.Errorf("TaxFor(1000) = %d, want 90", got)
	}
}

func TestTax_RoundedToNearestToman(t *testing.T) {
	cases := []struct{ amount, want int64 }{
		{1_010, 91}, // 90.9
		{1_030, 93}, // 92.7
	}
	for _, c := range cases {
		if got := TaxFor(c.amount); got != c.want {
			t.Errorf("TaxFor(%d) = %d, want %d", c.amount, got, c.want)
		}
	}
}
