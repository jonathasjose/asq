package br.com.asq.rest;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.asq.config.AppConfig;
import br.com.asq.model.Procedimento;
import br.com.asq.service.ProcedimentoService;

@Named
@Path(value = "/autorizador/procedimento/{codigo}/idade/{idade}/sexo/{sexo}")
public class ProcedimentoConsultaRest extends AppConfig {
	
	@Inject
	private ProcedimentoService procedimentoService;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN + ";charset=UTF-8")
	public String validarProcedimentos(@PathParam("codigo") Integer codigo, 
												   @PathParam("idade") Integer idade, 
												   @PathParam("sexo") String sexo) {
		
		try {
			//Verifica procedimento
			return procedimentoService.verificaProcedimento(codigo, idade, sexo);
            
		} catch (Exception e) {
            e.printStackTrace();
		}
		
		return "NAO";
	}
}
