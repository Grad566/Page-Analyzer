package hexlet.code.dto.urls;

import hexlet.code.model.Url;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class UrlsPage {
    private List<Url> urls;
    private String flash;

    public UrlsPage(List<Url> urls) {
        this.urls = urls;
    }
}
