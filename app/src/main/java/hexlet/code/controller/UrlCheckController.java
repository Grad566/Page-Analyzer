package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.paths.Paths;
import hexlet.code.repository.SitesRepository;
import hexlet.code.repository.UrlChecksRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.core.Unirest;
import org.jsoup.Jsoup;

import java.sql.SQLException;
import java.util.Objects;

public class UrlCheckController {
    public static void makeCheck(Context ctx) throws SQLException {
        var siteId = ctx.pathParamAsClass("id", Long.class).get();
        var site = SitesRepository.getById(siteId)
                    .orElseThrow(() -> new NotFoundResponse("Site with id: " + siteId + " not found"));
        var url = site.getName();

        try {
            var codeResponse = Unirest.get(url).asEmpty().getStatus();
            var body = Unirest.get(url).asString().getBody();
            var doc = Jsoup.parse(body);
            var title = doc.title();
            var h1 = doc.selectFirst("h1") == null ? ""
                    : Objects.requireNonNull(doc.selectFirst("h1")).text();

            String description = doc.select("meta[name=description]").first() == null ? ""
                    : Objects.requireNonNull(doc.selectFirst("meta[name=description]"))
                        .attr("content");

            var urlCheck = UrlCheck.builder()
                                        .urlId(siteId)
                                        .statusCode(codeResponse)
                                        .title(title)
                                        .h1(h1)
                                        .description(description)
                                        .build();
            UrlChecksRepository.save(urlCheck);
            ctx.sessionAttribute("flash", "Страница успешно проверенна");
            ctx.redirect(Paths.urlsIdPath(siteId));

        } catch (Exception e) {
            ctx.sessionAttribute("flash", "Некорректный адрес");
            ctx.redirect(Paths.urlsIdPath(siteId));
        }

    }

}
