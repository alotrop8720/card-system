package by.misevich.app.core.security.filter;

import by.misevich.app.core.error.JwtAuthenticationException;
import by.misevich.app.core.security.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {
    private final JwtTokenUtils jwtTokenUtils;

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String token = jwtTokenUtils.resolveToken(httpServletRequest);
        try {
            if (Objects.nonNull(token)) {
                final String usernameFromToken = jwtTokenUtils.getUsernameFromToken(token);
                UserDetails userDetails = userDetailsService.loadUserByUsername(usernameFromToken);
                final boolean isValid = jwtTokenUtils.validateToken(token, userDetails);

                if (isValid) {
                    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e) {
            SecurityContextHolder.clearContext();
            log.info(String.format("Token is not correct. %s", e.getMessage()));
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
