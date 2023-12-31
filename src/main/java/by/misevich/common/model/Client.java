package by.misevich.common.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "m_client")
public class Client extends AbstractEntity{
    private String fullName;
    private String phone;
    private String email;
    private String status;
    private String username;
    private String securePassword;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
    @JsonBackReference
    private Set<Card> cards = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return Objects.equals(fullName, client.fullName) &&
                Objects.equals(phone, client.phone) &&
                Objects.equals(email, client.email) &&
                Objects.equals(status, client.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, phone, email, status);
    }
}
