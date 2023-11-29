package com.test.b2chat.util;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptionComponent {

    private final BasicTextEncryptor textEncryptor;

    
    public EncryptionComponent(@Value("${jasypt.encryptor.password}") String encryptionPassword) {
        textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword(encryptionPassword);
    }

    public String encrypt(String text) {
        return textEncryptor.encrypt(text);
    }

    public String decrypt(String encryptedText) {
        return textEncryptor.decrypt(encryptedText);
    }
}
