package curso.todocode.salesservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShopcartDTO {

    private Long id;
    private List<Long> prodCodes;
    private double totalPrice;

}