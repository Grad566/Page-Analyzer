package hexlet.code.controller;

import hexlet.code.model.UrlCheck;
import hexlet.code.paths.Paths;
import hexlet.code.repository.SitesRepository;
import hexlet.code.repository.UrlChecksRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;
import kong.unirest.core.Unirest;
import kong.unirest.core.UnirestException;

import java.sql.SQLException;

public class UrlCheckController {
    public static void makeCheck(Context ctx) throws SQLException {
        var siteId = ctx.pathParamAsClass("id", Long.class).get();
        var site = SitesRepository.getById(siteId)
                    .orElseThrow(() -> new NotFoundResponse("Site with id: " + " not found"));
        var url = site.getName();

        try {
            var response = Unirest.get(url).asEmpty().getStatus();
            var urlCheck = UrlCheck.builder().urlId(siteId).statusCode(response).build();
            UrlChecksRepository.save(urlCheck);
            ctx.sessionAttribute("flash", "Страница успешно проверенна");
            ctx.redirect(Paths.urlsIdPath(siteId));
        } catch (UnirestException e) {
            ctx.sessionAttribute("flash", "Некорректный адрес");
            ctx.redirect(Paths.urlsIdPath(siteId));
        }

    }
}
