package hexlet.code;

import io.javalin.Javalin;
import io.javalin.testtools.JavalinTest;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.sql.SQLException;

class AppTest {
    Javalin app;

    @BeforeEach
    public final void setApp() throws IOException, SQLException {
        app = App.getApp();
    }

    @Test
    public void testMainPage() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.get("/");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string().contains("Анализатор страниц"));
        });
    }

    @Test
    public void testAddUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://github.com";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string().contains("https://github.com"));
        });
    }

    @Test
    public void testAddUrl2() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=http://localhost:7070/abracodabre";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string().contains("http://localhost:7070"));
        });
    }

    @Test
    public void testAddWrongUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=123";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string().contains("Некорректный URL"));
        });
    }

    @Test
    public void testAddTwoEqualsUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://github.com";
            var response = client.post("/urls", requestBody);
            requestBody = "url=https://github.com/copy";
            response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string().contains("Страница уже существует"));
        });
    }

    @Test
    public void testShowAddedSites() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://github.com";
            var response = client.post("/urls", requestBody);
            requestBody = "url=http://localhost:7070/abracodabre";
            response = client.post("/urls", requestBody);

            assertThat(response.body().string().contains("http://localhost:7070")).isTrue();

            response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testCheckAddedUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://github.com";
            var response = client.post("/urls", requestBody);
            response = client.get("/urls/1");
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string().contains("https://github.com"));
        });
    }

    @Test
    public void testCheckNotAddedUrl() {
        JavalinTest.test(app, (server, client) -> {
            var response = client.post("/urls/23498");
            assertThat(response.code()).isEqualTo(404);
        });
    }

}
