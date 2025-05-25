package io.github.kambasdojava.angolaconnectionhub.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Map;

@Configuration
public class DatabaseConfig {
    private static final String H2_DRIVER = "org.h2.Driver";
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final Map<DatabasePlatform, String> PLATFORM_DRIVERS = Map.of(
        DatabasePlatform.H2, H2_DRIVER,
        DatabasePlatform.MYSQL, MYSQL_DRIVER
    );

    @Value("${ach.db.platform}")
    private String platform;

    @Value("${ach.db.url}")
    private String url;

    @Value("${ach.db.username}")
    private String username;

    @Value("${ach.db.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        DatabasePlatform dbPlatform = DatabasePlatform.fromString(platform);
        String driverClassName = PLATFORM_DRIVERS.get(dbPlatform);
        if (driverClassName == null) {
            throw new IllegalStateException("Unsupported database platform: " + platform);
        }
        
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    private enum DatabasePlatform {
        H2("h2"),
        MYSQL("mysql");

        private final String value;

        DatabasePlatform(String value) {
            this.value = value;
        }

        public static DatabasePlatform fromString(String platform) {
            return Arrays.stream(values())
                .filter(p -> p.value.equals(platform))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown platform: " + platform));
        }
    }
}