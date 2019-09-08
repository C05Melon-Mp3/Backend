package codegym.mp3zingdao.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class JwtRequest {
    private String username;
    private String password;

    public JwtRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @NotBlank
    @Size(min = 4, max = 60)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotBlank
    @Size(min = 6, max = 40)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
