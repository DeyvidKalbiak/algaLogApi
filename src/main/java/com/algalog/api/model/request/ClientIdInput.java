package com.algalog.api.model.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientIdInput {

	@NotNull
	private Long cliente;
}
