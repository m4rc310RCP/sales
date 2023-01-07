package foundation.cmo.sales.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
@Entity
@RedisHash("cmo_dispositivo")
@Table(name = "cmo_dispositivo")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Device implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@org.springframework.data.annotation.Id
	@GraphQLQuery(name = "cd_dispositivo")
	@Column(name = "cd_dispositivo")
	private String deviceID;
	
}
