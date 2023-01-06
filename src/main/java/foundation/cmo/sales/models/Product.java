package foundation.cmo.sales.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)

public class Product {
	@GraphQLQuery(name = "cd_ncm")
	@JsonProperty(value = "Ncm")
	private String ncm;
	
	@GraphQLQuery(name = "cd_ean")
	private String ean;
	
	@GraphQLQuery(name = "cd_cest")
	@JsonProperty(value = "Cest_Codigo")
	private String cest;
	
	@JsonProperty(value = "Nome")
	@GraphQLQuery(name = "ds_produto")
	private String description;
	
	@JsonProperty(value = "QuantidadeEmbalagem")
	@GraphQLQuery(name = "qt_embalagem")
	private String quantityInPackage;
}
