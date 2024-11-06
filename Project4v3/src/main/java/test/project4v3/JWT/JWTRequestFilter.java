package test.project4v3.JWT;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTRequestFilter extends OncePerRequestFilter {
    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
            , FilterChain filterChain) throws ServletException, IOException {
        if (this.isWhileList(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        final String authorizationHeader = request.getHeader("Authorization");
        boolean hasToken = authorizationHeader != null && authorizationHeader.startsWith("Bearer ");
        if (!hasToken) {
            response.setStatus(401);
            response.getWriter().write("Thieu token");
            return;
        }

        try {
            String jwtToken = authorizationHeader.substring(7);
            String username = jwtUtility.extractUsername(jwtToken);
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtUtility.validateToken(jwtToken, userDetails.getUsername())) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

                filterChain.doFilter(request, response);
            } else {
                response.setStatus(403);
                response.getWriter().write("Invalid token");
            }
        } catch (Exception e) {
            response.setStatus(403);
            response.getWriter().write("Invalid token");
        }
    }

    private boolean isWhileList(HttpServletRequest request) {
            if (request.getRequestURI().contains("api/login")
                    || request.getRequestURI().contains("api/logout")
                    || request.getRequestURI().contains("api/register")
                    || request.getRequestURI().contains("api/getTest")
                    || request.getRequestURI().contains("api/getTestById")
                    || request.getRequestURI().contains("/api/products")
            ) {
                return true;
            }

            {
            return true;
        }

    }
}
