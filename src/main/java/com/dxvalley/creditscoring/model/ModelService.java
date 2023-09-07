package com.dxvalley.creditscoring.model;

import com.dxvalley.creditscoring.model.dto.ModelAddReq;
import com.dxvalley.creditscoring.model.dto.ModelUpdateReq;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ModelService {

    Model addModel(ModelAddReq modelAddReq);

    Model updateModel(Long id, ModelUpdateReq modelUpdateReq);

    Model getModelById(Long id);

    List<Model> getModels();

    Model toggleModelStatus(Long id);

    List<Model> getModelsById(List<Long> modelIds);
}
