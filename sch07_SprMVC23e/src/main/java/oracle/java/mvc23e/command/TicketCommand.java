package oracle.java.mvc23e.command;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import oracle.java.mvc23e.dao.TicketDao;
import oracle.java.mvc23e.dto.TicketDto;

public class TicketCommand implements ITicketCommand {

	private TicketDao ticketDao;
	private TransactionTemplate transactionTemplate2;
	
	public void setTransactionTemplate2(TransactionTemplate transactionTemplate2) {
		this.transactionTemplate2 = transactionTemplate2;
	}
	
	public void setTicketDao(TicketDao ticketDao) {
		this.ticketDao = ticketDao;
	}
	
	public TicketDao getTicketDao() {
		return ticketDao;
	}
	// class의 인스턴스가 만들어지는 시점에 메서드의 값이 인스턴스 필드에 복사되는 구조이다. 따라서 한 곳에서 값을 변경하는 경우 서로 
	// 다른 값이 되기 때문에 문제가 발생할 가능성이 많기 때문
	@Override
	public void execute(final TicketDto ticketDto) {
//		ticketDto.setAmount("1");
//		ticketDao.buyTicket(ticketDto);
//		
//		ticketDto.setAmount("7");
//		ticketDao.buyTicket(ticketDto);
	
        transactionTemplate2.execute(new TransactionCallbackWithoutResult() {
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus status) {
				ticketDto.setAmount("1");
				ticketDao.buyTicket(ticketDto);
				
				ticketDto.setAmount("7");
				ticketDao.buyTicket(ticketDto);
			}
		}); 
	}

}
