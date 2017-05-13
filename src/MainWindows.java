import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.FlowLayout;

import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.UIManager;
import javax.swing.JTextArea;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import net.miginfocom.swing.MigLayout;

import javax.swing.SwingConstants;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JScrollBar;
import javax.swing.AbstractListModel;
import javax.swing.JSlider;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class MainWindows extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTree tree;
	private DefaultMutableTreeNode root;
	private DefaultTreeModel defaultTreeModel;
	private JTextArea textArea;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindows frame = new MainWindows();
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
	public MainWindows() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u8BF7\u9009\u62E9\u6587\u4EF6\u5939\uFF1A");
		label.setBounds(22, 14, 92, 15);
		
		JLabel label_3 = new JLabel("");
		label_3.setBounds(236, 21, 0, 0);
		
		JLabel label_5 = new JLabel("");
		label_5.setBounds(241, 21, 0, 0);
		
		textField = new JTextField();
		textField.setBounds(107, 11, 572, 21);
		textField.setColumns(60);
	
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(679, 21, 0, 0);
		
		JLabel label_4 = new JLabel("");
		label_4.setBounds(684, 21, 0, 0);
		contentPane.setLayout(null);
		contentPane.add(label);
		contentPane.add(label_3);
		contentPane.add(label_5);
		contentPane.add(textField);
		
		JButton btnOpen = new JButton("Open");
		btnOpen.setBounds(682, 10, 67, 23);
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if(jfc.showDialog(new JLabel(), "选择")!=0)return;
				File file = jfc.getSelectedFile();
				if(file.isDirectory()){
					textField.setText(file.getAbsolutePath());
					refreshTreeFromDir(file.getAbsolutePath());
				}else{
					JOptionPane.showMessageDialog(null, "请选择一个文件夹");
				}
			}
		});
		contentPane.add(btnOpen);
		contentPane.add(label_2);
		contentPane.add(label_4);
		
		root = new DefaultMutableTreeNode("目录");
		defaultTreeModel=new DefaultTreeModel(root);
		tree = new JTree(defaultTreeModel);
		tree.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(node==null || !node.isLeaf()) return ;
				File file = new File(((FileNode)(node.getUserObject())).getFilePath());
				String contentText = "";
				textArea.setText(contentText);
				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf8"));
					while((contentText=br.readLine())!=null){
						textArea.append(contentText+"\n");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}finally{
					if(br!=null)
						try {
							br.close();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
				}
			}
		});
		tree.setVisibleRowCount(40);
		tree.setBounds(22, 38, 154, 408);
		tree.setToolTipText("");
		//contentPane.add(tree);
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
	    int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
	    JScrollPane jsp = new JScrollPane(tree,v,h);
	    jsp.setBounds(22, 38, 154, 408);
	    contentPane.add(jsp);
		
		JLabel label_1 = new JLabel("\u79FB\u52A8\u5230\uFF1A");
		label_1.setBounds(33, 469, 67, 15);
		contentPane.add(label_1);
		
		textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setLineWrap(true);
		textArea.setBounds(186, 39, 636, 407);
		textArea.setRows(20);
		textArea.setColumns(60);
		
		JScrollPane scrollPane = new JScrollPane(textArea, v, h);
		scrollPane.setBounds(186, 39, 636, 407);
		contentPane.add(scrollPane);
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignOnBaseline(true);
		panel.setBounds(97, 456, 725, 95);
		contentPane.add(panel);
		
		//contentPane.add(textArea);
	}
	
	public boolean refreshTreeFromDir(String dir){
		File file = new File(dir);
		if(file.isDirectory()){
			root.removeAllChildren();
			panel.removeAll();
			panel.repaint();
			for(String subDir: file.list()){
				File subFile = new File(dir+"\\"+subDir);
				if(!subFile.isDirectory()) continue;
				JButton mvBtn = new JButton(subDir);
				mvBtn.addActionListener(new ActionListener() {				
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
						if(node==null || !node.isLeaf()){
							JOptionPane.showMessageDialog(null, "请选择一个文件。");
							return ;
						}
						FileNode fileNode = (FileNode)(node.getUserObject());
						File file = new File(fileNode.getFilePath());
						if(!file.exists()){
							JOptionPane.showMessageDialog(null, "文件不存在："+fileNode.getFilePath());
							return;
						}
						//创建Marked目录
						String dstDir = fileNode.getRoot()+"Marked";
						File marked = new File(dstDir);
						if(! marked.exists()) marked.mkdirs();
						dstDir += "\\"+fileNode.getParent();
						File subMarked = new File(dstDir);
						if(!subMarked.exists()) subMarked.mkdirs();
						if(!file.renameTo(new File(dstDir+"\\"+fileNode.toString()))){
							JOptionPane.showMessageDialog(null, "移动文件失败：from "+fileNode.getFilePath()+" to "+dstDir+"\\"+fileNode.toString());
						}else{
							defaultTreeModel.removeNodeFromParent(node);
						}
					}
				});
				panel.add(mvBtn);
				
				//添加文件夹节点
				DefaultMutableTreeNode node = new DefaultMutableTreeNode(subFile.getName());
				for(String ssDir:subFile.list()){
					//添加文件节点
					node.add(new DefaultMutableTreeNode(new FileNode(dir, subDir, ssDir)));
				}
				root.add(node);
			}
			panel.revalidate();
			defaultTreeModel.reload();
		}else{
			JOptionPane.showMessageDialog(null, "路径非法，请重新选择。");
			return false;
		}
		return true;
	}
}
