package test.project4v2.JWT;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import test.project4v2.entity.Account;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class JWTService {
    private final JWTUtility jwtUtility;
  public CompletableFuture<String> generateToken(Account userDetail) {
      return jwtUtility.generateToken(userDetail.getUsername());
  }
}
