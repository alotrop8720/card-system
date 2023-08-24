package by.misevich.app.core.convertors.dto;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class AbstractDTO implements Serializable {
    private Long id;
}
