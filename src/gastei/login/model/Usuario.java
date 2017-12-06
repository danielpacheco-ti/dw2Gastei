package gastei.login.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="usuario")

public class Usuario {

	@Id
	private String login;
	private String senha;
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
	private double rendamensal;
	private boolean admin;
	@OneToMany(cascade=CascadeType.REMOVE, mappedBy="user")
	private Collection<Gastos> gasto = new ArrayList<Gastos>();

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public double getRendamensal() {
		return rendamensal;
	}

	public void setRendamensal(double rendaMensal) {
		this.rendamensal = rendaMensal;
	}

	public Collection<Gastos> getGasto() {
		return gasto;
	}

	public void setGasto(Collection<Gastos> gasto) {
		this.gasto = gasto;
	}

	public boolean isAdmin() {
		return admin;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}	
}
