package api.models;

import lombok.Data;

@Data
public class OrderPurchasingThePetRequestBody {
    private int id, petId, quantity;
    private String status, shipDate;
    private boolean complete;

    public boolean getComplete() {
        return complete;
    }
}
