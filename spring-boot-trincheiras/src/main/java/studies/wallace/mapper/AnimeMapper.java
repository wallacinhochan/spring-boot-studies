package studies.wallace.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import studies.wallace.domain.Anime;
import studies.wallace.request.AnimePostRequest;
import studies.wallace.request.AnimePutRequest;
import studies.wallace.response.AnimeGetResponse;

import java.util.List;

@Mapper
public interface AnimeMapper {
    AnimeMapper INSTANCE = Mappers.getMapper(AnimeMapper.class);

    AnimeGetResponse toAnimeGetResponse(Anime anime);

    List<AnimeGetResponse> toAnimeGetResponseList(List<Anime> animes);

    @Mapping(target = "id", expression = "java(java.util.concurrent.ThreadLocalRandom.current().nextLong(100_000))")
    Anime toAnime(AnimePostRequest animepostrequest);

    Anime toAnime(AnimePutRequest animeputrequest);
}
