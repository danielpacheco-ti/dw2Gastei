package gastei.login.control;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gastei.login.model.Usuario;
import gastei.login.repository.UsuarioRepository;

@ManagedBean
@SessionScoped
public class UsuarioBean {
	private String login;
	private String senha;
	private String senha2;
	private String nome;
	private String cpf;
	private String endereco;
	private String telefone;
	private float rendamensal;
	
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
	
	public String getSenha2() {
		return senha2;
	}

	public void setSenha2(String senha2) {
		this.senha2 = senha2;
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

	public float getRendamensal() {
		return rendamensal;
	}

	public void setRendamensal(float rendaMensal) {
		this.rendamensal = rendaMensal;
	}

	public String autentica() {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		UsuarioRepository repository = new UsuarioRepository(manager);
		if(repository.validar(login, senha)) {
			if(login.equals("admin")) {
				ExternalContext ec = fc.getExternalContext();
				HttpSession session = (HttpSession) ec.getSession(false);
				session.setAttribute("usuario", login);
				return "/homeAdmin";
			} else {
			ExternalContext ec = fc.getExternalContext();
			HttpSession session = (HttpSession) ec.getSession(false);
			session.setAttribute("usuario", login);
			return "/home";
			}
		} else {
			FacesMessage fm = new FacesMessage("login e/ou senha inválidos");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/login";
		}
	}
	
	public String deslogar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		session.removeAttribute("usuario");
		session.invalidate();
		return "/login";
	}
	
	public String cadastra() {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		UsuarioRepository repository =
				new UsuarioRepository(manager);
		if(senha.equals(senha2)) {
			Usuario usuario = new Usuario();
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setCpf(cpf);
			usuario.setEndereco(endereco);
			usuario.setNome(nome);
			usuario.setRendamensal(rendamensal);
			usuario.setTelefone(telefone);
			repository.inserir(usuario);
			return "/login";
		} else {
			FacesMessage fm = new FacesMessage("Senhas não são iguais!");
			fm.setSeverity(FacesMessage.SEVERITY_ERROR);
			fc.addMessage(null, fm);
			return "/cadastro";
		}
	}
	
	private EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager;
	}
	

}
