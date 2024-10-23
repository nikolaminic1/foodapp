package com.example.foodapp.auth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AesEncryptionDecryption {
    @Value("${hash_token}")
    private static String secretKey;

    // AES encryption/decryption method
    public static String decrypt(String encryptedText, String iv) throws Exception {
        // Convert the secret key to bytes and recreate the SecretKey object
//        System.out.println(encryptedText);
//        byte[] decodedKey = secretKey.getBytes();
//        SecretKeySpec keySpec = new SecretKeySpec(decodedKey, "AES");
//
//        // Initialize Cipher for decryption
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.DECRYPT_MODE, keySpec);
//
//        // Decode the encrypted text from Base64
//        byte[] decodedMessage = Base64.getDecoder().decode(encryptedText);
//
//        // Decrypt the message
//        byte[] decryptedBytes = cipher.doFinal(decodedMessage);
//
//        // Convert decrypted bytes to string
//        return new String(decryptedBytes);
        byte[] decodedEncryptedText = Base64.getDecoder().decode(encryptedText);
        byte[] decodedIV = Base64.getDecoder().decode(iv);

        // Convert the secret key to a SecretKeySpec
        SecretKeySpec keySpec = new SecretKeySpec(secretKey.getBytes(), "AES");

        // Initialize the Cipher for decryption with AES-GCM
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec gcmSpec = new GCMParameterSpec(128, decodedIV); // 128-bit authentication tag, IV is passed

        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

        // Decrypt the data
        byte[] decryptedBytes = cipher.doFinal(decodedEncryptedText);

        // Convert the decrypted bytes back to a string
        return new String(decryptedBytes);
    }

    // Encrypt method (optional, if you want to see the whole process)
    public static String encrypt(String plainText, String secretKey) throws Exception {
        byte[] key = secretKey.getBytes();
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, keySpec);

        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
