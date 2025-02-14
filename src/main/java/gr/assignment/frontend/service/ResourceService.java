package gr.assignment.frontend.service;

import gr.assignment.frontend.dto.FileDownloadDto;
import gr.assignment.frontend.dto.ResourceDto;

import java.io.IOException;
import java.util.List;

public interface ResourceService {


    void createResource(ResourceDto resource) throws IOException;

    List<ResourceDto> findAll();

    void deleteResource(Long resourceId);

    ResourceDto findById(Long resourceId);

    void updateResource(Long resourceId, ResourceDto resourceDto) throws IOException;

    FileDownloadDto downloadFile(Long resourceId);
}
