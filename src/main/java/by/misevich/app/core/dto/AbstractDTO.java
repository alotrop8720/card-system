package by.misevich.app.core.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class AbstractDTO implements Serializable {
    private Long id;
}
