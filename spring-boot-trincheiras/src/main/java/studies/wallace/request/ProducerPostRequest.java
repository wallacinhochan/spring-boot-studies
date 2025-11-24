package studies.wallace.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProducerPostRequest {
    private String name;
}
