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
    MockWebServer server;

    @BeforeEach
    public final void setApp() throws IOException, SQLException {
        app = App.getApp();
        server = new MockWebServer();
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
    public void testAddUri() {
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
            client.post("/urls", requestBody);
            requestBody = "url=https://github.com/copy";
            var response = client.post("/urls", requestBody);
            assertThat(response.code()).isEqualTo(200);
            assertThat(response.body().string().contains("Страница уже существует"));
        });
    }

    @Test
    public void testShowAddedSites() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://github.com";
            client.post("/urls", requestBody);
            requestBody = "url=http://localhost:7070/abracodabre";
            var response = client.post("/urls", requestBody);

            assertThat(response.body().string().contains("http://localhost:7070")).isTrue();

            response = client.get("/urls");
            assertThat(response.code()).isEqualTo(200);
        });
    }

    @Test
    public void testCheckAddedUrl() {
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=https://github.com";
            client.post("/urls", requestBody);
            var response = client.get("/urls/1");
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

    @Test
    public void testUrlCheckCode200() throws IOException {
        HttpUrl baseUrl = server.url("https://123.com");
        server.enqueue(new MockResponse().setResponseCode(200));
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=" + baseUrl;
            client.post("/urls", requestBody);
            client.post("/urls/1/checks");
            var response = client.get("/urls/1");
            assertThat(response.body().string().contains("200"));
        });
        server.shutdown();
    }

    @Test
    public void testUrlCheckCode404() throws IOException {
        HttpUrl baseUrl = server.url("https://123.com");
        server.enqueue(new MockResponse().setResponseCode(404));
        JavalinTest.test(app, (server, client) -> {
            var requestBody = "url=" + baseUrl;
            client.post("/urls", requestBody);
            client.post("/urls/1/checks");
            var response = client.get("/urls/1");
            assertThat(response.body().string().contains("404"));
        });
        server.shutdown();
    }

}
