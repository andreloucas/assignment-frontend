import gr.assignment.frontend.dto.RevisionDto;
import gr.assignment.frontend.entity.ResourceEntity;
import gr.assignment.frontend.entity.RevisionEntity;
import gr.assignment.frontend.repository.RevisionRepository;
import gr.assignment.frontend.service.impl.RevisionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class RevisionServiceTest {

    @Mock
    private RevisionRepository revisionRepository;

    @InjectMocks
    private RevisionServiceImpl revisionService;

    @Test
    void testCreateResourceRevision() {
        ResourceEntity resource = new ResourceEntity();
        resource.setId(1L);
        resource.setName("name");
        resource.setFileName("fileName");
        resource.setFileData("fileName".getBytes());

        revisionService.createRevision(resource);
        verify(revisionRepository, times(1)).save(any(RevisionEntity.class));
    }

    @Test
    void testFindAllRevisions() {
        List<RevisionEntity> revisions = new ArrayList<>();
        RevisionEntity revision = new RevisionEntity();
        revision.setId(1L);
        revision.setResource(new ResourceEntity());
        revision.setResourceName("resourceName");
        revision.setFileName("fileName");
        revision.setFileData("fileName".getBytes());
        revisions.add(revision);

        when(revisionRepository.findAll()).thenReturn(revisions);
        List<RevisionDto> dtos = revisionService.findAllRevisions();
        verify(revisionRepository, times(1)).findAll();

        assertEquals(revision.getId(), dtos.getFirst().getId());
        assertEquals(revision.getResource().getId(), dtos.getFirst().getResourceId());
        assertEquals(revision.getResourceName(), dtos.getFirst().getResourceName());
        assertEquals(revision.getFileName(), dtos.getFirst().getFileName());
        assertArrayEquals(revision.getFileData(), dtos.getFirst().getFileData());
    }

    @Test
    void testFindResourceRevisionsByResourceId() {
        Long resourceId = 1L;
        ResourceEntity resource = new ResourceEntity();
        resource.setId(resourceId);

        List<RevisionEntity> revisions = new ArrayList<>();
        RevisionEntity revision = new RevisionEntity();
        revision.setId(1L);
        revision.setResource(resource);
        revision.setResourceName("resourceName");
        revision.setFileName("fileName");
        revision.setFileData("fileName".getBytes());
        revisions.add(revision);

        when(revisionRepository.findRevisionsByResourceId(resourceId)).thenReturn(revisions);
        List<RevisionDto> dtos = revisionService.findRevisionsByResourceId(resourceId);

        assertEquals(revision.getId(), dtos.get(0).getId());
        assertEquals(revision.getResourceName(), dtos.get(0).getResourceName());
        assertEquals(revision.getFileName(), dtos.get(0).getFileName());
        assertArrayEquals(revision.getFileData(), dtos.get(0).getFileData());
    }
}
