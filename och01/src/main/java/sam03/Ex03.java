package sam03;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Ex03 {

	public static void main(String[] args) {
		ApplicationContext bf = new FileSystemXmlApplicationContext("C:\\spring\\springSrc39\\och01\\src\\main\\java\\sam03\\bean01.xml");
		MessageBean mb = (MessageBean) bf.getBean("mb");
		mb.sayHello("spring");
	}

}
