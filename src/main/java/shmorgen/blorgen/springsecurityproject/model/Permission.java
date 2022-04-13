package shmorgen.blorgen.springsecurityproject.model;

public enum Permission {
    PLAYERS_READ("developers:read"),
    PLAYERS_WRITE("developers:write");

    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}