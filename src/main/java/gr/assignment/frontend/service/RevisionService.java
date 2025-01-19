package gr.assignment.frontend.service;

import gr.assignment.frontend.dto.RevisionDto;
import gr.assignment.frontend.entity.ResourceEntity;

import java.util.List;

public interface RevisionService {

    void createRevision(ResourceEntity resource);

    List<RevisionDto> findAllRevisions();

    List<RevisionDto> findRevisionsByResourceId(Long resourceId);
}
