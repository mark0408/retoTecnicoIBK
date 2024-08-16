package com.demo.ibk.commons.util.encryption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class EncryptionContext {

    private final EncryptionStrategy encryptionStrategy;

    public EncryptionContext(@Value("${encryption.strategy}") String strategy) {
        switch (strategy){
            case "AES": this.encryptionStrategy = new AESEncryptionStrategy();break;
            case "MD5": this.encryptionStrategy = new MD5EncryptionStrategy();break;
            default: throw new IllegalArgumentException("Unknown encryption strategy: " + strategy);
        }

    }

    public String encrypt(String data) {
        return encryptionStrategy.encrypt(data);
    }

    public String decrypt(String encryptedData) {
        return encryptionStrategy.decrypt(encryptedData);
    }
}
