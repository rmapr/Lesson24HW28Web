package lesson24HW28_V2.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@ToString

public class Order {
    private UUID uid;
    private Timestamp createOrder;
    private Timestamp updateOrder;
    private Double cost;
    private List<Product> products = new ArrayList<>();
}
