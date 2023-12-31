package by.misevich.app.core.convertors;

import by.misevich.app.core.dto.AbstractDTO;
import by.misevich.common.model.AbstractEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public abstract class AbstractMapper <E extends AbstractEntity, D extends AbstractDTO> implements Mapper<E, D> {
    @Autowired
    private ModelMapper mapper;

    private final Class<E> entityClass;
    private final Class<D> dtoClass;

    protected AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
        this.entityClass = entityClass;
        this.dtoClass = dtoClass;
    }

    @Override
    public E toEntity(D dto) {
        return  Objects.isNull(dto)
                ? null
                : mapper.map(dto, entityClass);
    }

    @Override
    public D toDto(E entity) {
        return Objects.isNull(entity)
                ? null
                : mapper.map(entity, dtoClass);
    }
}
