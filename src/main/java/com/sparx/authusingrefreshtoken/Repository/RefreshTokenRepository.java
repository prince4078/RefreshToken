package com.sparx.authusingrefreshtoken.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparx.authusingrefreshtoken.entities.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
   Optional<RefreshToken> findByRefreshToken(String token);
}
