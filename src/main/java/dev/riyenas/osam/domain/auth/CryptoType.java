package dev.riyenas.osam.domain.auth;

public enum CryptoType {
    HmacSHA1("HmacSHA1"),
    HmacSHA256("HmacSHA256"),
    HmacSHA512("HMacSHA512");

    CryptoType(String crypto) {
        this.crypto = crypto;
    }

    private final String crypto;

    public String toString() {
        return crypto;
    }
}
