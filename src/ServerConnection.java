import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ServerConnection
{
    private Socket socket;
    private ObjectOutputStream oos;
    private ObjectInputStream ois;

    public ServerConnection() throws IOException
    {
        System.out.println("connect to server>>>");

        //socket = new Socket("127.0.0.1",5050);
        //createStreams();
        //updateCustomers();
        //updateVehicles();
        //updateRentals();
        //updateRentDates();
    }
    public void connectToServer() throws IOException
    {
        socket = new Socket("127.0.0.1",5050);
    }

    public void createStreams() throws IOException
    {
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();

            ois = new ObjectInputStream(socket.getInputStream());
    }

    public String receiveMassageFromServer() throws IOException, ClassNotFoundException
    {
        String receive = " ";

        receive = (String)ois.readObject();

        return receive;
    }

    public void fillCustomers(SortableTableModel tableModel) throws IOException, ClassNotFoundException
    {
        ArrayList<Customer> customers;

        oos.writeObject("RetrieveCustomersData>>>");
        oos.flush();

        customers = (ArrayList<Customer>)ois.readObject();

        tableModel.setRowCount(0);
        for (int i = 0; i < customers.size() ; i++)
        {
            tableModel.addRow(new Object[]{customers.get(i).getCustNumber(),customers.get(i).getName(),customers.get(i).getSurname(),customers.get(i).getPhoneNum(),customers.get(i).getCredit(),customers.get(i).canRent()});
        }


    }

  public void fillDvds(SortableTableModel tableModel) throws IOException, ClassNotFoundException
  {
      ArrayList<DVD> dvds;

      oos.writeObject("RetrieveDvdData>>>");
      oos.flush();

      dvds = (ArrayList<DVD>) ois.readObject();

      tableModel.setRowCount(0);
      for (int i = 0; i < dvds.size(); i++)
      {
          tableModel.addRow(new Object[]{dvds.get(i).getDvdNumber(),dvds.get(i).getTitle(),dvds.get(i).getCategory(),dvds.get(i).isNewRelease(),dvds.get(i).isAvailable(),dvds.get(i).getPrice()});
      }
      System.out.println(" movie table filled");
  }

  public void fillRentals(SortableTableModel tableModel) throws IOException, ClassNotFoundException
  {
      ArrayList<Rental> rentals;

      oos.writeObject("RetrieveRentalData>>>");
      oos.flush();

      rentals = (ArrayList<Rental>)ois.readObject();
      tableModel.setRowCount(0);
      for (int i = 0; i < rentals.size(); i++)
      {
          tableModel.addRow(new Object[]{rentals.get(i).getRentalNumber(),rentals.get(i).getDateRented(),rentals.get(i).getCustNumber(),rentals.get(i).getDvdNumber()});
      }
      System.out.println("printed.....");
  }



}
