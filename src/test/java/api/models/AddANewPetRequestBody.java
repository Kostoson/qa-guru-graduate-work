package api.models;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.*;

@Data
public class AddANewPetRequestBody {
    private int id;
    private Pet category;
    private String name, status;
    private List<String> photoUrls;
    private List<Tags> tags;
}

/*
{
        "id": 2212,
        "category": {
        "id": 4,
        "name": "Dog"
        },
        "name": "doggie",
        "photoUrls": [
        "string"
        ],
        "tags": [
        {
        "id": 0,
        "name": "string"
        }
        ],
        "status": "available"
        }*/
