# JDBC in Java

## JDBC Overview

In order to work with JDBC in Java, we need to follow these steps:

1. **Add Required Modules**:
   - Add the `java.sql` module and any required database connector (e.g., `org.postgresql.*` for PostgreSQL).

2. **Register the Database Driver**:
   - Register the database driver with `DriverManager`:
     ```java
     DriverManager.registerDriver(new org.postgresql.Driver());
     ```

3. **Establish a Connection**:
   - Get a connection from the driver:
     ```java
     Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
     ```

4. **Create a Statement**:
   - Create the appropriate statement based on the type of query:
     ```java
     Statement s = connection.createStatement();
     PreparedStatement ps = connection.prepareStatement(SQL_QUERY);
     CallableStatement cs = connection.prepareCall(PROCEDURE_CALL);
     ```

5. **Execute the Query**:
   - Execute the query using one of the following methods:
     - `execute`: Generically used with `SELECT`, `UPDATE`, `DELETE`, `CREATE`.
     - `executeQuery`: Used only with `SELECT` queries which return a `ResultSet`.
     - `executeUpdate`: Used with `UPDATE`, `DELETE`, `CREATE` queries which do not return a `ResultSet`.
     ```java
     s.execute(SQL_QUERY);
     ps.executeQuery(); // for SELECT
     ps.executeUpdate(); // for UPDATE/DELETE/CREATE
     ```

6. **Process the Result**:
   - If multiple rows are retrieved by a `ResultSet`, store them in a collection class (e.g., `ArrayList`, `HashMap`) for further processing.

7. **Close Resources**:
   - Always close resources (like `Connection`, `Statement`, and `ResultSet`) to prevent memory leaks:
     ```java
     resultSet.close();
     statement.close();
     connection.close();
     ```

---

## Statement Types

### 1. **Statement**
   - **Usage**: Executes static SQL queries that don't require user inputs.
   - **When to Use**: The query is executed once or infrequently, and performance is not a significant concern. Not suitable for SQL queries with user inputs.

### 2. **PreparedStatement**
   - **Usage**: Executes SQL queries with parameters (parameterized queries).
   - **Benefits**:
     - Protects against SQL injection.
     - Pre-compiles the query, improving performance for repeated queries.
   - **When to Use**: Ideal for queries with user inputs.

### 3. **CallableStatement**
   - **Usage**: Executes SQL stored procedures or functions.
   - **Can Also Be Used**:
     - To execute normal SQL queries with parameters.
     - Protects against SQL injection vulnerabilities.
   - **When to Use**: When working with stored procedures or calling functions in the database.

---

## Summary of Methods

| Method            | Description                                                | Expected Result     |
|-------------------|------------------------------------------------------------|---------------------|
| `execute()`       | Used for any type of query (SELECT, UPDATE, DELETE, etc.).  | Returns a boolean: `true` if a `ResultSet` is returned, `false` otherwise. |
| `executeQuery()`  | Used for `SELECT` queries.                                 | Returns a `ResultSet` containing the query result. |
| `executeUpdate()` | Used for `INSERT`, `UPDATE`, `DELETE`, and other non-`SELECT` queries. | Returns an integer indicating the number of affected rows. |

---

## Key Points

- **`Statement`**: Static queries, no user input.
- **`PreparedStatement`**: Dynamic queries with user input, protects against SQL injection.
- **`CallableStatement`**: Executes stored procedures and functions, handles parameters.
- **Always close your resources** (connections, statements, result sets) to avoid memory leaks.

---

## Additional features in JDBC
### 1. Batch Processing

Batch processing allows multiple SQL queries to be executed together, which can improve performance significantly.

Example of executing a batch of insert queries:

```java
for (Employee e : employees) {
    ps.setInt(1, e.getId());
    ps.setString(2, e.getName());
    ps.setDouble(3, e.getSalary());
    ps.addBatch(); // Add to batch
}
int[] results = ps.executeBatch();  // Execute all the queries in the batch
```
### Transaction Management

In JDBC, transactions are managed automatically, but you can disable auto-commit to manage transactions manually for multiple queries.
```java
connection.setAutoCommit(false);  // Disable auto-commit

try {
    // Execute multiple queries

    connection.commit();  // Commit transaction if all queries are successful
} catch (SQLException ex) {
    connection.rollback();  // Rollback in case of error
}
```
### Connection pooling

* Connection pooling improves performance by reusing existing database connections, avoiding the overhead of opening and closing connections repeatedly.
* We used HikariCP here. To use this we added HikariCP, slf4j API, slf4j-simple provider JARs(since this is a java code) to work with it.

```java
public static HikariConfig getConfig() {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(URL);
    config.setUsername(USER);
    config.setPassword(PASSWORD);
    config.setMaximumPoolSize(10);  // Set maximum number of connections in the pool
    config.setIdleTimeout(300000);  // Timeout for idle connections
    config.setAutoCommit(true);  // Enable auto-commit for all connections
    config.setDriverClassName("org.postgresql.Driver");
    config.setMaxLifetime(180000);  // Set max lifetime for connections

    return config;
}

HikariDataSource hds = new HikariDataSource(getConfig());
Connection connection = hds.getConnection();  // Get connection from pool
```

## Note

* Java 9+ Modularization: From Java 9, JDK libraries are modularized. You can declare modules using **module-info.java** to specify dependencies, e.g., requires java.sql; for JDBC.
* **JDBC: Part of the java.sql module** for CRUD operations in modular applications.
* Unmodularized Applications: You can still use JDBC and **work without a module-info.java** file in non-modular applications (as before Java 9)
    * Dependencies (like JDBC) are managed via the classpath, not modules.
