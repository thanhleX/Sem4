package test.project4v2.JWT;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import test.project4v2.entity.UserDetailImpl;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class JWTService {
    private final JWTUtility jwtUtility;
  public CompletableFuture<String> generateToken(UserDetailImpl userDetail) {
      return jwtUtility.generateToken(userDetail.getUsername());
  }
}
