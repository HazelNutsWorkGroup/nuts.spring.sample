package nuts.spring.annotation;

import nuts.spring.annotation.model.Person;
import nuts.spring.annotation.util.ContextType;
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

    private void printBeanNames(String[] beanNames) {
        for (String beanName : beanNames) {
            System.out.println("===>" + beanName);
        }
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
        printBeanNames(beanNames);
        Assert.isTrue(Arrays.asList(beanNames).contains("helloController"), "controller");
    }

    @Test
    @DisplayName("testExcludeBeanNames")
    void testExcludeBeanNames() {
        SpringContext springContext = new SpringContext(ContextType.Exclude);
        String[] beanNames = springContext.getBeanNames();
        printBeanNames(beanNames);
        Assert.isTrue(!Arrays.asList(beanNames).contains("helloController"), "Controller");
        Assert.isTrue(Arrays.asList(beanNames).contains("helloService"), "Service");
    }

    @Test
    @DisplayName("testIncludeBeanNames")
    void testIncludeBeanNames() {
        SpringContext springContext = new SpringContext(ContextType.Include);
        String[] beanNames = springContext.getBeanNames();
        printBeanNames(beanNames);
        Assert.isTrue(!Arrays.asList(beanNames).contains("helloController"), "Controller");
        Assert.isTrue(Arrays.asList(beanNames).contains("helloService"), "Service");
    }

    @Test
    @DisplayName("testCustomBeanNames")
    void testCustomBeanNames() {
        SpringContext springContext = new SpringContext(ContextType.Custom);
        String[] beanNames = springContext.getBeanNames();
        printBeanNames(beanNames);
        Assert.isTrue(Arrays.asList(beanNames).contains("helloService"), "Service");

    }

    @Test
    @DisplayName("testInstanceScope")
    void testInstanceScope() {
        SpringContext springContext = new SpringContext(ContextType.Scope);
        System.out.println("Spring Context initialized...");
        Person personBean = springContext.getPersonBean();
        Assert.isInstanceOf(Person.class, personBean);

    }

    @Test
    @DisplayName("testInstanceScope")
    void testInstanceLazy() {
        SpringContext springContext = new SpringContext(ContextType.Lazy);
        System.out.println("Spring Context initialized...");
        Person personBean = springContext.getPersonBean();
        Assert.isInstanceOf(Person.class, personBean);

    }

    @Test
    @DisplayName("testConditional")
    void testConditional() {
        SpringContext springContext = new SpringContext(ContextType.Conditional);
        System.out.println("Spring Context initialized...");
        String[] beanNames = springContext.getBeanNames();
        printBeanNames(beanNames);
    }

    @Test
    @DisplayName("testImportBean")
    void testImportBean() {
        SpringContext springContext = new SpringContext(ContextType.Import);
        System.out.println("Spring Context initialized...");
        String[] beanNames = springContext.getBeanNames();
        printBeanNames(beanNames);
    }

    @Test
    @DisplayName("testFactoryBean")
    void testFactoryBean() {
        SpringContext springContext = new SpringContext(ContextType.FactoryBean);
        System.out.println("Spring Context initialized...");
        String[] beanNames = springContext.getBeanNames();
        printBeanNames(beanNames);
        Object personFactory = springContext.getBean("personFactory");
        System.out.println(personFactory);      //FactoryBean 返回的是要创建的目标对象
        Object personFactory1 = springContext.getBean("&personFactory");
        System.out.println(personFactory1);      //FactoryBean &前缀返回的是自身实例
        Object personFactory2 = springContext.getBean("personFactory");
        System.out.println(personFactory2);      //FactoryBean 返回的是要创建的目标对象

    }


    @Test
    @DisplayName("testBeanLifeCycle")
    void testBeanLifeCycle() {
        SpringContext springContext = new SpringContext(ContextType.LifeCycle);
        System.out.println("Spring Context initialized...");
        String[] beanNames = springContext.getBeanNames();
        printBeanNames(beanNames);

        // singleton 单例，容器初始化过程中创建实例，并调用initMethod，在IoC关闭时自动调用destroy
        // prototype 多例，获取对象时创建实例，并调用initMethod，手动处理destroy
        Object school = springContext.getBean("school");
        System.out.println(school);

        springContext.close();
    }

}