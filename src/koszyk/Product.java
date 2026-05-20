package koszyk;

public class Product {

    String code = "";
    String name = "";
    double price = 0.0;
    double priceDiscounted = 0.0;

    public Product(String c, String n, double p){
        code = c;
        name = n;
        price = p;
        priceDiscounted = p;
    }

    public double getCurrentPrice(){
        return priceDiscounted;
    }

    public String getName() {
        return name;
    }
}
