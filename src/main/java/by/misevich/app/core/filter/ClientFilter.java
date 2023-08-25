package by.misevich.app.core.filter;

import by.misevich.app.core.filter.base.Filter;
import by.misevich.common.model.Client;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import static by.misevich.app.core.filter.base.CommonSpecifications.start;


@Getter
@Setter
public class ClientFilter implements Filter<Client> {
    private String name;
    private String phone;
    private String email;
    private String status;

    @Override
    public Specification<Client> toSpecification() {
        return Specification.where(start("name", name))
                .and(start("phone", phone))
                .and(start("email", email))
                .and(start("status", status));
    }
}
