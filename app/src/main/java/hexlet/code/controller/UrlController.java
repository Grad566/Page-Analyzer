package hexlet.code.controller;

import hexlet.code.dto.urls.MainPage;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.dto.urlChecks.UrlCheckPage;
import hexlet.code.model.Url;
import hexlet.code.paths.Paths;
import hexlet.code.repository.UrlsRepository;
import hexlet.code.repository.UrlChecksRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static io.javalin.rendering.template.TemplateUtil.model;

public class UrlController {
    public static void enterUrl(Context ctx) {
        var flash = ctx.consumeSessionAttribute("flash");
        var page = new MainPage((String) flash);
        ctx.render("urls/mainPage.jte", model("page", page));
        page.setFlash(null);
    }

    public static void addUrl(Context ctx) {
        var uriParam = ctx.formParam("url");
        try {
            if (uriParam == null) {
                System.out.println("NULLLL");
            }
            var uri = new URI(uriParam);
            var url = uri.toURL();
            var site = new Url(url.toString());
            UrlsRepository.save(site);
            ctx.sessionAttribute("flash", "Страница успешно добавлена");
            ctx.redirect(Paths.urlsPath());
        } catch (URISyntaxException | MalformedURLException | IllegalArgumentException e) {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.redirect(Paths.rootPath());
        } catch (SQLException e) {
            ctx.sessionAttribute("flash", "Страница уже существует");
            ctx.redirect(Paths.rootPath());
        }

    }

    public static void showAddedUrls(Context ctx) throws SQLException {
        try {
            var flash = ctx.consumeSessionAttribute("flash");
            var page = new UrlsPage(UrlsRepository.getUrls(), (String) flash);
            ctx.render("urls/showAddedUrls.jte", model("page", page));
            page.setFlash(null);
        } catch (SQLException e) {
            throw new SQLException("Data base error, when try to get urls");
        }
    }

    public static void showInfoAboutUrl(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var site = UrlsRepository.getById(id)
                    .orElseThrow(() -> new NotFoundResponse("Site with id: " + " not found"));

        Map<String, Object> model = new HashMap<>();

        var page = new UrlPage(site);
        var flash = ctx.consumeSessionAttribute("flash");
        page.setFlash((String) flash);
        model.put("page", page);

        var urlChecks = UrlChecksRepository.getUrlChecksByUrlId(id);
        var checksPage = new UrlCheckPage(urlChecks);
        model.put("checksPage", checksPage);

        ctx.render("urls/showInfoAboutUrl.jte", model);
        page.setFlash(null);
    }
}
