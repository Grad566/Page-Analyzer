package hexlet.code.dto.urlChecks;

import hexlet.code.model.UrlCheck;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UrlCheckPage {
    private List<UrlCheck> urlChecks;
}
