package gr.assignment.frontend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileDownloadDto {

    String fileName;
    byte[] fileData;

    public FileDownloadDto(String fileName, byte[] fileData) {
        this.fileData = fileData;
        this.fileName = fileName;
    }


}
