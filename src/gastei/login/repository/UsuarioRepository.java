package gastei.login.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import gastei.login.model.Gastos;
import gastei.login.model.Usuario;

public class UsuarioRepository {
	private EntityManager manager;
	
	public UsuarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Usuario usuario) {
		manager.persist(usuario);
	}
	
	public void atualizar(Usuario user) {
		manager.merge(user);
	}
	
	public void excluir(Usuario user) {
		Usuario c = manager.merge(user);
		manager.remove(c);
	}

	
	public Usuario buscaUser(String login) {
		Usuario user;
		Query query = manager.createQuery("select c from Usuario c where c.login = ?1");
		query.setParameter(1, login);
		try {
			user = (Usuario) query.getSingleResult();
			return user;
		} catch (NoResultException e) {
			return null;
		}
		
	}
	
	public List<Usuario> buscaUsuarios() {
		List<Usuario> listaUser = new ArrayList<Usuario>();
		Query query = manager.createQuery("select c from Usuario c");
		try {
			listaUser = (List<Usuario>) query.getResultList();
			return listaUser;
		} catch (NoResultException e) {
			return null;
		}
	}
		
	public boolean validar(String login, String senha) {
		Query query = manager.createQuery("select c from Usuario c where c.login = ?1 and c.senha = ?2");
		query.setParameter(1, login).setParameter(2, senha);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

}