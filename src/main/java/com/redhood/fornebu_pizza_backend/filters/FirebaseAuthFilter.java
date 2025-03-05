package com.redhood.fornebu_pizza_backend.filters;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.auth.FirebaseToken;
import com.redhood.fornebu_pizza_backend.service.FirebaseAuthService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class FirebaseAuthFilter extends OncePerRequestFilter {
    private final FirebaseAuthService firebaseAuthService;
    private final Firestore firestore;

    public FirebaseAuthFilter(FirebaseAuthService firebaseAuthService, Firestore firestore) {
        this.firebaseAuthService = firebaseAuthService;
        this.firestore = firestore;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }

        String token = header.substring(7); // Remove "Bearer " prefix

        try {
            FirebaseToken decodedToken = firebaseAuthService.verifyToken(token);
            String uid = decodedToken.getUid();

            // 🔹 Fetch the role from Firestore
            DocumentReference docRef = firestore.collection("users").document(uid);
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get(); // Synchronous retrieval

            boolean isAdmin = false;
            if (document.exists()) {
                isAdmin = Boolean.TRUE.equals(document.getBoolean("isAdmin")); // Get isAdmin field
            }

            // 🔹 Convert role into a granted authority (Spring Security format)
            List<GrantedAuthority> authorities = isAdmin
                    ? Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"))
                    : Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));

            // 🔹 Set authentication in SecurityContext
            Authentication authentication = new UsernamePasswordAuthenticationToken(uid, null, authorities);

            // Set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Unauthorized: Invalid Token");
            return;
        }

        chain.doFilter(request, response);
    }
}
