package net.snascimento.contentcalendar.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import net.snascimento.contentcalendar.model.Content;
import net.snascimento.contentcalendar.model.Status;
import net.snascimento.contentcalendar.model.Type;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContentJdbcTemplateRepository {

  private final JdbcTemplate jdbcTemplate;

  public ContentJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private static Content mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Content(
        rs.getInt("id"),
        rs.getString("title"),
        rs.getString("desc"),
        Status.valueOf(rs.getString("status")),
        Type.valueOf(rs.getString("content_type")),
        rs.getTimestamp("date_created").toLocalDateTime(),
        rs.getTimestamp("date_updated").toLocalDateTime(),
        rs.getString("url"));
  }

  public List<Content> getAllContent() {
    String sql = "SELECT * FROM Content";
    return jdbcTemplate.query(sql, ContentJdbcTemplateRepository::mapRow);
  }

  public void createContent(
      String title, String desc, String status, String contentType, String url) {
    String sql =
        "INSERT INTO Content(title, desc, status,content_type, date_created, url) VALUES (?,?,?,?, NOW(),?)";
    jdbcTemplate.update(sql, title, desc, status, contentType, url);
  }

  public void updateContent(
      int id, String title, String desc, String status, String contentType, String url) {
    String sql =
        "UPDATE Content SET title=?, desc=?, status=?, content_type=?, date_updated=NOW(), url=?) WHERE id=?";
    jdbcTemplate.update(sql, title, desc, status, contentType, url, id);
  }

  public void deleteContent(int id) {
    String sql = "DELETE FROM Content WHERE id =?";
    jdbcTemplate.update(sql, id);
  }

  public Content getContent(int id) {
    String sql = "SELECT * FROM Content WHERE id=?";
    return jdbcTemplate.queryForObject(sql, ContentJdbcTemplateRepository::mapRow, id);
  }
}
