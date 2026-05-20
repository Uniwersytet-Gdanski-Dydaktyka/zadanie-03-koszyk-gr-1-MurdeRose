package koszyk;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class produktTest {
    @Test
    void createProduct(){
        Product prod = new Product("A13", "kubek", 20.50);
        assertEquals("A13", prod.code);
        assertEquals("kubek", prod.name);
        assertEquals(20.50, prod.price);
    }

    @Test
    void setDiscountPrice(){
        Product prod = new Product("A13", "kubek", 20.50);
        double discount = 0.5;
        prod.priceDiscounted = prod.price*(1-discount);
        assertEquals(10.25, prod.priceDiscounted);
    }
}
