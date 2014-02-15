package com.aware.db.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.aware.db.model.Friends;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

public class FriendsDao extends AbstractDao<Friends> {

//	public FriendsDao() {
//		super(Friends.class);
//	}

	 @Inject
	public FriendsDao() {
		super(Friends.class);
		// TODO Auto-generated constructor stub
	}

	public List<Friends> getByProviderId(Long providerId) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Friends> cq = cb.createQuery(Friends.class);
		Root<Friends> person = cq.from(Friends.class);

		Predicate cond1 = cb.equal(person.<Long>get("providerId"), providerId);
		cq.where(cond1);

		return getEntityManager().createQuery(cq).getResultList();
	}
	
	public Friends getFriends(Long providerId, Long requestId) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<Friends> cq = cb.createQuery(Friends.class);
		Root<Friends> person = cq.from(Friends.class);

		List<Predicate> predicates = Lists.newArrayList();
		predicates.add(cb.equal(person.<Long>get("providerId"), providerId));
		predicates.add(cb.equal(person.<Long>get("requestId"), requestId));
		
		cq.where(predicates.toArray(new Predicate[predicates.size()]));

		return getEntityManager().createQuery(cq).getSingleResult();
	}
	
	

}