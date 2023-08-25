package by.misevich.app.core.filter.base;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

@UtilityClass
public class CommonSpecifications {
    public static <T> Specification<T> start(String attr, String value) {
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        return (root, query, cb) -> cb.like(cb.lower(root.get(attr)), value.toLowerCase() + "%");
    }
}
