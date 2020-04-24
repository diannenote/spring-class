package sam07;

public class MessageBeanImpl implements Vehicle {
	  private String  name;
	  private String  rider;
	  private int     speed;
    
	  public MessageBeanImpl(String name) {
		 this.name = name;
	  } 
	  
	  public void setRider(String rider) {
		this.rider = rider;
	  }
	  public void setSpeed(int speed) {
		this.speed = speed;
	  }
	  
	  public void ride() {
	    	  System.out.println(name+" 은 " + 
	  rider + "을 이용하여  "+speed+"KM 속도 냄");
	  }	  

}
