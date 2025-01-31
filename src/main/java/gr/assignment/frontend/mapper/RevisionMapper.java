package gr.assignment.frontend.mapper;

import gr.assignment.frontend.dto.RevisionDto;
import gr.assignment.frontend.entity.RevisionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


import java.util.List;

@Mapper
public interface RevisionMapper {

    RevisionMapper INSTANCE = Mappers.getMapper(RevisionMapper.class);

    @Mapping(target = "resourceId", source = "resource.id")
    RevisionDto revisionToDto(RevisionEntity revision);

    List<RevisionDto> convertRevisionListToDto(List<RevisionEntity>revisions);



}
