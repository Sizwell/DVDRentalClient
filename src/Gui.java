import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Gui extends JFrame implements ActionListener
{
    String[] categories = {"All","Horror","Sci-fi","Drama","Romance","Comedy","Action","Cartoon"};

    String[] canRentAr = {"Yes", "No"};
    String[] canRentArray = {"Yes", "No"};

    //Customer[] custArray = new Customer[10];
    ArrayList<Customer> custArray = new ArrayList<>();
    ArrayList<DVD> dvdArray = new ArrayList<>();
    ArrayList<Rental> rentedArray = new ArrayList<>();
    // Dvd[] dvdArray = new Dvd[10];

    CardLayout cardLayout = new CardLayout();
    JPanel mainPanel = new JPanel(cardLayout); // this sets the main Panel's layout to cards, no need to set it after this...
    JMenu add = new JMenu("Add");
    JMenu delete = new JMenu("Delete");
    JMenu update = new JMenu("Update");
    JMenu out = new JMenu("Screen");
    JMenuItem mnCustomer, mnDvD, mnRent,mnExit, mnOut, mnAddCustomer, mnAddDvd, mnAddExit, mnUpdate;
    JMenuBar mBar = new JMenuBar();

    JPanel panelHire = new JPanel();
    JPanel panelLog = new JPanel();
    JPanel panelRented = new JPanel();
    //Hire screen
    JPanel panelCustomers = new JPanel();
    JPanel panelMovies = new JPanel();
    JPanel panelOptions = new JPanel();

    JButton btnAdd = new JButton("+");
    JButton btnDelete = new JButton("-");
    JButton btnHire = new JButton("Hire");
    JButton btnReturn = new JButton("Return");
    JButton btnReturnDvd = new JButton("Return");
    JButton btnLogin = new JButton("Login");
    JTextField textUser = new JTextField(20);
    JTextField textPassword = new JTextField(20);

    JTextField textSearch = new JTextField();
    final JTextField textSearchDvd = new JTextField();
    JComboBox<String> comboBoxCategories = new JComboBox<>(categories);
    JTextField textSearchs = new JTextField();
    JComboBox<String> comboBoxCanRent = new JComboBox<>(canRentAr);

    Customer cust = new Customer();

    public SortableTableModel tableModelCustomers = new SortableTableModel();
    private TableRowSorter<SortableTableModel> tableRowSorterCust = new TableRowSorter<>(tableModelCustomers);
    JTable tableCust = new JTable(tableModelCustomers);
    JScrollPane scrollPaneCustomers = new JScrollPane(tableCust);

    public SortableTableModel tableModelMovies = new SortableTableModel();
    private TableRowSorter<SortableTableModel> tableRowSorterMovie = new TableRowSorter<>(tableModelMovies);
    JTable tableDvd = new JTable(tableModelMovies);
    JScrollPane scrollPaneMovies = new JScrollPane(tableDvd);

    public SortableTableModel tableModelRented = new SortableTableModel();
    private TableRowSorter<SortableTableModel> tableRowSorterRented = new TableRowSorter<>(tableModelRented);
    JTable tableRented = new JTable(tableModelRented);
    JScrollPane scrollPaneRented = new JScrollPane(tableRented);

    public Gui()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(1000,500));
        this.setResizable(false);
        this.panelMenu();
        this.addTitle();

        this.setHirePanel();
        this.setRentalPanel();
        this.addFooter();


        this.addMainPanel();
        this.display();
    }

    public void display()
    {
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void addTitle()
    {
        JLabel labelTitle = new JLabel("Movie Store");
        labelTitle.setPreferredSize(new Dimension(10,100));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 60));
        this.add(labelTitle, BorderLayout.NORTH);
    }

    public void panelMenu()
    {
        mnAddCustomer = new JMenuItem("Add Customer");
        mnAddCustomer.addActionListener(this);
        add.add(mnAddCustomer);

        mnAddDvd = new JMenuItem("Add DvD");
        mnAddDvd.addActionListener(this);
        add.add(mnAddDvd);


        mnAddExit = new JMenuItem("Exit");
        mnAddExit.addActionListener(this);
        add.addSeparator();
        add.add(mnAddExit);

        mnCustomer = new JMenuItem("Customer");
        mnCustomer.addActionListener(this);
        delete.add(mnCustomer);

        mnDvD = new JMenuItem("DvD");
        mnDvD.addActionListener(this);
        delete.add(mnDvD);

        mnRent = new JMenuItem("Main");
        mnRent.addActionListener(this);
        out.add(mnRent);

        mnOut = new JMenuItem("Rented");
        mnOut.addActionListener(this);
        out.add(mnOut);



        mnExit = new JMenuItem("Customer");
        mnExit.addActionListener(this);
        update.add(mnExit);

        mnUpdate = new JMenuItem("DvD");
        mnUpdate.addActionListener(this);
        update.add(mnUpdate);


        add.addActionListener(this);
        delete.addActionListener(this);
        update.addActionListener(this);
        mBar.add(add);
        mBar.add(delete);
        mBar.add(out);
        mBar.add(update);
        setJMenuBar(mBar);
    }
    private void addMainPanel() // add all the panels you want switch in here ... the hire panel is added so long
    {
        mainPanel.add(panelLog,"Login Screen");
        mainPanel.add(panelHire, "Hire Screen");
        mainPanel.add(panelRented, "Rented Screen");
        // add more panels to main panel between here

        this.switchPanelTo("Hire Screen"); // this screen will be displayed when gui is run
        this.add(mainPanel, BorderLayout.CENTER);
    }
    private void setHirePanel() // add the hire panel's components in here
    {
        panelHire.setLayout(new GridBagLayout());
        panelHire.setBorder(BorderFactory.createTitledBorder("Hire"));

        panelHire.add(panelCustomers, new GridBagConstraints(1,1,1,1,1,1, GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));
        panelHire.add(panelOptions, new GridBagConstraints(2,1,1,1,0,1, GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));
        panelHire.add(panelMovies, new GridBagConstraints(3,1,1,1,1,1, GridBagConstraints.CENTER,GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

        //-------------------Customers Panel-------------
        panelCustomers.setLayout(new BorderLayout());
        panelCustomers.setBorder(BorderFactory.createTitledBorder("Customers"));

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());
        textSearch.setPreferredSize(new Dimension(80,20));

        searchPanel.add(btnAdd);
        btnAdd.addActionListener(this);
        searchPanel.add(new Label("Search: "));
        searchPanel.add(textSearch);
        textSearch.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                search(textSearch.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                search(textSearch.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                search(textSearch.getText());
            }
            public void search(String s)
            {
                if (s.length()==0)
                {
                    tableRowSorterCust.setRowFilter(RowFilter.regexFilter(""));
                }
                else
                {
                    tableRowSorterCust.setRowFilter(RowFilter.regexFilter("(?i)^"+s,0));
                }
            }
        });
        //searchPanel.add(new Label("Can Rent: "));
        //searchPanel.add(comboBoxCanRent);
        searchPanel.add(btnDelete);
        btnDelete.addActionListener(this);

        panelCustomers.add(searchPanel, BorderLayout.NORTH);
        tableCust.setPreferredSize(new Dimension(150,200));

        tableModelCustomers.addColumn("Cust No");
        tableModelCustomers.addColumn("Name");
        tableModelCustomers.addColumn("Surname");
        tableModelCustomers.addColumn("Phone Number");
        tableModelCustomers.addColumn("Credit");
        tableModelCustomers.addColumn("Can Rent");
        tableCust.setRowSorter(tableRowSorterCust);
        //tableCust.isCellEditable();

        scrollPaneCustomers.setPreferredSize(new Dimension(400,0));
        panelCustomers.add(scrollPaneCustomers, BorderLayout.CENTER);

        //------Options Panel-------
        panelOptions.setBorder(BorderFactory.createTitledBorder("Options"));
        panelOptions.setPreferredSize(new Dimension(150,0));

        JPanel btnGroup = new JPanel();

        btnGroup.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnGroup.add(btnHire);
        btnGroup.add(btnReturn);

        btnHire.addActionListener(this);
        btnReturn.addActionListener(this);
        panelOptions.add(btnGroup);


        //---------------------Movies Panel----------------
        panelMovies.setLayout(new BorderLayout());
        panelMovies.setBorder(BorderFactory.createTitledBorder("Movies"));

        JPanel searchPanel1 = new JPanel();
        searchPanel1.setLayout(new FlowLayout());
        textSearchDvd.setPreferredSize(new Dimension(80,20));
        searchPanel1.add(new Label("Search: "));

        searchPanel1.add(textSearchDvd);

        textSearchDvd.getDocument().addDocumentListener(new DocumentListener()
        {
            @Override
            public void insertUpdate(DocumentEvent e)
            {
                search(textSearchDvd.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e)
            {
                search(textSearchDvd.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e)
            {
                search(textSearchDvd.getText());
            }

            public void search(String s)
            {
                if (s.length()==0)
                {
                    tableRowSorterMovie.setRowFilter(null);
                }
                else
                {
                    tableRowSorterMovie.setRowFilter(RowFilter.regexFilter("(?i)^"+s, 0));
                }
            }
        });



        searchPanel1.add(new Label("Search Category: "));
        searchPanel1.add(comboBoxCategories);
        comboBoxCategories.addActionListener(this);
        panelMovies.add(searchPanel1, BorderLayout.NORTH);

        tableDvd.setPreferredSize(new Dimension(150,200));
        tableModelMovies.addColumn("Dvd Number");
        tableModelMovies.addColumn("Title");
        tableModelMovies.addColumn("Category");
        tableModelMovies.addColumn("Available");
        tableModelMovies.addColumn("New Release");
        tableModelMovies.addColumn("Price");

        tableDvd.setRowSorter(tableRowSorterMovie);

        scrollPaneMovies.setPreferredSize(new Dimension(400,0));
        panelMovies.add(scrollPaneMovies, BorderLayout.CENTER);

    }
    public void setRentalPanel()
    {
        panelRented.setLayout(new BorderLayout());
        panelRented.setBorder(BorderFactory.createTitledBorder("Rented Movies"));
        //panelRented.add(new JLabel("Rented Movies"), BorderLayout.NORTH);

        tableRented.setPreferredSize(new Dimension(400,350));

        tableModelRented.addColumn("Rental Number");
        tableModelRented.addColumn("Date Rented");
        tableModelRented.addColumn("Customer Number");
        tableModelRented.addColumn("DVD Number");
        tableRented.setRowSorter(tableRowSorterRented);

        scrollPaneRented.setPreferredSize(new Dimension(400,350));
        panelRented.add(scrollPaneRented, BorderLayout.CENTER);

        JPanel btnDvdpanel = new JPanel();
        btnDvdpanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        btnDvdpanel.add(btnReturnDvd);
        btnReturnDvd.addActionListener(this);
        panelRented.add(btnDvdpanel, BorderLayout.SOUTH);

    }
    private void addFooter()
    {
        /*Date date = new Date();
        JLabel labelTitle = new JLabel(date.toString());
        labelTitle.setPreferredSize(new Dimension(0,70));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setFont(new Font("Dialog", Font.ROMAN_BASELINE, 20));
        this.add(labelTitle, BorderLayout.SOUTH);*/

        SimpleDateFormat timer = new SimpleDateFormat("HH:mm \n\n EEE,d MMM, yyyy");
        JLabel labelTitle = new JLabel();
        labelTitle.setPreferredSize(new Dimension(0,70));
        labelTitle.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitle.setFont(new Font("Dialog", Font.BOLD, 30));
        this.add(labelTitle, BorderLayout.SOUTH);
        int i = 100;
        new Timer(i, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Calendar now = Calendar.getInstance();
                labelTitle.setText(timer.format(now.getTime()));
            }
        }).start();
    }

    public void switchPanelTo(String screenConstraint) // this method you can switch through the screens
    {
        cardLayout.show(mainPanel, screenConstraint);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource()==mnAddCustomer)
        {
            //addCustomer();
            System.out.println(custArray);
        }
        else if(e.getSource()==btnAdd)
        {
           // addCustomer();
        }
        else if(e.getSource()==mnAddDvd)
        {
           // addMovie();
        }
        else if (e.getSource()==mnOut)
        {
            switchPanelTo("Rented Screen");
        }
        else if (e.getSource()==btnReturn)
        {
            switchPanelTo("Rented Screen");
        }
        else if (e.getSource()==mnRent)
        {
            switchPanelTo("Hire Screen");

            //refresh table

        }
        else if(e.getSource()==mnExit)
        {
            System.exit(0);
        }
        else if(e.getSource()==mnAddExit)
        {
            System.exit(0);
        }
    }

    public static void main(String[] args)
    {
        new Gui();
    }
}
