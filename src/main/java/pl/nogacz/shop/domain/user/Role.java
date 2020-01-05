package pl.nogacz.shop.domain.user;

public enum  Role {
    USER,
    ADMIN,
    ROOT;

    public boolean isUser() {
        return this == USER;
    }

    public boolean isAdmin() {
        return this == ADMIN;
    }

    public boolean isRoot() {
        return this == ROOT;
    }
}
