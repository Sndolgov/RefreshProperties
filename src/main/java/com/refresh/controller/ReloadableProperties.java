package com.refresh.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import lombok.Cleanup;

public abstract class ReloadableProperties {
    @Autowired
    protected StandardEnvironment environment;

    private long lastModTime = 0L;
    private Path configPath = null;
    private PropertySource<?> appConfigPropertySource = null;

    @PostConstruct
    private void stopIfProblemsCreatingContext() {
        System.out.println("reloading");
        MutablePropertySources propertySources = environment.getPropertySources();
        for (PropertySource<?> propertySource : propertySources) {
            if (propertySource.getName().contains("name")) {
                appConfigPropertySource = propertySource;
                break;
            }
        }
//        Optional<PropertySource<?>> appConfigPsOp =
//                StreamSupport.stream(propertySources.spliterator(), false)
//                        .filter(ps -> ps.getName().matches("^.*applicationConfig.*:.*$"))
//                        .findFirst();
//        if (!appConfigPsOp.isPresent())  {
//            // this will stop context initialization
//            // (i.e. kill the spring boot program before it initializes)
//            throw new RuntimeException("Unable to find property Source as file");
//        }
//        appConfigPropertySource = appConfigPsOp.get();

        if (appConfigPropertySource != null) {
            String filename = appConfigPropertySource.getName();
            filename = filename
//                    .replace("applicationConfig: [file:", "")
                    .replace("class path resource [", "")
                    .replaceAll("\\]$", "");

            configPath = Paths.get(filename);
        }
    }

    public void reload() throws IOException {
        System.out.println("reloading...");
        Properties properties = new Properties();
//        @Cleanup InputStream inputStream = Files.newInputStream(configPath);
        @Cleanup InputStream inputStream =getClass().getClassLoader().getResourceAsStream("name.properties");
        properties.load(inputStream);
        environment.getPropertySources()
                .replace(
                        appConfigPropertySource.getName(),
                        new PropertiesPropertySource(
                                appConfigPropertySource.getName(),
                                properties
                        )
                );
        System.out.println("Reloaded.");
        propertiesReloaded();
//        }
    }

    protected abstract void propertiesReloaded();
}
