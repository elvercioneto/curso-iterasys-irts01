package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ModalProdutoPage {
	
	private WebDriver driver;
	
	private By mensagemProdutoAdicionado = By.id("myModalLabel");
	
	public ModalProdutoPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public String obterMensagemProdutoAdicionado() {
		return driver.findElement(mensagemProdutoAdicionado).getText();
	}

}
