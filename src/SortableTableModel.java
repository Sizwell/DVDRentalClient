import javax.swing.table.DefaultTableModel;

public class SortableTableModel extends DefaultTableModel
{
    public SortableTableModel()
    {
        super();
    }

    @Override
    public boolean isCellEditable(int row, int column)
    {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        if(getColumnCount()<1 &&    getRowCount()<1)
        {
            return Object.class;
        }
        else
        {
            return getValueAt(0,columnIndex).getClass();
        }

    }
}