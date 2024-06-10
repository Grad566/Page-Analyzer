package hexlet.code.dto.sites;

import hexlet.code.model.Site;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class SitesPage {
    private List<Site> sites;
}
