package hexlet.code.controller;

import hexlet.code.dto.sites.MainPage;
import hexlet.code.dto.sites.SitePage;
import hexlet.code.dto.sites.SitesPage;
import hexlet.code.model.Site;
import hexlet.code.paths.Paths;
import hexlet.code.repository.SitesRepository;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.sql.SQLException;
import java.util.regex.Pattern;

import static io.javalin.rendering.template.TemplateUtil.model;

public class SitesController {
    public static void enterUrl(Context ctx) {
        var flash = ctx.consumeSessionAttribute("flash");
        var page = new MainPage((String) flash);
        ctx.render("sites/mainPage.jte", model("page", page));
        page.setFlash(null);
    }

    public static void addUrl(Context ctx) {
        var uri = ctx.formParam("url");
        var pattern = Pattern.compile("^((https|http)://[^/]+)");
        var matcher = pattern.matcher(uri);
        if (matcher.find()) {
            var site = new Site(matcher.group());
            try {
                SitesRepository.save(site);
                ctx.sessionAttribute("flash", "Страница успешно добавлена");
                ctx.redirect(Paths.urlsPath());
            } catch (SQLException e) {
                ctx.sessionAttribute("flash", "Страница уже существует");
                ctx.redirect(Paths.rootPath());
            }
        } else {
            ctx.sessionAttribute("flash", "Некорректный URL");
            ctx.redirect(Paths.rootPath());
        }
    }

    public static void showAddedSites(Context ctx) throws SQLException {
        try {
            var flash = ctx.consumeSessionAttribute("flash");
            var page = new SitesPage(SitesRepository.getSites(), (String) flash);
            ctx.render("sites/showAddedSites.jte", model("page", page));
            page.setFlash(null);
        } catch (SQLException e) {
            throw new SQLException("Data base error, when try to get sites");
        }
    }

    public static void showInfoAboutSite(Context ctx) throws SQLException {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var site = SitesRepository.getById(id)
                    .orElseThrow(() -> new NotFoundResponse("Site with id: " + " not found"));
        var page = new SitePage(site);
        ctx.render("sites/showInfoAboutSite.jte", model("page", page));
    }
}
