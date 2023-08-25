package by.misevich.app.core.convertors;

import by.misevich.app.core.dto.AbstractDTO;
import by.misevich.common.model.AbstractEntity;

public interface Mapper<E extends AbstractEntity, D extends AbstractDTO> {
    E toEntity(D dto);

    D toDto(E entity);
}
