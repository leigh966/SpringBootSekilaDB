package org.tsi.leigh.demo;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"}, features = "/src/test/resources/Cucumber/somethingtotest.feature", glue = "org.tsi.leigh.demo")
public class runCucumberTest
{
}
