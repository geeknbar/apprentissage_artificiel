package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.Component;

import javax.swing.Box;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import apprentissage_artificiel.Instances;
import apprentissage_artificiel.ID3;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

public class Hmi extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextArea taOutput;
	private JLabel lblFileName_, lblRelationName_;
	private final JFileChooser fc = new JFileChooser(new File("./bin/doc/"));
	private Instances instances = new Instances();
	private ID3 id3 = new ID3();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Hmi frame = new Hmi();
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
	public Hmi() {
		setTitle("ID3 algorithm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panelNorth = new JPanel();
		panelNorth.setBorder(new TitledBorder(null, "File",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		FlowLayout flowLayout = (FlowLayout) panelNorth.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panelNorth, BorderLayout.NORTH);

		JButton btnFile = new JButton("File");
		btnFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileNameExtensionFilter filter = new FileNameExtensionFilter(".arff files", "arff");
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(getParent());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					instances.loadFile(fc.getSelectedFile().getAbsolutePath());
					lblFileName_.setText(fc.getSelectedFile().getName());
					lblRelationName_.setText(instances.getRelationName());
				}
			}
		});
		panelNorth.add(btnFile);

		Component horizontalStrut = Box.createHorizontalStrut(20);
		panelNorth.add(horizontalStrut);

		JLabel lblFileName = new JLabel("File name : ");
		lblFileName.setFont(new Font("Dialog", Font.BOLD, 12));
		panelNorth.add(lblFileName);

		lblFileName_ = new JLabel("");
		lblFileName_.setFont(new Font("Dialog", Font.ITALIC, 12));
		panelNorth.add(lblFileName_);

		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		panelNorth.add(horizontalStrut_1);

		JLabel lblRelationName = new JLabel("Relation name : ");
		panelNorth.add(lblRelationName);

		lblRelationName_ = new JLabel("");
		lblRelationName_.setFont(new Font("Dialog", Font.ITALIC, 12));
		panelNorth.add(lblRelationName_);

		JPanel panelWest = new JPanel();
		panelWest.setBorder(new TitledBorder(null, "Properties",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelWest, BorderLayout.WEST);
		panelWest.setLayout(new BorderLayout(0, 0));

		JButton btnCompute = new JButton("Compute");
		btnCompute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ID3 id3Return = id3.compute(instances);
				taOutput.setText("Relation:\t" + instances.getRelationName() + "\n");
				taOutput.append("Instances:\t" + instances.getInstances().size() + "\n");
				taOutput.append("Attributes:\t" + instances.getAttributes().size() + "\n");
				for (Entry<String, ArrayList<String>> entry : instances.getAttributes().entrySet()) {
					taOutput.append("\t" + entry.getKey() + "\n");
				}
				taOutput.append("\n\n Id3 Decision Tree\n\n");
				taOutput.append(id3Return.display(0));
			}
		});
		panelWest.add(btnCompute, BorderLayout.SOUTH);

		JPanel panelCenter = new JPanel();
		panelCenter.setBorder(new TitledBorder(null, "Output",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(panelCenter, BorderLayout.CENTER);
		panelCenter.setLayout(new BorderLayout(0, 0));

		taOutput = new JTextArea();
		taOutput.setMargin(new Insets(10,10,10,10));
		JScrollPane scroll = new JScrollPane(taOutput);
		panelCenter.add(scroll);

	}

}
