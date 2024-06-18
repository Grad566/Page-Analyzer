package hexlet.code.repository;

import hexlet.code.model.Url;
import hexlet.code.utils.TimeUtils;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class UrlsRepository extends BaseRepository {
    public static void save(Url url) throws SQLException {
        var sql = "INSERT INTO urls (name, created_at) VALUES (?, ?)";
        var time = new Timestamp(System.currentTimeMillis());
        try (var conn = dataSource.getConnection();
                var prepareStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStmt.setString(1, url.getName());
            prepareStmt.setTimestamp(2, time);
            prepareStmt.executeUpdate();

            var generatedKey = prepareStmt.getGeneratedKeys();
            if (generatedKey.next()) {
                url.setId(generatedKey.getLong(1));
                var createdAt = generatedKey.getTimestamp("created_at");
                var formattedCreatedAt = TimeUtils.getFormattedData(createdAt);
                url.setCreatedAt(formattedCreatedAt);
            }
        }
    }

    public static List<Url> getUrls() throws SQLException {
        var sql = "SELECT * FROM urls";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<Url>();
            while (resultSet.next()) {
                var id = resultSet.getLong("id");
                var name = resultSet.getString("name");
                var createdAt = resultSet.getTimestamp("created_at");
                var formattedCreatedAt = TimeUtils.getFormattedData(createdAt);
                var site = new Url(id, name, formattedCreatedAt);
                result.add(site);
            }
            return result;
        }
    }

    public static Optional<Url> getById(Long id) throws SQLException {
        var sql = "SELECT * FROM urls WHERE id = ?";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var createdAt = resultSet.getTimestamp("created_at");
                var formattedCreatedAt = TimeUtils.getFormattedData(createdAt);
                var site = new Url(id, name, formattedCreatedAt);
                return Optional.of(site);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
