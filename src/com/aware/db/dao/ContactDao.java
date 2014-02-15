package com.aware.db.dao;

import java.util.List;

import javax.persistence.Query;

import com.aware.db.model.Client;
import com.aware.db.model.Contact;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ContactDao extends AbstractDao<Client> {


	@Inject
	public ContactDao() {
		super(Client.class);
		// TODO Auto-generated constructor stub
	}

	public Contact find(String email) {
		Query q = getEntityManager().createQuery("select c from Contact c where c.email = :email");
		q.setParameter("email", email);
		List<Contact> result = q.getResultList();

		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}


}
