package com.basespringboot.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilTest {

    private JwtUtil jwtUtil;

    @BeforeEach
    void setUp() {
        jwtUtil = new JwtUtil();
    }

    @Test
    void generateAndValidateToken_ShouldSucceedForValidToken() {
        String token = jwtUtil.generateToken("admin");

        assertNotNull(token);
        assertTrue(jwtUtil.validateToken(token));
        assertEquals("admin", jwtUtil.extractUsername(token));
    }

    @Test
    void validateToken_ShouldReturnFalse_ForTamperedOrInvalidToken() {
        String invalidToken = "eyJhbGciOiJIUzI1NiJ9.fauxPayload.fausseSignature";

        assertFalse(jwtUtil.validateToken(invalidToken));
    }
}
