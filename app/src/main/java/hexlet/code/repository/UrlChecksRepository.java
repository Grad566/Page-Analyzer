package hexlet.code.repository;

import hexlet.code.model.UrlCheck;
import hexlet.code.utils.TimeUtils;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UrlChecksRepository extends BaseRepository{
    public static List<UrlCheck> getUrlChecksBySiteId(Long id) throws SQLException {
        var result = new ArrayList<UrlCheck>();
        var sql = "SELECT * FROM url_checks WHERE url_id = ?";
        try (var conn = dataSource.getConnection();
                var prepareStmt = conn.prepareStatement(sql)) {
            prepareStmt.setLong(1, id);
            var resultSet = prepareStmt.executeQuery();
            while (resultSet.next()) {
                var checkId = resultSet.getLong("id");
                var statusCode = resultSet.getInt("status_code");
                var title = resultSet.getString("title");
                var h1 = resultSet.getString("h_1");
                var description = resultSet.getString("description");
                var created_at = resultSet.getTimestamp("created_at");
                var formattedCreatedAt = TimeUtils.getFormattedData(created_at);
                var urlCheck = UrlCheck.builder()
                                        .id(checkId)
                                        .description(description)
                                        .h1(h1)
                                        .title(title)
                                        .createdAt(formattedCreatedAt)
                                        .urlId(id)
                                        .statusCode(statusCode)
                                        .build();
                result.add(urlCheck);
            }

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }

        return result;
    }

    public static void save(UrlCheck urlCheck) throws SQLException {
        var sql = "INSERT INTO url_checks (status_code, title, h_1, description, url_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
        try (var conn = dataSource.getConnection();
                var prepareStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStmt.setInt(1, urlCheck.getStatusCode());
            prepareStmt.setString(2, urlCheck.getTitle());
            prepareStmt.setString(3, urlCheck.getH1());
            prepareStmt.setString(4, urlCheck.getDescription());
            prepareStmt.setLong(5, urlCheck.getUrlId());
            prepareStmt.executeUpdate();

            var generatedKeys = prepareStmt.getGeneratedKeys();
            if ((generatedKeys.next())) {
                urlCheck.setId(generatedKeys.getLong(1));
                var createdAt = generatedKeys.getTimestamp("created_at");
                var formattedCreatedAt = TimeUtils.getFormattedData(createdAt);
                urlCheck.setCreatedAt(formattedCreatedAt);
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
