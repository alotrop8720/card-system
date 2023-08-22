package by.misevich.common.model;

import by.misevich.common.model.enums.CardType;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Client {
    @Id
    private String id;
    private String name;
    private String phone;
    private String email;
    private CardType status;
}
