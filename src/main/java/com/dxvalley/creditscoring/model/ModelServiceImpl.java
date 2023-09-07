package com.dxvalley.creditscoring.model;

import com.dxvalley.creditscoring.exceptions.customExceptions.ResourceNotFoundException;
import com.dxvalley.creditscoring.model.dto.ModelAddReq;
import com.dxvalley.creditscoring.model.dto.ModelUpdateReq;
import com.dxvalley.creditscoring.utils.CurrentLoggedInUser;
import com.dxvalley.creditscoring.utils.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final CurrentLoggedInUser currentLoggedInUser;

    @Override
    public Model addModel(ModelAddReq modelAddReq) {
        Model model = new Model();

        model.setName(modelAddReq.getName());
        model.setPath(modelAddReq.getPath());
        model.setDescription(modelAddReq.getDescription());
        model.setAddedBy(currentLoggedInUser.getUser().getFullName());
        model.setModelStatus(Status.ACTIVE);

        return modelRepository.save(model);
    }

    @Override
    public Model updateModel(Long id, ModelUpdateReq modelUpdateReq) {
        Model model = getModelById(id);

        if (modelUpdateReq.getName() != null)
            model.setName(modelUpdateReq.getName());

        if (modelUpdateReq.getPath() != null)
            model.setPath(modelUpdateReq.getPath());

        if (modelUpdateReq.getDescription() != null)
            model.setDescription(modelUpdateReq.getDescription());

        model.setUpdatedBy(currentLoggedInUser.getUser().getFullName());
        return modelRepository.save(model);
    }

    @Override
    public Model toggleModelStatus(Long id) {
        Model model = getModelById(id);

        if (model.getModelStatus() == Status.ACTIVE)
            model.setModelStatus(Status.BLOCKED);
        else
            model.setModelStatus(Status.ACTIVE);

        model.setUpdatedBy(currentLoggedInUser.getUser().getFullName());
        return modelRepository.save(model);
    }

    @Override
    public List<Model> getModelsById(List<Long> modelIds) {
        List<Model> models = modelRepository.findAllById(modelIds);

        if (models.isEmpty())
            throw new ResourceNotFoundException("No models found for the provided IDs");

        return models;
    }

    @Override
    public Model getModelById(Long id) {
        return modelRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Model not found"));
    }

    @Override
    public List<Model> getModels() {
        return modelRepository.findAll();
    }
}
