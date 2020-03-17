package nuts.spring.annotation;

import nuts.spring.annotation.config.*;
import nuts.spring.annotation.model.Person;
import nuts.spring.annotation.util.ContextType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <p>
 * Spring Context Instance
 * </p>
 *
 * @author sleeber
 * @date 2020-03-15
 */
public class SpringContext {

    private ApplicationContext context;

    public SpringContext() {
        context = new AnnotationConfigApplicationContext(DefaultConfiguration.class);
    }

    public SpringContext(ContextType contextType) {
        switch (contextType) {
            case Exclude: {
                context = new AnnotationConfigApplicationContext(ExcludeConfiguration.class);
                break;
            }
            case Include: {
                context = new AnnotationConfigApplicationContext(IncludeConfiguration.class);
                break;
            }
            case Custom: {
                context = new AnnotationConfigApplicationContext(CustomerConfiguration.class);
                break;
            }
            case Scope: {
                context = new AnnotationConfigApplicationContext(ScopeConfiguration.class);
                break;
            }
            case Lazy: {
                context = new AnnotationConfigApplicationContext(LazyConfiguration.class);
                break;
            }
            case Conditional: {
                context = new AnnotationConfigApplicationContext(ConditionalConfiguration.class);
                break;
            }
            case Import: {
                context = new AnnotationConfigApplicationContext(ImportConfiguration.class);
                break;
            }

            default: {
                context = new AnnotationConfigApplicationContext(DefaultConfiguration.class);
            }
        }
    }

    public Person getPersonBean() {
        return (Person) context.getBean("person");
    }

    public String[] getBeanNames() {
        return context.getBeanDefinitionNames();
    }

}
