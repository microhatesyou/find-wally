package owl.discovery.findwally.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NamingServiceTest {

	@Autowired
	private NamingService namingService; 
	
	@Test
	public void withoutVersion() {
		assertThat(namingService.getSimpleName("ip-ejb.jar")).isEqualTo("ip-ejb.jar");  
	}

	@Test
	public void withVersion() {
		assertThat(namingService.getSimpleName("lib/spring-expression-3.2.4.RELEASE.jar")).isEqualTo("lib/spring-expression.jar");  
	}

	@Test
	public void withVersionWithoutDot() {
		assertThat(namingService.getSimpleName("lib/json-20090211.jar")).isEqualTo("lib/json.jar");  
	}

	@Test
	public void complexVersion() {
		assertThat(namingService.getSimpleName("lib/whole-langs-core-1.0.0-v20110512-1609.jar")).isEqualTo("lib/whole-langs-core.jar");  
	}
}
