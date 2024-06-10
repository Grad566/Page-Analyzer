package hexlet.code.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
public class Site {
    private Long id;
    private String name;
    private String createdAt;

    public Site(String name) {
        this.name = name;
    }
}
