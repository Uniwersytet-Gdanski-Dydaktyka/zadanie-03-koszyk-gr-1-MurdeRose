package promocje;

import koszyk.Koszyk;

public class coupon30ProcentOffBucket implements Promotion{
    @Override
    public void applyPromo(Koszyk k) {
        k.addDiscount("Coupon30OffBucket");
    }
}
