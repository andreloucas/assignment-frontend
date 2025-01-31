package gr.assignment.frontend.mapper;

import gr.assignment.frontend.dto.NotificationLogDto;
import gr.assignment.frontend.entity.NotificationLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NotificationLogMapper {

    NotificationLogMapper INSTANCE = Mappers.getMapper(NotificationLogMapper.class);

    NotificationLogDto notificationLogToDto(NotificationLogEntity notificationLog);
}
