package gastei.login.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import gastei.login.model.Usuario;

public class UsuarioRepository {
	private EntityManager manager;
	
	public UsuarioRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Usuario usuario) {
		manager.persist(usuario);
	}
	
	public boolean validar(String login, String senha) {
		Query query = manager.createQuery(
				"select c from Usuario c where c.login = ?1 and c.senha = ?2");
		query.setParameter(1, login).setParameter(2, senha);
		try {
			query.getSingleResult();
			return true;
		} catch (NoResultException e) {
			return false;
		}
	}

}