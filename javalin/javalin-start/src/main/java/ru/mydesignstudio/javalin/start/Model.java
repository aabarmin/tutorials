package ru.mydesignstudio.javalin.start;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Model {
    private Long id;
    private String firstName;
    private String lastName;
}
