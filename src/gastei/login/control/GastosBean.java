package gastei.login.control;

import java.util.Calendar;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gastei.login.model.Gastos;
import gastei.login.model.Usuario;	
import gastei.login.repository.GastosRepository;

@ManagedBean
public class GastosBean {
	private long id;
	private String nome;
	private String descricao;
	private double valor;
	private String dataGasto;
	private String dataVencimento;
	private String status;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getDataGasto() {
		return dataGasto;
	}
	public void setDataGasto(String dataGasto) {
		this.dataGasto = dataGasto;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String cadastra() {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		Usuario_gastosBean userGasto = new Usuario_gastosBean();
			Gastos gasto = new Gastos();
			gasto.setDataGasto(dataGasto);
			gasto.setDataVencimento(dataVencimento);
			gasto.setDescricao(descricao);
			gasto.setNome(nome);
			gasto.setStatus(status);
			gasto.setValor(valor);
			repository.inserir(gasto);
			userGasto.cadastra();
			return "/home";
	}
	
	private EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager;
	}
	
	

}
