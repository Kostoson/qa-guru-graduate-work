package api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserRequest {
private int id, userStatus;
private String username, firstName, lastName, email, password, phone;
}
