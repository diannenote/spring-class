package sam03;

import org.springframework.beans.factory.BeanFactory;
// import org.springframework.beans.factory.xml.XmlBeanFactory; 구버전 
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.sun.glass.ui.Application;

public class Ex01 {
     public static void main(String[] args) {
    //	 ApplicationContext bf = new FileSystemXmlApplicationContext("bean01.xml");
       	 ApplicationContext bf = new FileSystemXmlApplicationContext( "C:\\spring\\springSrc39\\ch01\\java\\sam03\\bean01.xml");
       	 
    //	 ApplicationContext bf = new FileSystemXmlApplicationContext("bean01.xml");
    //	 MessageBean mb = bf.getBean("mb", MessageBean.class);
    	 MessageBean mb = (MessageBean)bf.getBean("mb");
    	 mb.sayHello("spring");
     }
     
}
