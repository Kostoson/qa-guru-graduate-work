package api.models;

import lombok.Data;

@Data
public class CreateUserRequest {
private int id, userStatus;
private String username, firstName, lastName, email, password, phone;

}
