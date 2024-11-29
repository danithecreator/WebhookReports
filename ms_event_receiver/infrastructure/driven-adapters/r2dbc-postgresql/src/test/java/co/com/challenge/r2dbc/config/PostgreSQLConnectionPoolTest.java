package co.com.challenge.r2dbc.config;

import io.r2dbc.pool.ConnectionPool;
import io.r2dbc.pool.ConnectionPoolConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostgreSQLConnectionPoolTest {
    @InjectMocks
    private PostgreSQLConnectionPool postgreSQLConnectionPool;

    @Mock
    private PostgresqlConnectionProperties properties;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetConnectionConfig() {
        PostgresqlConnectionProperties mockProperties = mock(PostgresqlConnectionProperties.class);
        when(mockProperties.host()).thenReturn("localhost");
        when(mockProperties.port()).thenReturn(5432);
        when(mockProperties.database()).thenReturn("testdb");
        when(mockProperties.schema()).thenReturn("public");
        when(mockProperties.username()).thenReturn("testuser");
        when(mockProperties.password()).thenReturn("testpassword");
        PostgreSQLConnectionPool connectionPool = new PostgreSQLConnectionPool();
        connectionPool.initialSize = 10;
        connectionPool.maxSize = 20;
        connectionPool.maxIdleTime = 5;
        ConnectionPool pool = connectionPool.getConnectionConfig(mockProperties);
        assertNotNull(pool);

    }
}
