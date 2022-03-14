package com.algalog.api.domain.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algalog.api.util.ValidationGroups;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe entidade cliente
 * 
 * @author dkalbiak
 *
 */
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity // anotação diz que esta classe representa uma entitade e como tal ela está
		// associada
//a uma tabela no banco1
@Table(name = "cliente")
public class Cliente {

	@NotNull(groups = ValidationGroups.ClienteId.class) // valida o cliente ID
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Not blank é uma propriedade do jakarta validation, ele não deixa inserir null
	 * e nem campo vazio, tem que validar na entrada tb, no controller usar o valid
	 * lá
	 */
	@NotBlank
	@Size(max = 20)
	private String nome;

	@NotBlank
	@Email // valida a sintaxe do email que será inserido
	private String email;

	@NotBlank
	private String telefone;

}
