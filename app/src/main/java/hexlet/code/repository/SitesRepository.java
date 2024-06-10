package hexlet.code.repository;

import hexlet.code.model.Site;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class SitesRepository extends BaseRepository {
    public static void save(Site site) throws SQLException {
        var sql = "INSERT INTO sites (name) VALUES (?)";
        try (var conn = dataSource.getConnection();
                var prepareStmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareStmt.setString(1, site.getName());
            prepareStmt.executeUpdate();

            var generatedKey = prepareStmt.getGeneratedKeys();
            if (generatedKey.next()) {
                site.setId(generatedKey.getLong(1));
                site.setCreatedAt(generatedKey.getString("created_at"));
            }
        }
    }

    public static List<Site> getSites() throws SQLException {
        var sql = "SELECT * FROM sites";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            var resultSet = stmt.executeQuery();
            var result = new ArrayList<Site>();
            while (resultSet.next()) {
                var id = resultSet.getString("id");
                var name = resultSet.getString("name");
                var createdAt = resultSet.getString("created_at");
                var site = new Site(Long.parseLong(id), name, createdAt);
                result.add(site);
            }
            return result;
        }
    }

    public static Optional<Site> getById(Long id) throws SQLException {
        var sql = "SELECT * FROM sites WHERE id = ?";
        try (var conn = dataSource.getConnection();
                var stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var createdAt = resultSet.getTimestamp("created_at");
                var site = new Site(id, name, String.valueOf(createdAt));
                return Optional.of(site);
            } else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        }
    }
}
