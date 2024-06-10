package hexlet.code.model;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class Site {
    private Long id;
    private String name;
    private String createdAt;

    public Site(String name) {
        this.name = name;
    }
}
