package br.com.caelum.financas.mb;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.financas.dao.CategoriaDao;
import br.com.caelum.financas.dao.ContaDao;
import br.com.caelum.financas.dao.MovimentacaoDao;
import br.com.caelum.financas.modelo.Categoria;
import br.com.caelum.financas.modelo.Conta;
import br.com.caelum.financas.modelo.Movimentacao;
import br.com.caelum.financas.modelo.TipoMovimentacao;

@Named
@SessionScoped
public class MovimentacoesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Movimentacao> movimentacoes;
	private List<Categoria> categorias;
	private Movimentacao movimentacao = new Movimentacao();
	private Integer contaId;
	private Integer categoriaId;
	
	@Inject
	private ContaDao contaDao;
	
	@Inject
	private MovimentacaoDao movimentacaoDao;
	
	@Inject
	private CategoriaDao categoriaDao;
	
	public void grava() {
		Conta contaRelacionada = contaDao.busca(contaId);
		movimentacao.setConta(contaRelacionada);
		movimentacaoDao.adiciona(movimentacao);
		this.movimentacoes = movimentacaoDao.listaComCategorias();
		limpaFormularioDoJSF();
	}
	
	public void remove() {
		movimentacaoDao.remove(this.movimentacao);
		this.movimentacoes = movimentacaoDao.listaComCategorias();
		limpaFormularioDoJSF();
	}

	public List<Movimentacao> getMovimentacoes() {
		if(this.movimentacoes == null){
			this.movimentacoes = movimentacaoDao.lista();
		}
		return this.movimentacoes;
	}
	
	public Movimentacao getMovimentacao() {
		if(movimentacao.getData()==null) {
			movimentacao.setData(Calendar.getInstance());
		}
		return movimentacao;
	}

	public void setMovimentacao(Movimentacao movimentacao) {
		this.movimentacao = movimentacao;
	}

	public Integer getContaId() {
		return contaId;
	}

	public void setContaId(Integer contaId) {
		this.contaId = contaId;
	}
	

	public Integer getCategoriaId() {
		return categoriaId;
	}

	public void setCategoriaId(Integer categoriaId) {
		this.categoriaId = categoriaId;
	}

	/**
	 * Esse metodo apenas limpa o formulario da forma com que o JSF espera.
	 * Invoque-o no momento manager que precisar do formulario vazio.
	 */
	private void limpaFormularioDoJSF() {
		this.movimentacao = new Movimentacao();
	}

	public TipoMovimentacao[] getTiposDeMovimentacao() {
		return TipoMovimentacao.values();
	}
	
	public void adicionaCategoria(){
		if(this.categoriaId != null && this.categoriaId > 0){
			Categoria categoria = categoriaDao.progura(this.categoriaId);
			this.movimentacao.getCategorias().add(categoria);
		}
	}
	
	public List<Categoria> getCategorias(){
		if(this.categorias == null){
			System.out.println("Listando as categorias");
			this.categorias = this.categoriaDao.lista();
		}
		return this.categorias;
	}
}
