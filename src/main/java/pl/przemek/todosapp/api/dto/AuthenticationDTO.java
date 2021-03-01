package pl.przemek.todosapp.api.dto;

public class AuthenticationDTO {
    private Boolean authenticated;

    public AuthenticationDTO(Boolean authenticated) {
        this.authenticated = authenticated;
    }

    public Boolean getAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }
}
