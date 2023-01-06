package foundation.cmo.sales.models;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
public class Product {
	@GraphQLQuery(name = "cd_ncm")
	private String ncm;
	
	@GraphQLQuery(name = "cd_cest")
	private String cest;
	
	@GraphQLQuery(name = "ds_produto")
	private String description;
	
	@GraphQLQuery(name = "qt_embalagem")
	private String quantityInPackage;
}
