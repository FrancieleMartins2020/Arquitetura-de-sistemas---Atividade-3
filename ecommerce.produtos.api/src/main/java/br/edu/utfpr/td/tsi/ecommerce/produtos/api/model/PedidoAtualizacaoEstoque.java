package br.edu.utfpr.td.tsi.ecommerce.produtos.api.model;

public class PedidoAtualizacaoEstoque {

	private Produto produto;
	private int quantidade;


	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

}
