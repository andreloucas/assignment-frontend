package gr.assignment.frontend.service.impl;

import gr.assignment.frontend.dto.RevisionDto;
import gr.assignment.frontend.entity.ResourceEntity;
import gr.assignment.frontend.entity.RevisionEntity;
import gr.assignment.frontend.mapper.RevisionMapper;
import gr.assignment.frontend.repository.RevisionRepository;
import gr.assignment.frontend.service.RevisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RevisionServiceImpl implements RevisionService {
    private final RevisionRepository revisionRepository;

    @Override
    public void createRevision(ResourceEntity resource) {
        RevisionEntity revision = new RevisionEntity();
        revision.setResource(resource);
        revision.setResourceName(resource.getName());
        revision.setFileName(resource.getFileName());
        revision.setFileData(resource.getFileData());
        revision.setCreatedOn(resource.getUploadOn());
        revisionRepository.save(revision);
    }

    @Override
    public List<RevisionDto> findAllRevisions() {
        List<RevisionDto> dtos = new ArrayList<>();
        List<RevisionEntity> revisions = revisionRepository.findAll();
        for (RevisionEntity revision : revisions) {
            dtos.add(convertToDto(revision));
        }
        return dtos;
    }

    @Override
    public List<RevisionDto> findRevisionsByResourceId(Long resourceId) {
        List<RevisionDto> dtos = new ArrayList<>();
        List<RevisionEntity> revisions = revisionRepository.findRevisionsByResourceId(resourceId);
        for (RevisionEntity revision : revisions) {
            dtos.add(convertToDto(revision));
        }
        return dtos;
    }

    private RevisionDto convertToDto(RevisionEntity revision) {
        return RevisionMapper.INSTANCE.revisionToDto(revision);
    }
}
