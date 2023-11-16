package com.azubu.schoolmanagementapplication.verification;

public enum VerificationType {

    // This enum is used for both verifying account creation & password reset requests
    ACCOUNT("ACCOUNT"),
    PASSWORD("PASSWORD");

    private final String type;

    VerificationType(String type) {
        this.type = type;
    }

    public String getType() {
        return type.toLowerCase();
    }
}
