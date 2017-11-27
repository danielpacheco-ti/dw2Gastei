package gastei.login.control;

import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import gastei.login.model.Usuario_gastos;
import gastei.login.repository.Usuario_gastosRepository;

@ManagedBean
public class Usuario_gastosBean {
	private String usuario_login;
	private long gasto_id;
	
	public String getUsuario_login() {
		return usuario_login;
	}
	public void setUsuario_login(String usuario_login) {
		this.usuario_login = usuario_login;
	}
	public long getGasto_id() {
		return gasto_id;
	}
	public void setGasto_id(long gasto_id) {
		this.gasto_id = gasto_id;
	}
	
	public void cadastra() {
		FacesContext fc = FacesContext.getCurrentInstance();
		EntityManager manager = getEntityManager();
		Usuario_gastosRepository repository = new Usuario_gastosRepository(manager);
		ExternalContext ec = fc.getExternalContext();
		HttpSession session = (HttpSession) ec.getSession(false);
		String user = (String) session.getAttribute("usuario");
			Usuario_gastos gastore = new Usuario_gastos();
			gastore.setGasto_id(repository.buscaLastId());
			gastore.setUsuario_login(user);
			repository.inserir(gastore);
	}
	
	private EntityManager getEntityManager() {
		FacesContext fc = FacesContext.getCurrentInstance();
		ExternalContext ec = fc.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) ec.getRequest();
		EntityManager manager = (EntityManager) request.getAttribute("EntityManager");
		return manager;
	}
	
}
