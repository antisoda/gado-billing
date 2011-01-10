/*=======================================
gado-billing oleh reza zulfarhan
eclipse helios
sunjdk update 22
=========================================
*/
package gado;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Server extends JFrame implements ActionListener{	
	private JPanel master_panel = new JPanel();
	
	private JMenuBar menubar = new JMenuBar();
	private JToolBar toolbar = new JToolBar();
	
	private JMenu menu_berkas = new JMenu("Berkas");
	private JMenu menu_sunting = new JMenu("Sunting");
	private JMenu menu_bantuan	= new JMenu("Bantuan");
	private JMenu menu_baru = new JMenu("Baru");
	
	private JMenuItem item_userbaru = new JMenuItem("User");
	private JMenuItem item_paketbaru = new JMenuItem("Paket");
	private JMenuItem item_properti = new JMenuItem("Properti");
	private JMenuItem item_keluar = new JMenuItem("Keluar");
	private JMenuItem item_suntinguser = new JMenuItem("User");
	private JMenuItem item_suntingpaket = new JMenuItem("Paket");
	private JMenuItem item_tentang = new JMenuItem("Tentang Gado-Billing");
	
	private JButton button_user = new JButton(new ImageIcon("img/user.png"));
	private JButton button_package = new JButton(new ImageIcon("img/package.png"));
	private JButton button_print = new JButton(new ImageIcon("img/print.png"));
	
	private Dimension x = Toolkit.getDefaultToolkit().getScreenSize();
	
	//table
	String[] list_connect = {"No","Username","Running Time","Package"};
	DefaultTableModel table_server;
	JTable table_connect = new JTable();
	JScrollPane scroll_connect = new JScrollPane();
	//--akhir table

	public Server(){
		
		//kofigurasi awal
		setSize(800,600);
		setLocation(x.width/2-getWidth()/2,x.height/2-getHeight()/2);
		setTitle("Devel - Gado-Billing");
		setVisible(true);
		setJMenuBar(menubar);
		getContentPane().add(master_panel);
		getContentPane().add(toolbar, BorderLayout.NORTH);
		master_panel.setLayout(null);
		//--akhir konfigurasi
			
		//isi panel

		toolbar.add(button_user);
		toolbar.add(button_package);
		toolbar.add(button_print);
		
		menubar.add(menu_berkas);
		menubar.add(menu_sunting);
		menubar.add(menu_bantuan);

		menu_berkas.add(menu_baru);
			menu_baru.add(item_userbaru);
			menu_baru.add(item_paketbaru);
		menu_berkas.addSeparator();
		menu_berkas.add(item_keluar);
		
		menu_sunting.add(item_suntinguser);
		menu_sunting.add(item_suntingpaket);
		menu_sunting.addSeparator();
		menu_sunting.add(item_properti);
		
		menu_bantuan.add(item_tentang);
		
		//set table
		table_server = new DefaultTableModel(null,list_connect);
		table_connect.setModel(table_server);
		scroll_connect.getViewport().add(table_connect);
		table_connect.setEnabled(true);
		scroll_connect.setBounds (0,0,600,450);
		master_panel.add(scroll_connect);
		//--akhir table
		
		//--akhir isi panel
		
		//penambahan respon
		item_keluar.addActionListener(this);
		item_userbaru.addActionListener(this);
		button_user.addActionListener(this);
		//--akhir penambahan respon
		
		
		/*
		//systemtray
		ImageIcon icon = new ImageIcon("gadop.png");
		Image image = icon.getImage();
		TrayIcon tray = new TrayIcon(image);
		SystemTray systray = SystemTray.getSystemTray();
		try{
			systray.add(tray);
		}catch (Exception e) {
			e.printStackTrace();
		}*/
	}
    
	public void actionPerformed(ActionEvent event){
		if(event.getSource()==item_keluar){
			System.exit(0);
		}
		if(event.getSource()==item_userbaru){
			User_Tambah form_add = new User_Tambah();
		}
		if(event.getSource()==button_user){
			User form_user = new User();
		}
	}
		
	public static void main(String[] args){
		//set look and feel GTK pada aplikasi
		try{
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//--akhir set look and feel GTK pada aplikasi
		
		new Server();
	}
}
