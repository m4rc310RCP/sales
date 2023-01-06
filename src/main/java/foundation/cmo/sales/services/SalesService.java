package foundation.cmo.sales.services;

import java.io.InputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import foundation.cmo.sales.models.Product;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@Service
@GraphQLApi
public class SalesService {

	private final String DES_QUERY_TEST = "Query para testar se o serviço está disponível.";

	private final String URL_API_EANPICTURES = "http://www.eanpictures.com.br:9000/api";
	private final String URL_EANPIC = "desc";
	private final String URL_IMGPIC = "gtin";
	private final ObjectMapper mapper = new ObjectMapper();

	@Deprecated
	@GraphQLQuery(name = "test", deprecationReason = "Apenas para testes. Não use em produção.", description = DES_QUERY_TEST)
	public String testService() {
		return "OK";
	}

	@GraphQLQuery(name = "product")
	public Product getEanDesc(@GraphQLArgument(name = "cd_ean", description = "Código EAN") String ean)
			throws Exception {
		String uri = String.format("%s/%s/%s", URL_API_EANPICTURES, URL_EANPIC, ean.trim());

		try (CloseableHttpClient client = HttpClients.createDefault()) {
			HttpGet request = new HttpGet(uri);

			Product response = client.execute(request,
					httpResponse -> mapper.readValue(httpResponse.getEntity().getContent(), Product.class));
			response.setEan(ean);

			return response;
		}
	}

	@GraphQLQuery(name = "ds_image")
	public String getImgProduct(@GraphQLContext Product product) {
		try {
			String uri = String.format("%s/%s/%s", URL_API_EANPICTURES, URL_IMGPIC, product.getEan());
			java.net.URL url = new java.net.URL(uri);
			InputStream is = url.openStream();
			byte[] bytes = org.apache.commons.io.IOUtils.toByteArray(is);
			return Base64.encodeBase64String(bytes);
		} catch (Exception e) {
			return null;
		}
	}

}
