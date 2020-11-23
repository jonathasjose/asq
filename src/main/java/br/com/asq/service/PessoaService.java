package br.com.asq.service;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Named
public class PessoaService {

	@PersistenceContext(unitName = "viaLaserUnit", name = "entityManager")
	private EntityManager entityManager;

	
}
