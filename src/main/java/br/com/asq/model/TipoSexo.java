package br.com.asq.model;

public enum TipoSexo {
	
	M("MASCULINO"),
	F("FEMININO");
	
	private String sigla;
	
	TipoSexo(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return sigla;
	}
	
}
