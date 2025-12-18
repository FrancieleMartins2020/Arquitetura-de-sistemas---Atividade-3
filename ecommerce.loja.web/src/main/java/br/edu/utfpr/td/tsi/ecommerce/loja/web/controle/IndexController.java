package br.edu.utfpr.td.tsi.ecommerce.loja.web.controle;

import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.edu.utfpr.td.tsi.ecommerce.loja.web.CatalogoCliente;
import br.edu.utfpr.td.tsi.ecommerce.loja.web.CepCliente;
import br.edu.utfpr.td.tsi.ecommerce.loja.web.modelo.Endereco;
import br.edu.utfpr.td.tsi.ecommerce.loja.web.modelo.Venda;
import br.edu.utfpr.td.tsi.ecommerce.loja.web.persistencia.VendaRepository;
import br.edu.utfpr.td.tsi.ecommerce.loja.web.modelo.Produto;

@Controller
public class IndexController {

    @Autowired
    private CatalogoCliente catalogoCliente;

    @Autowired
    private CepCliente cepCliente;
    
    @Autowired
    private VendaRepository vendaRepository;

   
    @GetMapping("/")
    public String index(Model model) {
        List<Produto> produtos = catalogoCliente.listarProdutos();
        model.addAttribute("produtos", produtos);
        return "loja";
    }

    @GetMapping("/consultarCep")
    public String exibirPaginaConsultaCep(Model model) {
        return "consulta-cep";
    }

    @GetMapping("/endereco")
    public String exibirPaginaEndereco(Model model) {
        Endereco endereco = cepCliente.obterEnderecoAleatorio();
        model.addAttribute("endereco", endereco);
        return "redirect:/pagamento";
    }

    // GET da página de compra concluída
    @GetMapping("/compraConcluida")
    public String compraConcluida(Model model) {
        // Os objetos "endereco" e "produto" virão via FlashAttributes do POST
        return "compra-concluida";
    }

    @GetMapping("/pagamento")
    public String exibirPaginaPagamento() {
        return "pagamento";
    }
    
    // POST para finalizar compra
    @GetMapping("/finalizarCompra")
    public String finalizarCompra(Model model) {
    	Produto produto = catalogoCliente.listarProdutos().get(0);
        Endereco endereco = cepCliente.obterEnderecoAleatorio();

        model.addAttribute("produto", produto);
        model.addAttribute("endereco", endereco);

        return "finalizaCompra";
    }
    
    // POST para finalizar compra
    @PostMapping("/compraConcluida")
    public String finalizarCompra(
            @RequestParam String produtoId,
            @RequestParam int quantidade,
            Endereco endereco,
            RedirectAttributes redirectAttributes) {
    	Produto produto = catalogoCliente.buscarPorId(produtoId);
    	if (quantidade <= 0) {
            quantidade = 1; // valor padrão
        }
        // Cria venda
        Venda venda = new Venda();
        venda.setId(produto.getId().toString());
        venda.setEndereco(endereco);
        venda.setProduto(produto);
        venda.setQuantidade(quantidade);
        
        vendaRepository.save(venda);


        // Passa Endereco e Produto para a página de compra concluída
        redirectAttributes.addFlashAttribute("endereco", endereco);
        redirectAttributes.addFlashAttribute("produto", produto);

        return "redirect:/compraConcluida";
    }

    @GetMapping("/carrinho")
    public String carrinho() {
        return "carrinho";
    }
}
