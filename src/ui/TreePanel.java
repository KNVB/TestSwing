package ui;
import javax.swing.JTree;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;

public class TreePanel extends JPanel implements TreeSelectionListener 
{
	/**
	 * 
	 */
	private JTree tree;
	private JScrollPane detailView = new JScrollPane();
	private static final long serialVersionUID = -5487355530272852522L;

	public TreePanel()
	{
		super(new GridLayout(1,2));
		JScrollPane serverView = new JScrollPane(builtTree());
		
		//Add the scroll panes to a split pane.
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        serverView.setBackground(Color.white);
        splitPane.setResizeWeight(.3d);
        splitPane.setLeftComponent(serverView);
        splitPane.setRightComponent(detailView);
        splitPane.setPreferredSize(new Dimension(500, 300));
        //Add the split pane to this panel.
        add(splitPane);
	}
	private JTree builtTree()
	{
		
		//Create the nodes.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Servers");
        DefaultMutableTreeNode user1 =new DefaultMutableTreeNode("User1");
        DefaultMutableTreeNode user2 =new DefaultMutableTreeNode("User2");
        DefaultMutableTreeNode server1 =new DefaultMutableTreeNode("Server1");
        DefaultMutableTreeNode server2 =new DefaultMutableTreeNode("Server2");
        top.add(server1);
        server1.add(user1);
        top.add(server2);
        server2.add(user2);
        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        
        //Optionally play with line styles.  Possible values are
        //"Angled" (the default), "Horizontal", and "None".
        tree.putClientProperty("JTree.lineStyle", "Angled");
        
        //That allows one selection at a time.
        tree.getSelectionModel().setSelectionMode
                (TreeSelectionModel.SINGLE_TREE_SELECTION);
        
        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);
        
        //Collapse all child nodes
        //tree.collapseRow(0);
        return tree;
	}
	@Override
	public void valueChanged(TreeSelectionEvent e) 
	{
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                 tree.getLastSelectedPathComponent();
		if (node == null) return;

		Object nodeInfo = node.getUserObject();
		if (node.isLeaf()) 
		{
			/*JPanel jp = new JPanel( ) ;
		    jp.setLayout( new GridLayout( 20, 2 ) ) ;
				
		    for(int i = 0 ; i < 20 ; i++)
		       for( int j = 0 ; j < 2 ; j ++  )   
			  jp.add(new JButton("Button " + j));
 
		    int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED ;
		    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED ;*/
			
			JPanel jp = new JPanel( ) ;
			JLabel label = new JLabel("Find What:");;
		    JTextField textField = new JTextField();
	        
		    detailView.setViewportView(jp);
			
		}
	}

}
