package org.yearup.data.mysql;

import org.apache.commons.dbcp2.DataSourceConnectionFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Category> getAllCategories()
    {
        //
        List<Category> categoriesList = new ArrayList<>();
        String sql = "SELECT * FROM categories";


        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Category category = mapRow(resultSet);
                categoriesList.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to retrieve categories.", e);
        }

        return categoriesList;
        // get all categories
    }

    @Override
    public Category getById(int categoryId)
    {
        String sql = "SELECT * FROM categories WHERE id = ?";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Bind the categoryId to the query
            statement.setInt(1, categoryId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    // Map the result set to a Category object
                    return mapRow(resultSet);
                } else {
                    // If no category is found, throw an exception
                    throw new RuntimeException("Category not found for ID: " + categoryId);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the category with ID: " + categoryId, e);
        }
    }

    @Override
    public Category create(Category category)
    {
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            // Binds parameters to the query
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());

            // Executes the query
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                // Retrieve the auto-generated key (if applicable)
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        category.setCategoryId(generatedKeys.getInt(1)); // Set the generated ID to the Category object
                    }
                }
            }

            return category;

        } catch (SQLException e) {
            throw new RuntimeException("Error while creating a new category: " + category.getName(), e);
        }
    }

    @Override
    public void update(int categoryId, Category category)
    {    String sql = "UPDATE categories SET name = ?, description = ? WHERE id = ?";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Bind the parameters to the SQL query
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3, categoryId);

            // Execute the update
            int rowsAffected = statement.executeUpdate();

            // Check if any rows were updated
            if (rowsAffected == 0) {
                throw new RuntimeException("No category found with ID: " + categoryId);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while updating the category with ID: " + categoryId, e);
        }
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
        String sql = "DELETE FROM categories WHERE id = ?";

        try (Connection connection = jdbcTemplate.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // Bind the categoryId to the query
            statement.setInt(1, categoryId);

            // Execute the delete query
            int rowsAffected = statement.executeUpdate();

            // Check if any rows were deleted
            if (rowsAffected == 0) {
                throw new RuntimeException("No category found with ID: " + categoryId);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error while deleting the category with ID: " + categoryId, e);
        }
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}
