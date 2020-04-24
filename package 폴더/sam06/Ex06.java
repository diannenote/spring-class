package sam06;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Ex06 {
     public static void main(String[] args) {
  		ApplicationContext ac = new ClassPathXmlApplicationContext("bean06.xml");
		
		MessageBean mb = (MessageBean)ac.getBean("mb6");
//		MessageBeanImpl mb2 = new MessageBeanImpl();
//		mb2.setName("이주원");
//		mb2.setGreet("안녕");
		mb.sayHello();
	}
}
