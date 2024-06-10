package hexlet.code.controller;

import hexlet.code.dto.sites.SitesPage;
import hexlet.code.model.Site;
import hexlet.code.paths.Paths;
import hexlet.code.repository.SitesRepository;
import io.javalin.http.Context;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.javalin.rendering.template.TemplateUtil.model;

public class SitesController {
    public static void enterUrl(Context ctx) {
        ctx.render("sites/mainPage.jte");
    }

    public static void addUrl(Context ctx) {
        var uri = ctx.formParam("url");
        var pattern = Pattern.compile("^((https|http)://[^/]+)");
        var matcher = pattern.matcher(uri);
        if (matcher.find()) {
            var site = new Site(matcher.group());
            try {
                SitesRepository.save(site);
                ctx.redirect(Paths.urlsPath());
            } catch (SQLException e) {
                ctx.result("Страница уже существует");
            }
        } else {
            ctx.result("Ops error message");
        }
    }

    public static void showAddedSites(Context ctx) throws SQLException {
        try {
            var page = new SitesPage(SitesRepository.getSites());
            ctx.render("sites/showAddedSites.jte", model("page", page));
        } catch (SQLException e) {
            throw new SQLException("Data base error, when try to get sites");
        }
    }
}
