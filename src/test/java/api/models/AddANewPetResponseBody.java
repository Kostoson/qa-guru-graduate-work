package api.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AddANewPetResponseBody {
    private int id;
    private Pet category;
    private String name, status;
}

