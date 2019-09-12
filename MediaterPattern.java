public interface ChatMediator {

	public void sendMessage(String msg, User user);

	void addUser(User user);
}
public abstract class User {
	protected ChatMediator mediator;
	protected String name;
	
	public User(ChatMediator med, String name){
		this.mediator=med;
		this.name=name;
	}
	
	public abstract void send(String msg);
	
	public abstract void receive(String msg);
}
public class ChatMediatorImpl implements ChatMediator {

	private List<User> users;
	
	public ChatMediatorImpl(){
		this.users=new ArrayList<>();
	}
	
	public void addUser(User user){
		this.users.add(user);
	}
	
	public void sendMessage(String msg, User user) {
		for(User u : this.users){
			if(u != user){
				u.receive(msg);
			}
		}
	}

}
public class UserImpl extends User {

	public UserImpl(ChatMediator med, String name) {
		super(med, name);
	}

	public void send(String msg){
		System.out.println(this.name+": Sending Message="+msg);
		mediator.sendMessage(msg, this);
	}

	public void receive(String msg) {
		System.out.println(this.name+": Received Message:"+msg);
	}

}