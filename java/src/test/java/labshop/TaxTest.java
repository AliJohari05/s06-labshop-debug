package labshop;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TaxTest {
    @Test
    void taxIsNinePercent() {
        assertEquals(90, Pricing.taxFor(1_000));
    }

    @Test
    void taxIsRoundedToNearestToman() {
        assertEquals(91, Pricing.taxFor(1_010)); // 90.9
        assertEquals(93, Pricing.taxFor(1_030)); // 92.7
    }
}
