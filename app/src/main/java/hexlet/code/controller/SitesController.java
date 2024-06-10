package hexlet.code.controller;

import hexlet.code.paths.Paths;
import io.javalin.http.Context;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SitesController {
    public static void enterUrl(Context ctx) {
        ctx.render("sites/mainPage.jte");
    }

    public static void addUrl(Context ctx) {
        var uri = ctx.formParam("url");
        var pattern = Pattern.compile("^((https|http)://[^/]+)");
        var matcher = pattern.matcher(uri);
        if (matcher.find()) {
            ctx.result(matcher.group());
        }
    }
}
