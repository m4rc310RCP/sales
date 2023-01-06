package foundation.cmo.sales.services;

import org.springframework.stereotype.Service;

import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class SalesService {

	@GraphQLQuery(name = "test", description = "${info.4.test}")
	public String testService() {
		return "OK";
	}
}
