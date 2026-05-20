package promocje;
import koszyk.*;

public class over300 implements Promotion{
    @Override
    public void applyPromo(Koszyk k) {
        double sum = k.sumPrice();
        if(sum > 300){
            k.addDiscount("over300");
        }
    }
}
