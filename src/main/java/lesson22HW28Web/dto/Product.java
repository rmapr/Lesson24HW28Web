package lesson22HW28Web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class Product {
    private Integer id;
    private String name;
    private Double cost;
}
