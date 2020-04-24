package sam08;
// import sam08.MessageBean;
// import sam08.Outputter;
public class MessageBeanImpl implements MessageBean {
	private String name;
	private String greeting;
	private Outputter output;
	
	public void setName(String name) {	
		this.name = name;	
		}
	public void setGreeting(String greeting) {
		this.greeting = greeting;
	}
	public void setOutput(Outputter output) {
		this.output = output;
	}		
	public void sayHello() {
		String msg = name+"님 !! "+greeting;
		System.out.println(msg);
		if (output!=null) {		output.output(msg);	}
	}
}