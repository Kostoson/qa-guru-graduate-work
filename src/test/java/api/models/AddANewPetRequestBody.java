package api.models;

import lombok.Builder;
import lombok.Data;
import java.util.*;

@Data
@Builder
public class AddANewPetRequestBody {
    private int id;
    private Pet category;
    private String name, status;
    private List<String> photoUrls;
    private List<Tags> tags;
}