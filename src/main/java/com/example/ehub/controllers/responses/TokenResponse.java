package com.example.ehub.controllers.responses;

import java.io.Serializable;

public class TokenResponse implements Serializable {
    private String accessToken;
    private String refreshToken;

    private TokenResponse(Builder builder) {
        this.accessToken = builder.accessToken;
        this.refreshToken = builder.refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public static class Builder {
        private String accessToken;
        private String refreshToken;

        public Builder() {}

        public Builder accessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public Builder refreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public TokenResponse build() {
            return new TokenResponse(this);
        }
    }
}
