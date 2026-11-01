namespace LabShop.Tests;

public class TaxTests
{
    [Fact]
    public void Tax_is_nine_percent()
    {
        Assert.Equal(90, Pricing.TaxFor(1_000));
    }

    [Fact]
    public void Tax_is_rounded_to_nearest_toman()
    {
        Assert.Equal(91, Pricing.TaxFor(1_010)); // 90.9
        Assert.Equal(93, Pricing.TaxFor(1_030)); // 92.7
    }
}
