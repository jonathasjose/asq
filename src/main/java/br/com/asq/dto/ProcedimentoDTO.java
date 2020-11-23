package br.com.asq.dto;

import javax.ws.rs.PathParam;

import br.com.asq.model.TipoSexo;

public class ProcedimentoDTO {
	
	private Integer codigo; 
	private Integer idade; 
	private TipoSexo sexo;
	private Boolean autoriza;
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	public TipoSexo getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = TipoSexo.valueOf(sexo);
	}
	public Boolean getAutoriza() {
		return autoriza;
	}
	public void setAutoriza(Boolean autoriza) {
		this.autoriza = autoriza;
	}
	
	
}
