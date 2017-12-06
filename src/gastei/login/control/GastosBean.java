package gastei.login.control;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gastei.login.model.Gastos;
import gastei.login.model.Usuario;
import gastei.login.repository.GastosRepository;
import gastei.login.repository.UsuarioRepository;

@ManagedBean
public class GastosBean {
	private long id;
	private String nome;
	private String descricao;
	private double valor;
	private String dataGasto;
	private String dataVencimento;

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

	public String cadastra(){
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		UsuarioRepository userRep = new UsuarioRepository(manager);
		Usuario user = new Usuario();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String login = (String) session.getAttribute("usuario");
		System.out.println(login);
		user = userRep.buscaUser(login);
		System.out.println(user.getLogin());
		Gastos gasto = new Gastos();
		gasto.setDataGasto(dataGasto);
		gasto.setDataVencimento(dataVencimento);
		gasto.setDescricao(descricao);
		gasto.setNome(nome);
		gasto.setValor(valor);
		gasto.setUser(user);
		gasto.setReserva(false);
		repository.inserir(gasto);
		return "/home";
	}
	
	public String cadastraReserva(){
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		UsuarioRepository userRep = new UsuarioRepository(manager);
		Usuario user = new Usuario();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String login = (String) session.getAttribute("usuario");
		System.out.println(login);
		user = userRep.buscaUser(login);
		System.out.println(user.getLogin());
		Gastos gasto = new Gastos();
		gasto.setDataGasto(dataGasto);
		gasto.setDescricao(descricao);
		gasto.setValor(valor);
		gasto.setUser(user);
		gasto.setReserva(true);
		repository.inserir(gasto);
		return "/home";
	}
	
	public String atualizar() {
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		Gastos gasto = (Gastos) session.getAttribute("gastos");
		gasto.setDataGasto(gasto.getDataGasto());
		gasto.setDataVencimento(gasto.getDataVencimento());
		gasto.setDescricao(gasto.getDescricao());
		gasto.setNome(gasto.getNome());
		gasto.setValor(gasto.getValor());
		repository.atualizar(gasto);
		session.removeAttribute("gastos");
		return "/home";
	}
	
	public String atualizarReserva() {
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		Gastos gasto = (Gastos) session.getAttribute("reserva");
		gasto.setDataGasto(gasto.getDataGasto());
		gasto.setDescricao(gasto.getDescricao());
		gasto.setValor(gasto.getValor());
		repository.atualizar(gasto);
		session.removeAttribute("reserva");
		return "/home";
	}
	
	public String editaGasto(long id) {
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		Gastos gasto = repository.buscaGasto(id);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.setAttribute("gastos", gasto);
		return "/editaGasto";
	}
	
	public String excluirGasto() {
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		Gastos gasto = (Gastos) session.getAttribute("gastos");
		repository.excluir(gasto);
		session.removeAttribute("gastos");
		return "/home";
	}
	
	public String excluirReserva() {
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		Gastos gasto = (Gastos) session.getAttribute("reserva");
		repository.excluir(gasto);
		session.removeAttribute("reserva");
		return "/home";
	}
	
	public String editaReserva(long id) {
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		Gastos gasto = repository.buscaGasto(id);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.setAttribute("reserva", gasto);
		return "/editaReserva";
	}
	
	public Double getSoma() {
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		UsuarioRepository repositoryUser = new UsuarioRepository(manager);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String login = (String) session.getAttribute("usuario");
		return repository.somaGastos(repositoryUser.buscaUser(login));
	}
	
	public Double getReserva() {	
		EntityManager manager = getEntityManager();
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		UsuarioRepository repositoryUser = new UsuarioRepository(manager);
		HttpSession session = (HttpSession) ec.getSession(false);
		String login = (String) session.getAttribute("usuario");
		Usuario user = repositoryUser.buscaUser(login);
		
		Double saldoPositivo = user.getRendamensal();
		Double saldoDevedor = getSoma();
		Double soma = saldoPositivo-saldoDevedor;
		return soma;
	}

	public List<Gastos> getGastos() {
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		UsuarioRepository repositoryUser = new UsuarioRepository(manager);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String login = (String) session.getAttribute("usuario");
		return repository.buscaGastos(repositoryUser.buscaUser(login));
	}
	
	public List<Gastos> getReservas() {
		EntityManager manager = getEntityManager();
		GastosRepository repository = new GastosRepository(manager);
		UsuarioRepository repositoryUser = new UsuarioRepository(manager);
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String login = (String) session.getAttribute("usuario");
		return repository.buscaReserva(repositoryUser.buscaUser(login));
	}

	private EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager;
	}

}
