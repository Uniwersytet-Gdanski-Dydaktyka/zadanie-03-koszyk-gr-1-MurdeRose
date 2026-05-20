package promocje;

import koszyk.Koszyk;

public class freeCup implements Promotion{
    @Override
    public void applyPromo(Koszyk k) {
        double sum = k.sumPrice();
        if(sum > 200){
            k.addDiscount("freeCup");
        }
    }
}
