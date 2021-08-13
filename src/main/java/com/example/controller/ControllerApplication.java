package com.example.controller;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.Ports;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.core.DockerClientImpl;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import com.github.dockerjava.transport.DockerHttpClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import static com.github.dockerjava.api.model.HostConfig.newHostConfig;

@SpringBootApplication
public class ControllerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ControllerApplication.class, args);

		//String[] args1 = new String[] {"java", "-jar", "-Dserver.port=7012", "-Dspring.profiles.active=production", "server.jar"};
		//Runtime.getRuntime().exec("java -jar -Dserver.port=7012 -Dspring.profiles.active=production server.jar");

		/*String SERVER_URI = "tcp://localhost:2375";

		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
				.withDockerHost(SERVER_URI)
				.withDockerTlsVerify(true)
				.withDockerCertPath("C:\\Users\\alexa\\.docker")
				.build();
		//DockerClient dockerClient = DockerClientBuilder
		//.getInstance(config)
		//.build();


		DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
				.dockerHost(config.getDockerHost())
				.sslConfig(config.getSSLConfig())
				.maxConnections(100)
				.connectionTimeout(Duration.ofSeconds(30))
				.responseTimeout(Duration.ofSeconds(45))
				.build();

		DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);

		ExposedPort tcp4444 = ExposedPort.tcp(2375);
		Ports portBindings = new Ports();
		portBindings.bind(tcp4444, Ports.Binding.bindPort(2375));

// create container from image
		CreateContainerResponse container = dockerClient.createContainerCmd("serverdocker")
				.withExposedPorts(tcp4444)
				.withHostConfig(newHostConfig()
						.withPortBindings(portBindings))
				.withName("serverdocker")
				.exec();

// start the container
		dockerClient.startContainerCmd(container.getId()).exec();*/
		//Process proc = new ProcessBuilder(args1).start();

		/*String SERVER_URI = "tcp://localhost:2376";

		Properties properties = new Properties();
		properties.setProperty("SERVER_PORT" +
				"", "8081");

		DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
				.withDockerHost(SERVER_URI)
				.withDockerTlsVerify(true)
				.withDockerCertPath("C:\\Users\\alexa\\.docker")
				.build();
		//DockerClient dockerClient = DockerClientBuilder
				//.getInstance(config)
				//.build();


		DockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
				.dockerHost(config.getDockerHost())
				.sslConfig(config.getSSLConfig())
				.maxConnections(100)
				.connectionTimeout(Duration.ofSeconds(30))
				.responseTimeout(Duration.ofSeconds(45))
				.build();

		DockerClient dockerClient = DockerClientImpl.getInstance(config, httpClient);
		dockerClient.buildImageCmd();
dockerClient.listContainersCmd();*/
		//DockerClient dockerClient1 = DockerClientBuilder.getInstance(config).build();
		//System.out.println(dockerClient1.listContainersCmd().withShowAll(true).exec());
		//dockerClient.
		//dockerClient1.pingCmd().exec();


		//List<Container> containers = dockerClient1.listContainersCmd()
		//		.withShowSize(true)
		//		.withShowAll(true)
		//		.withStatusFilter(Collections.singleton("exited")).exec();
		/*DockerClientConfig custom = DefaultDockerClientConfig.createDefaultConfigBuilder()
				.withDockerHost("http://localhost:8080/api/auction")
				.withDockerTlsVerify(true)
				.withDockerCertPath("/home/user/.docker")
				.withRegistryUsername(registryUser)
				.withRegistryPassword(registryPass)
				.withRegistryEmail(registryMail)
				.withRegistryUrl(registryUrl)
				.build();*/
	}

}
