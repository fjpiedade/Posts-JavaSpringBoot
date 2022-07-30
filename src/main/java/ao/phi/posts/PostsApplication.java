package ao.phi.posts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
@RestController
//@ComponentScan(basePackages = {"ao.phi.posts"})
public class PostsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostsApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Hello Post Manager App";
	}
}
