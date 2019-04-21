package tests.sort;

import java.util.ArrayList;
import org.junit.runner.Result;

public class MainTester
{
  public MainTester() {}
  
  public static void main(String[] args)
  {
    Result result = org.junit.runner.JUnitCore.runClasses(new Class[] { IntegrationTest.class });
    Result result2 = org.junit.runner.JUnitCore.runClasses(new Class[] { UnitTest.class });
    
    int totalNumOfTests = result.getRunCount() + result2.getRunCount();
    int totalFailures = result.getFailureCount() + result2.getFailureCount();
    
    System.out.println("Total tests passed: " + (totalNumOfTests - totalFailures) + "/" + totalNumOfTests);
    
    ArrayList<org.junit.runner.notification.Failure> failures = new ArrayList();
    failures.addAll(result.getFailures());
    failures.addAll(result2.getFailures());
    
    for (org.junit.runner.notification.Failure failure : failures) {
      System.out.println(failure.toString());
    }
  }
}
