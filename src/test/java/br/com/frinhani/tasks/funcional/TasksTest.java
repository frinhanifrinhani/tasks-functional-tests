package br.com.frinhani.tasks.funcional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Frinhani\\dev\\java\\seleniumDrivers\\chromedriver.exe");
		WebDriver drive = new ChromeDriver();
		//DesiredCapabilities cap = DesiredCapabilities.chrome();
		//WebDriver drive = new RemoteWebDriver(new URL("http://10.0.0.109:4444/wd/hub"), cap);
		
		drive.navigate().to("http://10.0.0.109:8001/tasks/");
		drive.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return drive;
		
	}

	@Test
	public void deveSalvarTarefaComSucesso()  throws MalformedURLException  {
		
		WebDriver drive = acessarAplicacao();
		
		try {
			//clicar em Add todo
			drive.findElement(By.id("addTodo")).click();
		
			//escrever descricao
			drive.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever data
			drive.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			drive.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String  message = drive.findElement(By.id("message")).getText();
			Assert.assertEquals("Success!",message);
		} finally {
			//fecha o navegador
			drive.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemDescricao()  throws MalformedURLException  {
		
		WebDriver drive = acessarAplicacao();
		
		try {
			//clicar em Add todo
			drive.findElement(By.id("addTodo")).click();
			
			//escrever data
			drive.findElement(By.id("dueDate")).sendKeys("10/10/2030");
			
			//clicar em salvar
			drive.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String  message = drive.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the task description",message);
		} finally {
			//fecha o navegador
			drive.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaSemData()  throws MalformedURLException  {
		
		WebDriver drive = acessarAplicacao();
		
		try {
			//clicar em Add todo
			drive.findElement(By.id("addTodo")).click();
		
			//escrever descricao
			drive.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//clicar em salvar
			drive.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String  message = drive.findElement(By.id("message")).getText();
			Assert.assertEquals("Fill the due date",message);
		} finally {
			//fecha o navegador
			drive.quit();
		}
	}
	
	@Test
	public void naoDeveSalvarTarefaComDataPassada()  throws MalformedURLException  {
		
		WebDriver drive = acessarAplicacao();
		
		try {
			//clicar em Add todo
			drive.findElement(By.id("addTodo")).click();
		
			//escrever descricao
			drive.findElement(By.id("task")).sendKeys("Teste via Selenium");
			
			//escrever data
			drive.findElement(By.id("dueDate")).sendKeys("10/10/2020");
			
			//clicar em salvar
			drive.findElement(By.id("saveButton")).click();
			
			//validar mensagem de sucesso
			String  message = drive.findElement(By.id("message")).getText();
			Assert.assertEquals("Due date must not be in past",message);
		} finally {
			//fecha o navegador
			drive.quit();
		}
	}
}
