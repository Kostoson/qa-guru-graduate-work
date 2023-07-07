package api.models;

import lombok.Data;

import java.util.*;

@Data
public class AddANewPetRequestBody {
    private int id;
    private Pet category;
    private String name, status;
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
