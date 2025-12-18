package br.edu.utfpr.td.tsi.ecommerce.fiscal.service;

public class PedidoAtualizacaoEstoque {
	private Produto produto;
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
	private int quantidade;
}
