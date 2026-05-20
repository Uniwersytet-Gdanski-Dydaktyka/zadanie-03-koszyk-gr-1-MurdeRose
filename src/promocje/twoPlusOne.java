package promocje;

import koszyk.Koszyk;

public class twoPlusOne implements Promotion{
    @Override
    public void applyPromo(Koszyk k) {
        if(k.getContentsSize() >= 3){
            k.addDiscount("TwoPlusOne");
        }
    }
}
