package com.algalog.api.util;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algalog.api.domain.model.Ocorrencia;
import com.algalog.api.model.OcorrenciaModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssemblerUtil {

	private ModelMapper modelMapper;
	
	public OcorrenciaModel toModel(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaModel.class);
	}
	
	public List<OcorrenciaModel> toCollectionModel(List<Ocorrencia> ocorrencias){
	
		return ocorrencias.stream()
				.map(this :: toModel)
				.collect(Collectors.toList());	
	}
}