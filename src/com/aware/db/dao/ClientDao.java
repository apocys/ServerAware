package com.aware.db.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.aware.db.model.Client;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ClientDao extends AbstractDao<Client> {


	@Inject
	public ClientDao() {
		super(Client.class);
		// TODO Auto-generated constructor stub
	}


	//	public ClientDao() {
	//		super(Client.class);
	//	}

	public Client getByName(String name) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		Root<Client> person = cq.from(Client.class);

		Predicate cond1 = cb.equal(person.<String>get("name"), name);
		cq.where(cond1);

		List<Client> clients = getEntityManager().createQuery(cq).getResultList();
		if(clients.isEmpty()) return null;
		return clients.get(0);
	}


	public List<Client> getByIds(List<Long> ids) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Client> cq = cb.createQuery(Client.class);
		Root<Client> person = cq.from(Client.class);

		Predicate cond1 = person.<String>get("id").in(ids);
		cq.where(cond1);

		return getEntityManager().createQuery(cq).getResultList();
	}


}