package Runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features="src//test//resources//features//CaseStudy.feature",
        glue = {"stepDefs"},
        monochrome=true,
        dryRun=false,
        plugin= {"pretty",
        		"html:target//Reports//HtmlReport.html",
     //    		"json:targer//Reports//JsonReport.json",
//        		"usage:targer//Reports//UsageReport",
  //      		"rerun:target//failedScenarios.txt"
        		}
		
        )
public class running extends AbstractTestNGCucumberTests{

}

