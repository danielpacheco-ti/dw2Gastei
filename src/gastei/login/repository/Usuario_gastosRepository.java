package gastei.login.repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import gastei.login.model.Usuario_gastos;

public class Usuario_gastosRepository {
	private EntityManager manager;

	public Usuario_gastosRepository(EntityManager manager) {
		this.manager = manager;
	}

	public void inserir(Usuario_gastos user_gastos) {
		manager.persist(user_gastos);
	}

	public int buscaLastId() {
		Query query = manager.createQuery("SELECT TOP 1 Id FROM GASTOS ORDER BY Id DESC");
		try {
			query.getSingleResult();
			System.out.println(Integer.parseInt(query.getSingleResult().toString()));
			return Integer.parseInt(query.getSingleResult().toString());
		} catch (NoResultException e) {
			return -1;
		}
	}

}
