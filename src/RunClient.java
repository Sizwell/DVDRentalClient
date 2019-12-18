import java.io.IOException;

public class RunClient
{
    Gui clientGui;
    ServerConnection server;

    public RunClient()
    {
        try
        {
            server = new ServerConnection();
            server.connectToServer();
            System.out.println("Connected>>>");
            server.createStreams();
            Gui clientGui = new Gui();
            server.fillDvds(clientGui.tableModelMovies);
            server.fillCustomers(clientGui.tableModelCustomers);

            server.fillRentals(clientGui.tableModelRented);
        }catch (IOException io)
        {

        }catch (ClassNotFoundException cnf)
        {
            
        }
    }

    public static void main(String[] args)
    {
        new RunClient();
    }
}
