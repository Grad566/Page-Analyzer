package hexlet.code.dto.urls;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
public class UrlsPage {
    private List<Url> urls;
    private String flash;
    private Map<Long, UrlCheck> lastChecks;
}
