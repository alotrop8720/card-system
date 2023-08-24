package by.misevich.app.core.convertors.mappers;

import by.misevich.app.core.convertors.Mapper;
import by.misevich.app.core.convertors.dto.ClientDTO;
import by.misevich.common.model.Client;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapper implements Mapper<Client, ClientDTO> {

    private final ModelMapper mapper;


    @Override
    public Client toEntity(ClientDTO dto) {
        return mapper.map(dto, Client.class);
    }

    @Override
    public ClientDTO toDto(Client entity) {
        return mapper.map(entity, ClientDTO.class);
    }
}
