# Teste AsQ

** Descrição **
- Projeto REST para registro das regras de autorização e das solicitações de autorização procedimentos.

** Informações **
- JDK8 e eclipse para desenvolvimento;
- Foi utilizado Maven para empacotar o projeto;
- Servidor de Aplicação wildfly-10.0.0.Final;
- Banco de dados PostgreSQL;
- Para os serviços REST foi usado RESTEasy; 
- Módulo do postgre instalado no Wildfly foi o postgresql-9.3-1104.jdbc41;
- Contexto da aplicação: http://localhost:8080/asq/
  
** Consulta de Autorização - GET / Retorno 'SIM' ou 'NAO'
- http://localhost:8080/asq/rest/autorizador/procedimento/111111/idade/41/sexo/M

** Cadastro de novo procedimento - POST / Retorno 200 OK
  - http://localhost:8080/asq/rest/autorizador/cadastro/procedimento/111111/idade/41/sexo/M/autoriza/SIM

** Configuraçao do Data Source - standalone.xml ** 
--------------------------------------------------
<datasource jta="true" jndi-name="java:jboss/datasources/AsqDS" pool-name="AsqDS" enabled="true" use-java-context="true">
<connection-url>jdbc:postgresql://localhost:5432/asq</connection-url>
<driver>postgresql</driver>
      <transaction-isolation>TRANSACTION_READ_COMMITTED</transaction-isolation>
      <pool>
          <min-pool-size>10</min-pool-size>
          <max-pool-size>100</max-pool-size>
      </pool>
      <security>
          <user-name>postgres</user-name>
          <password>101010</password>
      </security>
  </datasource>
  <drivers>
    <driver name="postgresql" module="org.postgresql">
        <xa-datasource-class>org.postgresql.xa.PGXADataSource</xa-datasource-class>
    </driver>
  </drivers>
---------------------------------------------
** Rodar liquibase para criar as tabelas **
- mvn liquibase:update
---------------------------------------------
