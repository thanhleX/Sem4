package test.project4v3.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import test.project4v3.JWT.JWTUtility;
import test.project4v3.entity.Account;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class JWTService {
    private final JWTUtility jwtUtility;
  public CompletableFuture<String> generateToken(Account userDetail) {
      return jwtUtility.generateToken(userDetail.getUsername());
  }
}
