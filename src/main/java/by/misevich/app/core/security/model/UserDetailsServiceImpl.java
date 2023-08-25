package by.misevich.app.core.security.model;

import by.misevich.common.model.Client;
import by.misevich.common.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@Service("userDetailsService")
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            Client client = clientRepository.findByUsername(username);
            if (client != null)
                return new User(client.getUsername(), client.getSecurePassword(), Collections.emptyList());
        } catch (UsernameNotFoundException e) {
            log.info(String.format("Client is not fount with login '%s'.", username));
        }
        throw new UsernameNotFoundException(username);
    }
}
