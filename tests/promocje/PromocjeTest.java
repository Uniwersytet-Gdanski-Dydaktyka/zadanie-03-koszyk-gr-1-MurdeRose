package promocje;
import koszyk.Koszyk;
import koszyk.Product;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PromocjeTest {
    Koszyk k = new Koszyk();
    Promotion prom1 = new over300();
    Promotion prom2 = new freeCup();
    Promotion prom3 = new twoPlusOne();
    Promotion prom4 = new coupon30ProcentOffBucket();
    Product pOver300 = new Product("X01", "abc", 350.00);

    @Test
    public void ApplyPromotionEmptyCart(){
        prom1.applyPromo(k);
        assertEquals(0, k.appliedPromotions.length);
    }

    @Test
    public void ApplyExistingPromotion(){
        k.addProduct(pOver300);
        prom1.applyPromo(k);
        prom1.applyPromo(k);
        assertEquals(1, k.appliedPromotions.length);
    }

    @Test
    public void ApplyOver300Prom(){
        k.addProduct(pOver300);
        prom1.applyPromo(k);
        assertEquals(332.5, k.sumPrice());
    }
    @Test
    public void ApplyFreeCupProm(){
        k.addProduct(pOver300);
        prom2.applyPromo(k);
        assertEquals(2, k.contents.length);
    }

    @Test
    public void ApplyTwoPromotions(){
        k.addProduct(pOver300);
        prom1.applyPromo(k);
        prom2.applyPromo(k);
        assertEquals(332.5, k.sumPrice());
        assertEquals(2, k.contents.length);
    }

    @Test
    public void ApplyTwoPlusOne(){
        k.addProduct(new Product("A04", "a", 50.00));
        k.addProduct(new Product("A05", "b", 100.00));
        k.addProduct(new Product("A06", "c", 25.00));
        prom3.applyPromo(k);
        assertEquals(150.00, k.sumPrice());

    }
    @Test
    public void ApplyTwoPlusOneMoreThanOnce(){
        k.addProduct(new Product("A04", "a", 50.00));
        k.addProduct(new Product("A05", "b", 100.00));
        k.addProduct(new Product("A06", "c", 25.00));
        k.addProduct(new Product("A04", "a", 50.00));
        k.addProduct(new Product("A05", "b", 100.00));
        k.addProduct(new Product("A07", "d", 30.00));
        prom3.applyPromo(k);
        assertEquals(300.00, k.sumPrice());

    }

    @Test
    public void ApplyCouponBucket(){
        k.addProduct(new Product("A08", "Bucket", 15.00));
        prom4.applyPromo(k);
        assertEquals(10.5, k.sumPrice());
    }
}
