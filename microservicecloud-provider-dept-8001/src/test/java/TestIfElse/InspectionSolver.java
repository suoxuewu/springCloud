package TestIfElse;

public abstract class InspectionSolver {
    //策略模式
    //1、首先抽象业务处理器
    public abstract void slove(Long orderId,Long userId);
    public abstract String[] support();
}

