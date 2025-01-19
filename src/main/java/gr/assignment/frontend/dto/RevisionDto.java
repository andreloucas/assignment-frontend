package gr.assignment.frontend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RevisionDto {

    private Long id;
    private Long resourceId;
    private String resourceName;
    private String FileName;
    private byte[] fileData;
    private LocalDateTime createdOn;
}
