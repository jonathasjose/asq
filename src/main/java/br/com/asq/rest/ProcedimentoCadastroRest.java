package br.com.asq.rest;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.asq.config.AppConfig;
import br.com.asq.service.ProcedimentoService;

@Named
@Path(value = "/autorizador/cadastro/procedimento/{codigo}/idade/{idade}/sexo/{sexo}/autoriza/{autoriza}")
public class ProcedimentoCadastroRest extends AppConfig {

	@Inject
	private ProcedimentoService procedimentoService;
	
	@POST
	@Consumes(MediaType.TEXT_PLAIN + ";charset=UTF-8")
	public Response validarProcedimentos(@PathParam("codigo") Integer codigo, 
												   @PathParam("idade") Integer idade, 
												   @PathParam("sexo") String sexo,
												   @PathParam("autoriza") String autoriza ){
		
		Response.ResponseBuilder rb = null;
		try {
			//Salva procedimento
			procedimentoService.salvarProcedimento(codigo, idade, sexo, autoriza);
            rb = Response.status(Response.Status.OK);
            
		} catch (Exception e) {
			
            rb = Response.status(Response.Status.BAD_REQUEST);
            rb.entity(e.getMessage());
            rb.type(MediaType.TEXT_PLAIN);
		}
		
		return rb.build();
	}
}
