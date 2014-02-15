package com.aware.db.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Client.class)
public abstract class Client_ {

	public static volatile SingularAttribute<Client, Long> id;
	public static volatile SingularAttribute<Client, String> status;
	public static volatile SingularAttribute<Client, String> email;
	public static volatile SingularAttribute<Client, String> name;
	public static volatile SingularAttribute<Client, Double> longitude;
	public static volatile SingularAttribute<Client, Double> latitude;
	public static volatile SingularAttribute<Client, Date> authenticationTime;
	public static volatile SingularAttribute<Client, Date> date;
	public static volatile SingularAttribute<Client, String> password;
	public static volatile SingularAttribute<Client, String> regId;

}

