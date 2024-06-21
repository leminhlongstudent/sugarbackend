package site.sugarnest.backend.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import site.sugarnest.backend.dto.dto.ProducerDto;
import site.sugarnest.backend.dto.dto.ProducerDto.ProducerDtoBuilder;
import site.sugarnest.backend.entities.ProducerEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-06-21T18:01:47+0700",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.7.jar, environment: Java 18.0.2 (Amazon.com Inc.)"
)
@Component
public class IProducerMapperImpl implements IProducerMapper {

    @Override
    public ProducerDto mapToProducerDto(ProducerEntity producer) {
        if ( producer == null ) {
            return null;
        }

        ProducerDtoBuilder producerDto = ProducerDto.builder();

        producerDto.id( producer.getId() );
        producerDto.nameProducer( producer.getNameProducer() );
        producerDto.isActive( producer.getIsActive() );

        return producerDto.build();
    }

    @Override
    public ProducerEntity mapToProducer(ProducerDto producer) {
        if ( producer == null ) {
            return null;
        }

        ProducerEntity producerEntity = new ProducerEntity();

        producerEntity.setId( producer.getId() );
        producerEntity.setNameProducer( producer.getNameProducer() );
        producerEntity.setIsActive( producer.getIsActive() );

        return producerEntity;
    }
}
