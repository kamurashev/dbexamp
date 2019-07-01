package com.stepdevstep.dbexam.dao;

import com.stepdevstep.dbexam.persistence.SQLConnectionBuilder;
import com.stepdevstep.dbexam.entities.Audiobook;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlABookDAO implements ABookDAOi {

    private static final String SELECT_ALL = "SELECT * FROM audiobooks";
    private static final String SELECT_ID = "SELECT * FROM audiobooks WHERE book_id=?";
    private static final String SELECT_NAME = "SELECT * FROM audiobooks WHERE name=?";

    private static final String SELECT_RANGE_PFX = "SELECT * FROM audiobooks WHERE genre LIKE ? AND author LIKE ?" +
            " AND speaker LIKE ? AND name LIKE ? ORDER BY ";
    private static final String SELECT_RANGE_SFX = " DESC";


    private static final String INSERT = "INSERT INTO audiobooks " +
            "(name,genre,author,speaker,duration,rate,description,cover,content)" +
            "VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String UPDATE = "UPDATE audiobooks SET " +
            "name=?, genre=?, author=?, speaker=?, duration=?, views=?, rate=?, description=?, cover=?, content=? " +
            "WHERE book_id=?";
    private static final String DELETE = "DELETE FROM audiobooks WHERE book_id=?";


    public int addBook(Audiobook book) {
        int book_id = -1;
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(INSERT, new String[]{"book_id"})
        ) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getGenre());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getSpeaker());
            preparedStatement.setString(5, book.getDuration());
            preparedStatement.setFloat(6, book.getRate());
            preparedStatement.setString(7, book.getDescription());
            preparedStatement.setString(8, book.getCover());
            preparedStatement.setString(9, book.getContent());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) throw new SQLException("Book creating failed, no rows affected.");

            try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
                if (rs.next()) {
                    book_id = rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book_id;
    }

    public int updateBook(Audiobook book) {
        int affectedRows = 0;
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(UPDATE)) {
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getGenre());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getSpeaker());
            preparedStatement.setString(5, book.getDuration());
            preparedStatement.setInt(6, book.getViews());
            preparedStatement.setFloat(7, book.getRate());
            preparedStatement.setString(8, book.getDescription());
            preparedStatement.setString(9, book.getCover());
            preparedStatement.setString(10, book.getContent());
            preparedStatement.setInt(11, book.getBook_id());

            affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) throw new SQLException("Book updating failed, no rows affected.");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public int deleteBook(int book_id) {
        int affectedRows = 0;
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, book_id);
            affectedRows = preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return affectedRows;
    }

    public Audiobook getBook(int book_id) {
        Audiobook book = null;
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SELECT_ID)) {
            preparedStatement.setInt(1, book_id);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                if (rs.next()) {
                    book = fill(rs);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    public List<Audiobook> getBooks(String name) {

        List<Audiobook> books = new ArrayList<>();
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SELECT_NAME)) {
            preparedStatement.setString(1, name);
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    books.add(fill(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Audiobook> getBooks() {
        List<Audiobook> books = new ArrayList<>();
        try (Connection con = SQLConnectionBuilder.getConnection();
             PreparedStatement preparedStatement = con.prepareStatement(SELECT_ALL)) {
            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    books.add(fill(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public List<Audiobook> getBooks(String genre, String author,
                                    String speaker, String name, String order) {
        List<Audiobook> books = new ArrayList<>();
        order = order.equals("") ? "book_id" : order;                        // If not specified set default sorting(by book_id)
        String sql = SELECT_RANGE_PFX + order + SELECT_RANGE_SFX;                   // Prepare SQL
        try (Connection con = SQLConnectionBuilder.getConnection();

             PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + genre + "%");
            preparedStatement.setString(2, "%" + author + "%");
            preparedStatement.setString(3, "%" + speaker + "%");
            preparedStatement.setString(4, "%" + name + "%");

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    books.add(fill(rs));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    private Audiobook fill(ResultSet rs) {
        Audiobook book = new Audiobook();
        try {
            book.setBook_id(rs.getInt("book_id"));
            book.setName(rs.getString("name"));
            book.setGenre(rs.getString("genre"));
            book.setAuthor(rs.getString("author"));
            book.setSpeaker(rs.getString("speaker"));
            book.setDuration(rs.getString("duration"));
            book.setViews(rs.getInt("views"));
            book.setRate(rs.getFloat("rate"));
            book.setDescription(rs.getString("description"));
            book.setCover(rs.getString("cover"));
            book.setContent(rs.getString("content"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }
}
