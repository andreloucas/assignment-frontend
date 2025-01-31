package gr.assignment.frontend.mapper;

import gr.assignment.frontend.dto.ResourceDto;
import gr.assignment.frontend.entity.ResourceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ResourceMapper {

    ResourceMapper INSTANCE = Mappers.getMapper(ResourceMapper.class);

    @Mapping(target = "file", ignore = true)
    ResourceDto resourceToDto(ResourceEntity resource);

    List<ResourceDto> convertResourceListToDto(List<ResourceEntity> resources);
}
