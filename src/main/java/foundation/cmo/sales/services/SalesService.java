package foundation.cmo.sales.services;

import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import foundation.cmo.sales.db.repositories.DeviceRepository;
import foundation.cmo.sales.db.repositories.ProcessRepository;
import foundation.cmo.sales.db.repositories.ProductRepository;
import foundation.cmo.sales.models.Device;
import foundation.cmo.sales.models.Process;
import foundation.cmo.sales.models.Product;
import io.leangen.graphql.annotations.GraphQLArgument;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
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

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProcessRepository processRepository;
	
	@Autowired
	private DeviceRepository deviceRepository;

	/**
	 * TODO {@code deviceId} é um Mock e deve ser substituído pelo código do
	 * dispositivo que extraído do token de acesso.
	 */
	private final String DEVICE_ID = "MAC001";

//	@Cacheable("sales.processo")
	@GraphQLMutation(name = "abrir_venda")
	public Process openSalesProcess() throws Exception {
		
		boolean exists = processRepository.existsByDeviceDeviceIDAndDateCloseIsNull(DEVICE_ID);
		assertTrue(exists, "Já existe um procecesso de venda para o dispositivo {0}", DEVICE_ID);
		
		Process process = new Process();
		process.setDateOpen(new Date());
		
		Device device = new Device();
		device.setDeviceID(DEVICE_ID);
		device = setDevice(device);
		process.setDevice(device);
		
		return  processRepository.save(process) ;
	}
	
//	@CacheEvict("sales.processo")
	@GraphQLMutation(name = "fechar_venda")
	public Process closeSaleProcess()  throws Exception {
		boolean exists = processRepository.existsByDeviceDeviceIDAndDateCloseIsNull(DEVICE_ID);
		assertTrue(!exists, "Não há um procecesso de venda em aberto para o dispositivo {0}", DEVICE_ID);
		
		Optional<Process> data = processRepository.findByDeviceDeviceIDAndDateCloseIsNull(DEVICE_ID);
		Process process = data.get();
		process.setDateClose(new Date());
		
		return processRepository.save(process) ;
	}
	
	
	
	@GraphQLMutation(name = "salvar_dispositivo")
	public Device setDevice(@GraphQLArgument(name = "dispositivo") Device device) {
		return deviceRepository.save(device);
	}
	

	@Deprecated
	@Cacheable("test")
	@GraphQLQuery(name = "test", deprecationReason = "Apenas para testes. Não use em produção.", description = DES_QUERY_TEST)
	public String testService() {
		
		System.out.println("Test");

		boolean exists = processRepository.existsByDeviceDeviceIDAndDateCloseIsNull("");
		
		return "OK -> " + exists;
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

	private void assertTrue(boolean pattern, String messageError, Object... args) throws Exception {
		if (pattern) {
			messageError = MessageFormat.format(messageError, args);
			throw new Exception(messageError);
		}
	}

}
