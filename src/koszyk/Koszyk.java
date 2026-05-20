package koszyk;
import java.util.*;
import static koszyk.Comparators.*;

public class Koszyk {
    ArrayList<Product> contents = new ArrayList<Product>();
    ArrayList<String> appliedPromotions = new ArrayList<String>();
    public int getContentsSize(){
        return contents.size();
    }
    public ArrayList<String> getAppliedPromotions(){
        return appliedPromotions;
    }
    private void sortCustom(Comparator<Product> c1, Comparator<Product> c2) {
        contents.sort(c1.thenComparing(c2));
    }

    public void addProduct(Product p){
        if(p.code == null || p.name == null){
            System.out.println("próba dodania produktu z polem null - nie można takiego dodać");
            return;
        }
        else if(p.price == 0 && !Objects.equals(p.name, "Kubek firmowy")){
            System.out.println("cena produktu 0.00 - nie można takiego dodać");
            return;
        }
        contents.add(p);
        sortCustom(byDiscountPrice.reversed(), byName);
    }

    public int findProductPosition(Product p){
        for (int i = 0; i < contents.size(); i++) {
            if(contents.get(i).code.equals(p.code) && contents.get(i).name.equals(p.name) && contents.get(i).price == p.price){
                return i;
            }
        }
        return -1;
    }

    public int findProductPositionFromName(String n){
        for (int i = 0; i < contents.size(); i++) {
            if(contents.get(i).name.equals(n)){
                return i;
            }
        }
        return -1;
    }

    public void removeProduct(Product p){
        if(isEmpty()){
            return;
        }
        int searchedIndex = findProductPosition(p);
        if (searchedIndex == -1){
            return;
        }
        contents.remove(searchedIndex);
        sortCustom(byDiscountPrice.reversed(), byName);
    }

    public boolean isEmpty(){
        return contents.isEmpty();
    }

    public Product findCheapest(){
        Product cheapest = findMostExpensive();
        for (int i = 1; i < contents.size(); i++) {
            if(contents.get(i).price < cheapest.price && contents.get(i).priceDiscounted != 0.00){
                cheapest = contents.get(i);
            }
        }
        return cheapest;
    }

    public Product findMostExpensive(){
        if(isEmpty()){
            return null;
        }
        else if(contents.size() == 1){
            return contents.getFirst();
        }
        else{
            Product mostExp = contents.getFirst();
            for (int i = 1; i < contents.size(); i++) {
                if(contents.get(i).price > mostExp.price){
                    mostExp = contents.get(i);
                }
            }
            return mostExp;
        }
    }

    public double sumPrice(){
        double sum = 0.0;
        for (int i = 0; i < contents.size(); i++) {
            sum += contents.get(i).getCurrentPrice();
        }
        return sum;
    }

    public boolean isPromoAlreadyApplied(String type){
        if(appliedPromotions.isEmpty()){
            return false;
        }

        for (int i = 0; i < appliedPromotions.size(); i++) {
            if (appliedPromotions.get(i).equals(type)) {
                return true;
            }
        }
        return false;
    }

    public void addDiscount(String type){
        if(isPromoAlreadyApplied(type)){
            System.out.println("promocja już jest zaaplikowana");
            return;
        }
        appliedPromotions.add(type);

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
                int applyAmount = contents.size() / 3;
                System.out.println(applyAmount);
                for(int i = 0; i < applyAmount; i++){
                    int j = findProductPosition(findCheapest());
                    contents.get(j).priceDiscounted = 0.00;
                }
                break;
            case "Coupon30OffBucket":
                int i = findProductPositionFromName("Bucket");
                contents.get(i).priceDiscounted = contents.get(i).price * 0.7;
                break;
            default:
                break;
        }
        sortCustom(byDiscountPrice.reversed(), byName);
    }
}
