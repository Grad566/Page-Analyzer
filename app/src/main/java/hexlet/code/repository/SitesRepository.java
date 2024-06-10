package hexlet.code.repository;

import hexlet.code.model.Site;
import lombok.Getter;
import lombok.Setter;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SitesRepository extends BaseRepository{
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
            } else {
                throw new SQLException("Страница уже существует");
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
                var created_at = resultSet.getString("created_at");
                var site = new Site(Long.parseLong(id), name, created_at);
                result.add(site);
            }
            return result;
        }
    }
}