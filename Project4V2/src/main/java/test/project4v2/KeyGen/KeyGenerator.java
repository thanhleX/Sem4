package test.project4v2.KeyGen;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    public static String generateSecretKey() {
        byte[] key = new byte[32]; // 256-bit key
        new SecureRandom().nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }

    public static void main(String[] args) {
        String secretKey = generateSecretKey();
        System.out.println("Generated Secret Key: " + secretKey);
    }
}