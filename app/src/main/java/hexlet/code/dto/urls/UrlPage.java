package hexlet.code.dto.urls;

import hexlet.code.model.Url;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UrlPage {
    private Url url;
    private String flash;

    public UrlPage(Url url) {
        this.url = url;
    }
}
