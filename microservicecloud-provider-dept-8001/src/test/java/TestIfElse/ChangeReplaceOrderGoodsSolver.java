package TestIfElse;

import org.springframework.stereotype.Component;

@Component
public class ChangeReplaceOrderGoodsSolver extends InspectionSolver{


    @Override
    public void slove(Long orderId, Long userId) {
        System.out.println("1");
    }

    @Override
    public String[] support() {
        return new String[]{"ReplaceOrderGoods"};
    }
}
