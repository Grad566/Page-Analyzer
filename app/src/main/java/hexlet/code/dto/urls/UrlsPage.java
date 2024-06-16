package hexlet.code.dto.urls;

import hexlet.code.model.Url;
import hexlet.code.model.UrlCheck;
import hexlet.code.repository.UrlChecksRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Getter
@Setter
@AllArgsConstructor
public class UrlsPage {
    private List<Url> urls;
    private String flash;
    private Map<Long, UrlCheck> lastChecks;

    public UrlsPage(List<Url> urls) {
        this.urls = urls;
        lastChecks = new HashMap<>();
        getLastCheck();
    }

    public UrlsPage(List<Url> urls, String flash) {
        this.urls = urls;
        this.flash = flash;
        lastChecks = new HashMap<>();
        getLastCheck();
    }

    private void getLastCheck() {
        urls.stream()
                .peek(u -> {
                    var key = u.getId();
                    try {
                        var values = UrlChecksRepository.getUrlChecksByUrlId(key);
                        if (!values.isEmpty()) {
                            var value = values.get(values.size() - 1);
                            lastChecks.put(key, value);
                        }
                    } catch (SQLException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                })
                .collect(Collectors.toList());
    }
}
