package com.webpack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.File;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.Set;

@SpringBootApplication
public class WebpackApplication implements EmbeddedServletContainerCustomizer {

	private final Logger log = LoggerFactory.getLogger(WebpackApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WebpackApplication.class, args);
	}

	// To Manage Static Web Resources when building for production

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
		mappings.add("html","text/html;charset=utf-8");
		mappings.add("json","text/html;charset=utf-8");
		container.setMimeMappings(mappings);
		//Set static web assets
		setStaticAssets(container);
	}

	private void setStaticAssets(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
		String prefixPath = resolvePathPrefix();
		log.info(prefixPath);
		File root = new File(prefixPath + "/build/www");
		if ( root.exists() && root.isDirectory() ) {
			log.info("Root Folder successfully configured ");
			configurableEmbeddedServletContainer.setDocumentRoot(root);
		} else {
			log.info("Please Build Project first");
		}

	}

	private String resolvePathPrefix() {
        String rootpath = Paths.get(".").toUri().normalize().getPath();
		return rootpath;
	}
}
