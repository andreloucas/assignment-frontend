package gr.assignment.frontend.service.impl;

import gr.assignment.frontend.dto.ResourceDto;
import gr.assignment.frontend.entity.ResourceEntity;
import gr.assignment.frontend.exceptions.NotFoundException;
import gr.assignment.frontend.exceptions.ValidateErrorException;
import gr.assignment.frontend.mapper.ResourceMapper;
import gr.assignment.frontend.repository.ResourceRepository;
import gr.assignment.frontend.service.ResourceService;
import gr.assignment.frontend.service.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceRepository resourceRepository;
    private final RevisionService revisionService;

    @Override
    public void createResource(ResourceDto resourceDto) throws IOException {
        validateResourceDto(resourceDto);
        ResourceEntity resource = new ResourceEntity();
        resource.setName(resourceDto.getName());
        resource.setUploadOn(LocalDateTime.now());
        resource.setFileName(resourceDto.getFile().getOriginalFilename());
        resource.setFileData(resourceDto.getFile().getBytes());
        resourceRepository.save(resource);
    }

    @Override
    public List<ResourceDto> findAll() {
        List<ResourceDto> dtos = new ArrayList<>();
        List<ResourceEntity> resources = resourceRepository.findAll();
        for (ResourceEntity resource : resources) {
            dtos.add(convertToDto(resource));
        }
        return dtos;
    }

    @Override
    public void deleteResource(Long resourceId) {
        ResourceEntity resource = findResourceById(resourceId);
        resourceRepository.delete(resource);
    }

    @Override
    public ResourceDto findById(Long resourceId) {
        ResourceEntity resource = findResourceById(resourceId);
        return convertToDto(resource);
    }

    @Override
    public void updateResource(Long resourceId, ResourceDto resourceDto) throws IOException {
        ResourceEntity resource = findResourceById(resourceId);

        validateResourceDto(resourceDto);
        revisionService.createRevision(resource);

        resource.setName(resourceDto.getName());
        resource.setFileName(resourceDto.getFile().getOriginalFilename());
        resource.setFileData(resourceDto.getFile().getBytes());
        resource.setUploadOn(LocalDateTime.now());
        resourceRepository.save(resource);
    }


    private ResourceDto convertToDto(ResourceEntity resource) {
        return ResourceMapper.INSTANCE.resourceToDto(resource);
    }

    private void validateResourceDto(ResourceDto dto) {
        if (dto.getName().isEmpty() || dto.getFile().isEmpty()) {
            throw new ValidateErrorException("cannot validate resource");
        }
    }

    public ResourceEntity findResourceById(Long resourceId) {
        ResourceEntity resource = resourceRepository.findById(resourceId).orElseThrow(NotFoundException::new);
        return resource;
    }
}
