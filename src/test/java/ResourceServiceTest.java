import gr.assignment.frontend.dto.ResourceDto;
import gr.assignment.frontend.entity.ResourceEntity;
import gr.assignment.frontend.exceptions.NotFoundException;
import gr.assignment.frontend.repository.ResourceRepository;
import gr.assignment.frontend.repository.RevisionRepository;
import gr.assignment.frontend.service.impl.ResourceServiceImpl;
import gr.assignment.frontend.service.impl.RevisionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ResourceServiceTest {

    @Mock
    private ResourceRepository resourceRepository;

    @Mock
    private RevisionServiceImpl revisionService;

    @InjectMocks
    private ResourceServiceImpl resourceService;

    @Test
    void testCreateResource() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "file.txt", "text/plain", "file".getBytes());

        ResourceDto dto = new ResourceDto();
        dto.setId(1L);
        dto.setName("name");
        dto.setFile(file);
        dto.setFileName(file.getOriginalFilename());
        dto.setFileData(file.getBytes());

        resourceService.createResource(dto);
        verify(resourceRepository, times(1)).save(any(ResourceEntity.class));
    }

    @Test
    void testFindAll() {
        List<ResourceEntity> resources = new ArrayList<>();
        ResourceEntity resource = new ResourceEntity();
        resource.setId(1L);
        resource.setName("name");
        resource.setFileName("fileName");
        resource.setFileData("fileName".getBytes());

        resources.add(resource);

        when(resourceRepository.findAll()).thenReturn(resources);
        List<ResourceDto> dtos = resourceService.findAll();
        verify(resourceRepository, times(1)).findAll();

        assertEquals(resource.getId(), dtos.get(0).getId());
        assertEquals(resource.getName(), dtos.get(0).getName());
        assertEquals(resource.getFileName(), dtos.get(0).getFileName());
        assertEquals(resource.getFileData(), dtos.get(0).getFileData());
    }

    @Test
    void testDeleteResource() {
        Long resourceId = 1L;
        ResourceEntity resource = new ResourceEntity();
        resource.setId(resourceId);

        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));
        resourceService.deleteResource(resourceId);
        verify(resourceRepository, times(1)).delete(any(ResourceEntity.class));
    }

    @Test
    void testFindById() {
        Long resourceId = 1L;
        ResourceEntity resource = new ResourceEntity();
        resource.setId(resourceId);

        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));
        ResourceDto dto = resourceService.findById(resourceId);

        assertEquals(resource.getId(), dto.getId());
        assertEquals(resource.getName(), dto.getName());
        assertEquals(resource.getFileName(), dto.getFileName());
        assertEquals(resource.getFileData(), dto.getFileData());
    }

    @Test
    void testUpdateResource() throws IOException {
        Long resourceId = 1L;
        ResourceEntity resource = new ResourceEntity();
        resource.setId(resourceId);
        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));

        MockMultipartFile file = new MockMultipartFile("file", "file.txt", "text/plain", "file".getBytes());

        ResourceDto dto = new ResourceDto();
        dto.setId(1L);
        dto.setName("name");
        dto.setFile(file);
        dto.setFileName(file.getOriginalFilename());
        dto.setFileData(file.getBytes());

        resourceService.updateResource(resourceId, dto);

        verify(resourceRepository, times(1)).findById(resourceId);
        verify(resourceRepository, times(1)).save(any(ResourceEntity.class));
    }

    @Test
    void testFindResourceById() {
        Long resourceId = 1L;
        ResourceEntity resource = new ResourceEntity();
        resource.setId(resourceId);

        when(resourceRepository.findById(resourceId)).thenReturn(Optional.of(resource));
        ResourceEntity result = resourceService.findResourceById(resourceId);

        assertEquals(resource.getId(), result.getId());
        verify(resourceRepository, times(1)).findById(resourceId);
    }

    @Test
    void findResourceById_nullField_throwsNotFoundException() {
        Long resourceId = 1L;
        ResourceEntity resource = new ResourceEntity();
        resource.setId(resourceId);

        when(resourceRepository.findById(resourceId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {
            resourceService.findResourceById(resourceId);
        });
    }
}
