package com.demo.ibk.commons.util.encryption;

public interface EncryptionStrategy {
    String encrypt(String data);
    String decrypt(String data);
}