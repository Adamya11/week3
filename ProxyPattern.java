import java.util.*;
import java.io.*;
public class ProxyPattern {

	public static void main(String[] args){
		CommandExecutor executor = new CommandExecutorProxy("Adamya", "wrong_pwd");
		try {
			executor.runCommand("ls -ltr");
			executor.runCommand(" rm -rf abc.pdf");
		} catch (Exception e) {
			System.out.println("Exception Message::"+e.getMessage());
		}
		
	}

}
public interface CommandExecutor
{
    public void runCommand(String command) throws Exception;
}
public class CommandExecutorImpl implements CommandExecutor
{
    public void runCommand(String command) throws IOException
    {
        Runtime.getRuntime().exec(command); //heavy implementation
        System.out.println("'"+command + " command executed.");
    }
}
public class CommandExecutorProxy implements CommandExecutor
{
    private boolean isAdmin;
    private CommandExecutor executor;
    public CommandExecutorProxy (String user, String pwd)
    {
        if("Adamya".equals(user) && "boshron".equals(pwd))
        isAdmin = true;
        executor = new CommandExecutorImpl();
    }
    public void runCommand(String command) throws Exception
    {
        if(isAdmin)
        {
            executor.runCommand(command);
        }
        else
        {
            if(command.trim().startsWith("rm"))
            {throw new Exception("rm command is not for non-admin users.");}
            else
            {executor.runCommand(command);}
        }
    }
}

