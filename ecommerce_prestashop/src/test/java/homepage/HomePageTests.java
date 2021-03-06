package homepage;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.List;

import org.junit.jupiter.api.Test;

import base.BaseTests;
import pages.LoginPage;
import pages.ModalProdutoPage;
import pages.ProdutoPage;


public class HomePageTests extends BaseTests {
	
	@Test
	public void testContarProdutos_oitoProdutosDiferentes() {
		carregarPaginaInicial();
		assertThat(homePage.contarProdutos(), is(8));
	}
	
	@Test
	public void testValidarCarrinhoZerado_ZeroItensNoCarrinho() {
		int produtosNoCarrinho = homePage.obterQuantidadeProdutosNoCarrinho();
		assertThat(produtosNoCarrinho, is(0));
	}
	ProdutoPage produtoPage;
	
	@Test
	public void testValidarDetalhesDoProduto_DescricaoEValorIguais() {
		int indice = 0;
		String nomeProduto_HomePage = homePage.obterNomeProduto(indice);
		String precoProduto_HomePage = homePage.obterPrecoProduto(indice);
		
		System.out.println(nomeProduto_HomePage);
		System.out.println(precoProduto_HomePage);
		
		produtoPage = homePage.clicarProduto(indice);
		
		String nomeProduto_ProdutoPage = produtoPage.obterNomeProduto();
		String precoProduto_ProdutoPage = produtoPage.obterPrecoProduto();
		
		System.out.println(nomeProduto_ProdutoPage);
		System.out.println(precoProduto_ProdutoPage);
		
		
		assertThat(nomeProduto_HomePage.toUpperCase(), is(nomeProduto_ProdutoPage.toUpperCase()));
		assertThat(precoProduto_HomePage, is(precoProduto_ProdutoPage));
		
	}
		
	LoginPage loginPage;
	@Test
	public void testLoginComSucesso_UsuarioLogado() {
		//Clicar no botão SignIn na HomePage
		loginPage = homePage.clicarBotaoSigIn();
		
		//Preencher usu�rio e senha
		loginPage.preencherEmail("elvercio@mail.com");
		loginPage.preencherPassword("12345");
		
		//Clicar no bot�o Sign in para logar
		loginPage.clicarBotaoSignIn();
		
		//Validar se o usu�ri est� logado de fao
		assertThat(homePage.estaLogado("Elvercio Neto"), is(true));
		
		carregarPaginaInicial();
	}
	
	@Test
	public void incluirProdutoNoCarrinho_ProdutoIncluidoComSucesso() {
		
		//--Pré-Condição
		//usuário logado
		if(!homePage.estaLogado("Elvercio Neto")) {
			testLoginComSucesso_UsuarioLogado();
		}
		
		//--Teste
		//selecionando produto
		testValidarDetalhesDoProduto_DescricaoEValorIguais();
		
		//Selecionar tamanho
		List<String> listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da Lista: " + listaOpcoes.size());
		
		produtoPage.selecionarOpcaoDropDown("M");
		
		listaOpcoes = produtoPage.obterOpcoesSelecionadas();
		
		System.out.println(listaOpcoes.get(0));
		System.out.println("Tamanho da Lista: " + listaOpcoes.size());
		
		//Selecionar cor
		produtoPage.selecionarCorPreta();
		
		//Selecionar quantidade
		produtoPage.alterarQuantidade(2);
		
		//Adicionar no carrinho
		
		ModalProdutoPage modalProdutoPage = produtoPage.clicarBotaoAddToCart();
		
		assertThat(modalProdutoPage.obterMensagemProdutoAdicionado(), is("Product successfully added to your shopping cart"));
		
	}
	

}
