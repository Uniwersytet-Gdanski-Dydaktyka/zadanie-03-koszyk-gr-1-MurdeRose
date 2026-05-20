package koszyk;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class KoszykTest {
    @Test
    void createCart(){
        Koszyk koszyk = new Koszyk();
        assertEquals(0, koszyk.contents.length);
    }
    @Test
    void addProductToEmptyCart(){
        Koszyk koszyk = new Koszyk();
        koszyk.addProduct(new Product("A01", "szczotka", 7.99));
        assertEquals(1, koszyk.contents.length);
    }
    @Test
    void addProductToFilledCart(){
        Koszyk koszyk = new Koszyk();
        koszyk.addProduct(new Product("A01", "szczotka", 7.99));
        Product p = new Product("A02", "kredka", 2.99);
        koszyk.addProduct(p);
        assertEquals(2, koszyk.contents.length);
        assertEquals(p, koszyk.contents[koszyk.contents.length-1]);
    }
    @Test
    void addProductWith0Price(){
        Koszyk koszyk = new Koszyk();
        koszyk.addProduct(new Product("A01", "szczotka", 0));
        assertEquals(0, koszyk.contents.length);
    }
    @Test
    void removeProductFromEmptyCart(){
        Koszyk koszyk = new Koszyk();
        koszyk.removeProduct(new Product("A01", "szczotka", 7.99));
        assertEquals(0, koszyk.contents.length);
    }
    @Test
    void removeProductFromCart(){
        Koszyk koszyk = new Koszyk();
        koszyk.addProduct(new Product("A01", "szczotka", 7.99));
        Product p = new Product("A02", "kredka", 2.99);
        koszyk.addProduct(p);
        koszyk.removeProduct(new Product("A01", "szczotka", 7.99));
        assertEquals(1, koszyk.contents.length);
        assertEquals(koszyk.contents[0], p);
    }

    @Test
    void findCheapestProduct(){
        Koszyk koszyk = new Koszyk();
        koszyk.addProduct(new Product("A01", "szczotka", 7.99));
        koszyk.addProduct(new Product("A02", "kredka", 2.99));
        koszyk.addProduct(new Product("A03", "olowek", 2.00));
        Product cheapest = koszyk.findCheapest(koszyk.contents);
        assertEquals(2.00, cheapest.price);
    }

    @Test
    void findMostExpensiveProduct(){
        Koszyk koszyk = new Koszyk();
        koszyk.addProduct(new Product("A01", "szczotka", 7.99));
        koszyk.addProduct(new Product("A02", "kredka", 2.99));
        koszyk.addProduct(new Product("A04", "kosiarka", 150.50));
        Product mostExpensive = koszyk.findMostExpensive(koszyk.contents);
        assertEquals(150.50, mostExpensive.price);
    }
}
