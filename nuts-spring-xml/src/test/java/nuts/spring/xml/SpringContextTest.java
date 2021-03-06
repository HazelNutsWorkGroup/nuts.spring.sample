package nuts.spring.xml;

import nuts.spring.xml.model.Person;
import nuts.spring.xml.util.ContextType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.util.Arrays;

class SpringContextTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("testGetPersonBean")
    void testGetPersonBean() {
        SpringContext springContext = new SpringContext();
        Person person = springContext.getPersonBean();
        Assert.isInstanceOf(Person.class, person);
    }

    @Test
    @DisplayName("testGetBeanNames")
    void testGetBeanNames() {
        SpringContext springContext = new SpringContext();
        String[] beanNames = springContext.getBeanNames();
        for (String beanName : beanNames) {
            System.out.println("===>" + beanName);
        }
        Assert.isTrue(Arrays.asList(beanNames).contains("helloController"), "controller");
    }

    @Test
    @DisplayName("testExcludeBeanNames")
    void testExcludeBeanNames() {
        SpringContext springContext = new SpringContext(ContextType.Exclude);
        String[] beanNames = springContext.getBeanNames();
        for (String beanName : beanNames) {
            System.out.println("===>" + beanName);
        }
        Assert.isTrue(!Arrays.asList(beanNames).contains("helloController"), "Controller");
        Assert.isTrue(Arrays.asList(beanNames).contains("helloService"), "Service");
    }

    @Test
    @DisplayName("testIncludeBeanNames")
    void testIncludeBeanNames() {
        SpringContext springContext = new SpringContext(ContextType.Include);
        String[] beanNames = springContext.getBeanNames();
        for (String beanName : beanNames) {
            System.out.println("===>" + beanName);
        }
        Assert.isTrue(!Arrays.asList(beanNames).contains("helloController"), "Controller");
        Assert.isTrue(Arrays.asList(beanNames).contains("helloService"), "Service");
    }

    @Test
    @DisplayName("testCustomBeanNames")
    void testCustomBeanNames() {
        SpringContext springContext = new SpringContext(ContextType.Custom);
        String[] beanNames = springContext.getBeanNames();
        for (String beanName : beanNames) {
            System.out.println("===>" + beanName);
        }
        Assert.isTrue(Arrays.asList(beanNames).contains("helloService"), "Service");

    }
}