package foundation.cmo.sales.services;

import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class SalesService {
	
	public final String INFO = "Teste de anotations";
	
	

	@GraphQLQuery(name = "test", description = INFO)
	public String testService() {
		return "OK";
	}
}
