package dev.edwlopez.android.finalproject.data.entity;

import java.time.LocalDateTime;

public class UserStates {
    private Long id;
    private Boolean isBanned;
    private Boolean isBlocked;
    private Byte accessIntent;
    private LocalDateTime blockedUntil;
    private LocalDateTime blockedFrom;

    public UserStates(Long id, boolean isBanned, byte accessIntent, LocalDateTime blockedUntil, LocalDateTime blockedFrom, boolean isBlocked) {
        this.id = id;
        this.isBanned = isBanned;
        this.accessIntent = accessIntent;
        this.blockedUntil = blockedUntil;
        this.blockedFrom = blockedFrom;
        this.isBlocked = isBlocked;
    }

    public UserStates (){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public byte getAccessIntent() {
        return accessIntent;
    }

    public void setAccessIntent(byte accessIntent) {
        this.accessIntent = accessIntent;
    }

    public LocalDateTime getBlockedUntil() {
        return blockedUntil;
    }

    public void setBlockedUntil(LocalDateTime blockedUntil) {
        this.blockedUntil = blockedUntil;
    }

    public LocalDateTime getBlockedFrom() {
        return blockedFrom;
    }

    public void setBlockedFrom(LocalDateTime blockedFrom) {
        this.blockedFrom = blockedFrom;
    }
}
