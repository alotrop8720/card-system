package by.misevich.app.core.filter.base;

import org.springframework.data.jpa.domain.Specification;

public interface Filter<T> {
    Specification<T> toSpecification();
}
