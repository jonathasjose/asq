package br.com.asq.service;

import java.util.List;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import br.com.asq.model.Procedimento;
import br.com.asq.model.TipoSexo;

@Named
public class ProcedimentoService {

	@PersistenceContext(unitName = "AsqDSUnit", name = "entityManager")
	private EntityManager entityManager;
	
	@Transactional
	public void salvarProcedimento(Integer codigo, Integer idade, String sexo, String autoriza) throws Exception {
		
		//Montar objeto
		Procedimento procedimento = montarProcedimento(codigo, idade, sexo, autoriza);
		
		//Validar Procedimento
		validarProcedimento(procedimento);
		
		//Persistir no banco
		entityManager.persist(procedimento);
		entityManager.flush();
	}

	private void validarProcedimento(Procedimento procedimento) throws Exception {

		if(procedimento.getCodigo() == null) {
			throw new Exception("Erro: Código é obrigatório");
		} else if(procedimento.getIdade() == null) {
			throw new Exception("Erro: Idade é obrigatório");
		} else if(procedimento.getTipoSexo() == null) {
			throw new Exception("Erro: Sexo é obrigatório");
		}
		
		//Validade duplicidade
		//Montar objeto
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT COUNT(p.id) FROM Procedimento p WHERE p.codigo = :pCodigo ");
		hql.append("AND p.idade = :pIdade AND p.tipoSexo = :pSexo ");
		
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("pCodigo", procedimento.getCodigo());
		query.setParameter("pIdade", procedimento.getIdade());
		query.setParameter("pSexo", procedimento.getTipoSexo());
		Long tt = (Long) query.getSingleResult();

		if(tt > 0) {
			throw new Exception("Procedimento duplicado");
		}
	}

	public String verificaProcedimento(Integer codigo, Integer idade, String sexo) throws Exception {
		
		//Montar objeto
		StringBuffer hql = new StringBuffer();
		hql.append("SELECT p FROM Procedimento p WHERE p.codigo = :pCodigo ");
		hql.append("AND p.idade = :pIdade AND p.tipoSexo = :pSexo ");
		
		Query query = entityManager.createQuery(hql.toString());
		query.setParameter("pCodigo", codigo);
		query.setParameter("pIdade", idade);
		query.setParameter("pSexo", TipoSexo.valueOf(sexo.toUpperCase()));
		List<Procedimento> procedimentos = query.getResultList();
		//GET 0, porque pela logica deve ser registro unico (codigo, idade e sexo)
		Procedimento  p = !procedimentos.isEmpty() ? procedimentos.get(0) : null;
		boolean isAutoriza = p != null ? p.getPermitido() : false;
		
		return isAutoriza ? "SIM" : "NAO";
	}

	private Procedimento montarProcedimento(Integer codigo, Integer idade, String sexo, String autoriza) throws Exception {
		
		try {
			Procedimento procedimento = new Procedimento();
			procedimento.setCodigo(codigo);
			procedimento.setIdade(idade);
			procedimento.setTipoSexo(TipoSexo.valueOf(sexo.toUpperCase()));
			procedimento.setPermitido(autoriza != "SIM" ? true : false);
			
			return procedimento;
		} catch (Exception e) {
			throw new Exception("Erro ao criar procedimento");
		}

	}
}
