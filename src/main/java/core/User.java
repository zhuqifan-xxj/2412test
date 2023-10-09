package core;
public class User {
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String customId;
    private String userType;
    

    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getFullName() {
        return fullName;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }


    public String getUserType() {
        return userType;
    }


    public void setUserType(String userType) {
        this.userType = userType;
    }


    public User(String username, String email, String fullName, String phoneNumber, String customId, String userType) {
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.customId = customId;
        this.userType = userType;
    }

    
    @Override
    public String toString() {
        return String.format("Username: %s, Email: %s, Full Name: %s, Phone: %s, Custom ID: %s",
                username, email, fullName, phoneNumber, customId);
    }
}
