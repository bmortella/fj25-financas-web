package br.com.caelum.financas.modelo;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Gerente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String telefone;
	
	@Embedded
	private Endereco endereco = new Endereco();
	
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getRua() {
		return this.endereco.getRua();
	}
	
	public void setRua(String rua) {
		this.endereco.setRua(rua);
	}
	
	public String getCidade() {
		return this.endereco.getCidade();
	}
	
	public void setCidade(String cidade) {
		this.endereco.setCidade(cidade);
	}
	
	public String getEstado() {
		return this.endereco.getEstado();
	}
	
	public void setEstado(String estado) {
		this.endereco.setEstado(estado);
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id){
		this.id = id;
	}
}
