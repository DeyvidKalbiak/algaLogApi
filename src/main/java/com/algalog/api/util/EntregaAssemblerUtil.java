package com.algalog.api.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algalog.api.domain.model.Entrega;
import com.algalog.api.model.EntregaModel;
import com.algalog.api.model.request.EntregaRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EntregaAssemblerUtil {

	private ModelMapper modelMapper;
	
	public EntregaModel toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaModel.class);
	}
	
	public List<EntregaModel> toCollectionModel(List<Entrega> entregas){
		return entregas.stream()
				.map(this :: toModel)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaRequest entrega) {
		return modelMapper.map(entrega, Entrega.class);
	}
}
