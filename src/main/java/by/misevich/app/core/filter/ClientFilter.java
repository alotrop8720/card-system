package by.misevich.app.core.filter;

import by.misevich.app.core.convertors.dto.ClientDTO;
import by.misevich.app.core.filter.base.Filter;
import by.misevich.common.model.Client;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static by.misevich.app.core.filter.specification.ClientSpecification.*;


@Getter
@Setter
public class ClientFilter extends ClientDTO implements Filter<Client> {
    @Override
    public Specification<Client> toSpecification() {
        return Specification.where(hasFullName(getFullName()))
                .and(hasPhone(getPhone()))
                .and(hasEmail(getEmail()))
                .and(hasStatus(getStatus()));
    }
}
