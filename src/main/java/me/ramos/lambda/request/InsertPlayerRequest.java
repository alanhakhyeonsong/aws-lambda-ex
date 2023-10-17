package me.ramos.lambda.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InsertPlayerRequest {

    private Long id;
    private String name;
    private Integer backNumber;

}
