package foundation.cmo.sales.services;

import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class SalesService {

	@GraphQLQuery(name = "test", description = "Grapho tem o objetivo de testar se o servidor está apto para execução.")
	public String testService() {
		return "OK";
	}
}
