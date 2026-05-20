package koszyk;
import java.util.*;

public class Koszyk {
    public Product[] contents = new Product[0];
    public String[] appliedPromotions = new String[0];

    private void sortByPriceDescAndNameAsc(){
        Arrays.sort(contents, Comparator.comparingDouble(Product::getCurrentPrice).reversed()
                        .thenComparing(Product::getName));
    }

    public void addProduct(Product p){
        if(p.price == 0 && !Objects.equals(p.name, "Kubek firmowy")){
            System.out.println("cena produktu 0.00 - nie można takiego dodać");
            return;
        }
        if(isEmpty()){
            Product[] newArray = new Product[]{p};
            contents = newArray;
            return;
        }
        Product[] newArray = new Product[contents.length+1];
        for (int i = 0; i < contents.length; i++) {
            newArray[i] = contents[i];
        }
        newArray[newArray.length-1] = p;
        contents = newArray;
        sortByPriceDescAndNameAsc();
    }

    public int findProductPosition(Product[] tab, Product p){
        for (int i = 0; i < tab.length; i++) {
            if(tab[i].code.equals(p.code) && tab[i].name.equals(p.name) && tab[i].price == p.price){
                return i;
            }
        }
        return -1;
    }

    public int findProductPositionFromName(Product[] tab, String n){
        for (int i = 0; i < tab.length; i++) {
            if(tab[i].name.equals(n)){
                return i;
            }
        }
        return -1;
    }

    public void removeProduct(Product p){
        if(isEmpty()){
            return;
        }
        int searchedIndex = findProductPosition(contents, p);
        if (searchedIndex == -1){
            return;
        }
        Product[] newArray = new Product[contents.length-1];
        for (int i = 0; i < contents.length-1; i++) {
            if(i < searchedIndex){
                newArray[i] = contents[i];
            }
            else{
                newArray[i] = contents[i+1];
            }
        }
        contents = newArray;
        sortByPriceDescAndNameAsc();
    }

    public boolean isEmpty(){
        return contents.length == 0;
    }

    public Product findCheapest(Product[] tab){
        Product cheapest = findMostExpensive(contents);
        for (int i = 1; i < tab.length; i++) {
            if(tab[i].price < cheapest.price && tab[i].priceDiscounted != 0.00){
                cheapest = tab[i];
            }
        }
        return cheapest;
    }

    public Product findMostExpensive(Product[] tab){
        if(isEmpty()){
            return null;
        }
        else if(contents.length == 1){
            return tab[0];
        }
        else{
            Product mostExp = tab[0];
            for (int i = 1; i < tab.length; i++) {
                if(tab[i].price > mostExp.price){
                    mostExp = tab[i];
                }
            }
            return mostExp;
        }
    }

    public double sumPrice(){
        double sum = 0.0;
        for (int i = 0; i < contents.length; i++) {
            sum += contents[i].getCurrentPrice();
        }
        return sum;
    }

    public boolean isPromoAlreadyApplied(String type){
        if(appliedPromotions.length == 0){
            return false;
        }

        for (int i = 0; i < appliedPromotions.length; i++) {
            if (appliedPromotions[i].equals(type)) {
                return true;
            }
        }
        return false;
    }

    public void addDiscount(String type){
        if(isEmpty()){
            appliedPromotions = new String[0];
            return;
        }
        if(isPromoAlreadyApplied(type)){
            System.out.println("promocja już jest zaaplikowana");
            return;
        }
        String[] newArray = new String[appliedPromotions.length+1];
        for (int i = 0; i < appliedPromotions.length; i++) {
            newArray[i] = appliedPromotions[i];
        }
        newArray[newArray.length-1] = type;
        appliedPromotions = newArray;

        switch(type){
            case "over300":
                for (Product p : contents) {
                    p.priceDiscounted = p.price * 0.95;
                }
                break;
            case "freeCup":
                addProduct(new Product("F00", "Kubek firmowy", 0.00));
                break;
            case "TwoPlusOne":
                int applyAmount = contents.length / 3;
                System.out.println(applyAmount);
                for(int i = 0; i < applyAmount; i++){
                    int j = findProductPosition(contents, findCheapest(contents));
                    contents[j].priceDiscounted = 0.00;
                }
                break;
            case "Coupon30OffBucket":
                int i = findProductPositionFromName(contents, "Bucket");
                contents[i].priceDiscounted = contents[i].price * 0.7;
                break;
            default:
                return;
        }
    }
}
