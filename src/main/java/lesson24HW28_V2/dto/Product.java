package lesson24HW28_V2.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
@ToString
public class Product {
    private UUID uid;
    private String name;
    private Double cost;
}