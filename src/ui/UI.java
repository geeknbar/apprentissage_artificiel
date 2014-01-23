package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.JTree;

public class UI extends JFrame {

	private JPanel contentPane;
	private JTree tree;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UI frame = new UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Prevision");
        //create the child nodes
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("Humidite");
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("Oui");
        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("Vent");
        
        node1.add(new DefaultMutableTreeNode("Non"));
        node1.add(new DefaultMutableTreeNode("Oui"));
        
        node3.add(new DefaultMutableTreeNode("Non"));
        node3.add(new DefaultMutableTreeNode("Oui"));
        
        //add the child nodes to the root node
        root.add(node1);
        root.add(node2);
        root.add(node3);
         
        //create the tree by passing in the root node
        tree = new JTree(root);
//        expandAllNodes(tree, 0, tree.getRowCount());
		contentPane.add(tree, BorderLayout.CENTER);
	}

//	private void expandAllNodes(JTree tree, int startingIndex, int rowCount){
//	    for(int i=startingIndex;i<rowCount;++i){
//	        tree.expandRow(i);
//	    }
//
//	    if(tree.getRowCount()!=rowCount){
//	        expandAllNodes(rowCount,0, tree.getRowCount());
//	    }
//	}
}
