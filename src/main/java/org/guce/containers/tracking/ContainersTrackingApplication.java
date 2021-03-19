package org.guce.containers.tracking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"org.guce.containers.tracking.services.impl", "org.guce.containers.tracking.*"})
@EntityScan("org.guce.containers.tracking.models")
@EnableJpaRepositories("org.guce.containers.tracking.repositories")
public class ContainersTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContainersTrackingApplication.class, args);
	}

}
