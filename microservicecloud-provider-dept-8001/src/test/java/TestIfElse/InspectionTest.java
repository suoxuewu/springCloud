//package TestIfElse;
//
//import com.spring.springcloud.DeptProvider8001_App;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes= DeptProvider8001_App.class)
//public class InspectionTest {
//    @Autowired
//    private InspectionSolverChooser chooser;
//
//    @Test
//    public void test()throws Exception{
//        String taskType ="Shipping";
//        InspectionSolver solver = chooser.choose(taskType);
//        if(solver == null ){
//            new RuntimeException("实验不成功");
//        }
//        solver.slove(Long.parseLong("3"),Long.parseLong("3"));
//    }
//}
