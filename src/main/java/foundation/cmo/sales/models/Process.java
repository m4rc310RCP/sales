package foundation.cmo.sales.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.redis.core.RedisHash;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;

@Data
@RedisHash("cmo_processo")
@Entity(name = "cmo_processo")
@JsonInclude(Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Process implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@org.springframework.data.annotation.Id
	@GraphQLQuery(name = "nr_processo")
	@Column(name = "nr_processo")
	private Long number;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cd_dispositivo")
	private Device device;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@GraphQLQuery(name = "dt_abertura")
	@Column(name = "dt_abertura", nullable = false)
	private Date dateOpen;
	
	@Temporal(TemporalType.TIMESTAMP)
	@GraphQLQuery(name = "dt_fechamento")
	@Column(name = "dt_fechamento", nullable = true)
	private Date dateClose;
	
	
}
