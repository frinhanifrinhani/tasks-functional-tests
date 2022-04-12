package br.com.frinhani.tasks.prod;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class HealthCheckIT {

	@Test
	public void healthCheck() throws MalformedURLException {
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver drive = new RemoteWebDriver(new URL("http://10.0.0.113:4444/wd/hub"), cap);
		try {
			drive.navigate().to("http://10.0.0.113:9999/tasks/");
			drive.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			String version = drive.findElement(By.id("version")).getText();
			Assert.assertTrue(version.startsWith("build"));
		} finally {
			drive.quit();
		}
		
		
	}
}
