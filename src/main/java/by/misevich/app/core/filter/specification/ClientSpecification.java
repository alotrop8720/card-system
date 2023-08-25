package by.misevich.app.core.filter.specification;

import by.misevich.common.model.Client;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import static by.misevich.app.core.filter.base.CommonSpecifications.start;

@UtilityClass
public class ClientSpecification {
    public static Specification<Client> hasName(String name) {
        return start("name", name);
    }

    public static Specification<Client> hasPhone(String phone) {
        return start("phone", phone);
    }

    public static Specification<Client> hasEmail(String email) {
        return start("email", email);
    }

    public static Specification<Client> hasStatus(String status) {
        return start("status", status);
    }
}
