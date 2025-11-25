package studies.wallace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import studies.wallace.domain.Producer;
import studies.wallace.request.ProducerPostRequest;
import studies.wallace.response.ProducerGetResponse;

@Mapper
public interface ProducerMapper {
    ProducerMapper INSTANCE = Mappers.getMapper(ProducerMapper.class);

    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Producer toProducer(ProducerPostRequest producerpostrequest);

    ProducerGetResponse toProducerGetResponse(Producer producer);
}
