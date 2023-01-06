package foundation.cmo.sales.services;

import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class SalesService {
	
	private final String DES_QUERY_TEST = "Query para testar se o serviço está disponível.";
	
	@GraphQLQuery(name = "test", description = DES_QUERY_TEST)
	public String testService() {
		return "OK";
	}
}
