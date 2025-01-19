package gr.assignment.frontend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ResourceDto {

    private Long id;
    private String name;
    private MultipartFile file;
    private String fileName;
    private byte[] fileData;
}
