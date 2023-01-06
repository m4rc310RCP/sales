package foundation.cmo.sales.services;

import java.net.URI;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;


import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import foundation.cmo.sales.models.Product;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class SalesService {

	private final String DES_QUERY_TEST = "Query para testar se o serviço está disponível.";
	private final String URL_EANPIC = "http://www.eanpictures.com.br:9000/api/desc/{0}";
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Deprecated
	@GraphQLQuery(
			name = "test", 
			deprecationReason = "Apenas para testes. Não use em produção.", 
			description = DES_QUERY_TEST) 
	public String testService() {
		return "OK";
	}
	
	@GraphQLQuery(name = "product")
	public Product getEanDesc(
			@GraphQLArgument(name = "cd_ean", description = "Código EAN") String ean) throws Exception {
		String uri = String.format(URL_EANPIC, ean.trim());
		
		 try (CloseableHttpClient client = HttpClients.createDefault()) {
			 HttpGet request = new HttpGet(uri);
			 
			 Product response = client.execute(request, httpResponse ->
             mapper.readValue(httpResponse.getEntity().getContent(), Product.class));
			 
			 return response;
		 }
		
	}
	
}
