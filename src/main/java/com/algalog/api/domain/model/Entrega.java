package com.algalog.api.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;

import com.algalog.api.domain.exception.NovaException;
import com.algalog.api.util.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe entidade Entregas
 * 
 * @author dkalbiak
 *
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
public class Entrega {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@NotNull
	@ManyToOne
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class) // Valida o cliente id usando a interface
																				// de marcação criada
	private Cliente cliente;

	@Valid
	@NotNull
	@Embedded
	private Destinatario destinatario;
	private BigDecimal taxa;

	@OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
	private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();

	@JsonProperty(access = Access.READ_ONLY) // campo somente leitura
	@Enumerated(EnumType.STRING)
	private StatusEntrega statusEntrega;

	@JsonProperty(access = Access.READ_ONLY) // campo somente leitura
	private OffsetDateTime dataPedido;

	@JsonProperty(access = Access.READ_ONLY) // campo somente leitura
	private OffsetDateTime dataEntrega;

	public Ocorrencia adicionarOcorrencia(String descricao) {
		Ocorrencia ocorrencia = new Ocorrencia();
		ocorrencia.setDescricao(descricao);
		ocorrencia.setDataRegistro(OffsetDateTime.now());
		ocorrencia.setEntrega(this);

		this.getOcorrencias().add(ocorrencia);

		return ocorrencia;
	}

	public void finalizar() {

		if (entregaNaoPodeSerFinalizada()) {
			throw new NovaException("A entrega n\u00E3o pode ser finalizada!");
		}
		setStatusEntrega(StatusEntrega.FINALIZADA);
		setDataEntrega(OffsetDateTime.now());

	}

	public boolean entregaPodeSerFinalizada() {
		return StatusEntrega.PENDENTE.equals(getStatusEntrega());
	}

	public boolean entregaNaoPodeSerFinalizada() {
		return !entregaPodeSerFinalizada();
	}
}
