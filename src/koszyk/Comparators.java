package koszyk;

import java.util.Comparator;

public class Comparators {
    public static final Comparator<Product> byDiscountPrice =
            Comparator.comparing(Product::getCurrentPrice);

    public static final Comparator<Product> byName =
            Comparator.comparing(Product::getName);
}
