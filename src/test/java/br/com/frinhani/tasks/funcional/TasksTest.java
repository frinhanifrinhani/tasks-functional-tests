package br.com.frinhani.tasks.funcional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TasksTest {
	
	public WebDriver acessarAplicacao() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Frinhani\\dev\\java\\seleniumDrivers\\chromedriver.exe");
		 
		WebDriver drive = new ChromeDriver();
		drive.navigate().to("http://localhost:8001/tasks/");
		drive.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		return drive;
		
	}

	@Test
	public void deveSalvarTarefaComSucesso() {
		
		WebDriver drive = acessarAplicacao();
		
		try {
			//clicar em Add todo
			drive.findElement(By.id("addTodo")).click();
		
			//escrever descrição
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
	public void naoDeveSalvarTarefaSemDescricao() {
		
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
	public void naoDeveSalvarTarefaSemData() {
		
		WebDriver drive = acessarAplicacao();
		
		try {
			//clicar em Add todo
			drive.findElement(By.id("addTodo")).click();
		
			//escrever descrição
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
	public void naoDeveSalvarTarefaComDataPassada() {
		
		WebDriver drive = acessarAplicacao();
		
		try {
			//clicar em Add todo
			drive.findElement(By.id("addTodo")).click();
		
			//escrever descrição
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
