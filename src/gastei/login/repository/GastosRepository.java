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
		Gastos c = manager.merge(gasto);
		manager.remove(c);
	}

	public void atualizar(Gastos gasto) {
		manager.merge(gasto);
	}

	public Gastos buscaGasto(long id) {
		Query query = manager.createQuery("select c from Gastos c where c.id = ?1");
		Gastos gasto = new Gastos();
		query.setParameter(1, id);
		try {
			gasto = (Gastos) query.getSingleResult();
			return gasto;
		} catch (NoResultException e) {
			return null;
		}
	}

	public Double somaGastos(Usuario user) {
		Double soma;
		Query query = manager.createQuery("select sum(c.valor) from Gastos c where c.user = ?1");
		query.setParameter(1, user);
		try {
			soma = (Double) query.getSingleResult();
			if (soma==null) {
				soma=(double) 0;
			}
			return soma;
		} catch (NoResultException e) {
			return (double) -1;
		}
	}

	public List<Gastos> buscaGastos(Usuario user) {
		List<Gastos> gastosUser = new ArrayList<Gastos>();
		Query query = manager.createQuery("select c from Gastos c where c.user = ?1 and c.reserva=false");
		query.setParameter(1, user);
		try {
			gastosUser = (List<Gastos>) query.getResultList();
			return gastosUser;
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Gastos> buscaReserva(Usuario user) {
		List<Gastos> gastosUser = new ArrayList<Gastos>();
		Query query = manager.createQuery("select c from Gastos c where c.user = ?1 and c.reserva=true");
		query.setParameter(1, user);
		try {
			gastosUser = (List<Gastos>) query.getResultList();
			return gastosUser;
		} catch (NoResultException e) {
			return null;
		}
	}
}
