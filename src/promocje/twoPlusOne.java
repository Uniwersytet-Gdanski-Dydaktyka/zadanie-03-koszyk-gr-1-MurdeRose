package promocje;

import koszyk.Koszyk;

public class twoPlusOne implements Promotion{
    @Override
    public void applyPromo(Koszyk k) {
        if(k.contents.length >= 3){
            k.addDiscount("TwoPlusOne");
        }
    }
}
