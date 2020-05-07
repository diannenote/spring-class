package oracle.java.mvc222.dto;

public class TicketDto {

	private String consumerId;
	private String amount;
	private String countnum;
	
	public String getCountnum() {
		return countnum;
	}
	public void setCountnum(String countnum) {
		this.countnum = countnum;
	}
	public String getConsumerId() {
		return consumerId;
	}
	public void setConsumerId(String consumerId) {
		this.consumerId = consumerId;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
