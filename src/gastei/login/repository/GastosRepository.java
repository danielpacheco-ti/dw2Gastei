package gastei.login.repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import gastei.login.model.Gastos;
import gastei.login.model.Usuario;

public class GastosRepository {
private EntityManager manager;
	
	public GastosRepository(EntityManager manager) {
		this.manager = manager;
	}
	
	public void inserir(Gastos gasto) {
		manager.persist(gasto);
	}
	
	public void excluir(Gastos gasto) {
		manager.remove(gasto);
	}
	
	public List <Gastos> buscaGastos (Usuario user){
		List <Gastos> gastosUser = new ArrayList<Gastos>();
		Query query = manager.createQuery("select c from Gastos c where c.user = ?1");
		query.setParameter(1, user);
		try {
			gastosUser = (List <Gastos>) query.getResultList();
			return gastosUser;
		} catch (NoResultException e) {
			return null;
		}
		
	}
}
