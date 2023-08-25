package by.misevich.app.core.convertors.mappers;

import by.misevich.app.core.convertors.AbstractMapper;
import by.misevich.app.core.dto.ClientDTO;
import by.misevich.common.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper extends AbstractMapper<Client, ClientDTO> {
    @Autowired
    public ClientMapper() {
        super(Client.class, ClientDTO.class);
    }
}
