package studies.wallace.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProducerPutRequest {
    private String name;
    private Long id;
}
