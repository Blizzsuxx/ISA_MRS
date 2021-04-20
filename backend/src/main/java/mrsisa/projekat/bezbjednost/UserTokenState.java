package mrsisa.projekat.bezbjednost;

public class UserTokenState {
    private String accessToken;
    private Long expiresIn;
    private String uloga;

    public UserTokenState(){
        this.accessToken = null;
        this.expiresIn = null;
        this.uloga = null;
    }

    public UserTokenState(String accessToken, Long expiresIn) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
    }

    public UserTokenState(String accessToken, Long expiresIn, String uloga) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.uloga = uloga;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }
}
