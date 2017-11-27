package gastei.login.repository;

import javax.persistence.EntityManager;

import gastei.login.model.Gastos;

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
}
