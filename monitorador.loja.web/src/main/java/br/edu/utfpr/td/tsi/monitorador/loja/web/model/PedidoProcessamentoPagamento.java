package br.edu.utfpr.td.tsi.monitorador.loja.web.model;

public class PedidoProcessamentoPagamento {

	private Endereco endereco;
	private Produto produto;
	private int quantidade;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

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
