package studies.wallace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import studies.wallace.domain.Anime;
import studies.wallace.domain.Producer;
import studies.wallace.response.AnimeGetResponse;

import java.util.List;

@Mapper
public interface AnimeMapper {
    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    AnimeGetResponse toAnimeGetResponse(Anime anime);

    List<AnimeGetResponse> toAnimeGetResponseList(List<Anime> animes);
}
