package gado;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class User extends JFrame implements ActionListener{

	private JPanel panel_user = new JPanel();
	
	private JButton button_tambah = new JButton("Tambah");
	private JButton button_ubah = new JButton("Ubah");
	private JButton button_hapus = new JButton("Hapus");
		
	private Dimension x = Toolkit.getDefaultToolkit().getScreenSize();
	
	//Variabel
	Object objek_isitabel;
	
	//table
	String[] list_user = {"Username","Nama","Alamat"};
	DefaultTableModel model_tabeluser;
	JTable tabel_user = new JTable();
	JScrollPane scroll_register = new JScrollPane();
	//--akhir table
	
	public User(){
		//konfigurasi awal
		setSize(500,400);
		setLocation(x.width/2-getWidth()/2,x.height/2-getHeight()/2);
		setTitle("User");
		setVisible(true);
		panel_user.setLayout(null);
		getContentPane().add(panel_user);
		button_ubah.setEnabled(false);
		button_hapus.setEnabled(false);
		//--akhir konfigurasi awal
		
		//pengaturan posisi
		button_tambah.setBounds(355,2,140,30);
		button_ubah.setBounds(355,35,140,30);
		button_hapus.setBounds(355,68,140,30);
		//--akhir pengaturan posisi
		
		
		//isi dari panel
		panel_user.add(button_tambah);
		panel_user.add(button_ubah);
		panel_user.add(button_hapus);
		//--akhir isi dari panel
		
		//table
		model_tabeluser = new DefaultTableModel(null,list_user);
		tabel_user.setModel(model_tabeluser);
		scroll_register.getViewport().add(tabel_user);
		tabel_user.setEnabled(true);
		scroll_register.setBounds (0,0,350,350);
		panel_user.add(scroll_register);
		//--akhir table
		
		//tambah respon
		button_tambah.addActionListener(this);
		button_ubah.addActionListener(this);
//		tabel_user.getModel().addTableModelListener(this);
		//--akhir tambah respon
		isi_tabel();
			
		//respon terhadap mouse
		tabel_user.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(SwingUtilities.isLeftMouseButton(e)){
					//aksi terhadap klik kiri
					button_ubah.setEnabled(true);
					button_hapus.setEnabled(true);	
					Point p = e.getPoint();
					int baris = tabel_user.rowAtPoint(p);
					int colom = 0;
					ListSelectionModel model = tabel_user.getSelectionModel();
					objek_isitabel = tabel_user.getModel().getValueAt(baris,colom);
					model.setSelectionInterval(baris,baris);
				}else if(SwingUtilities.isRightMouseButton(e)){
					//aksi terhadap klik kanan
				}
			}
		});
		//--akhir respon terhadap mouse
	}
	
	//aksi terhadap respon
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==button_tambah){
			new User_Tambah();
		}if(e.getSource()==button_ubah){
			User_edit();
		}
	}
	public void focusGained(FocusEvent e){
	}
	//--akhir aksi terhadap respon
	
	//fungsi
	void isi_tabel(){
		try{
			hapus_tabel();
			Koneksi koneksi = new Koneksi();
			Connection connection = koneksi.Buka();
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM User ORDER BY IdUser";
			ResultSet resultset = statement.executeQuery(sql);
			while(resultset.next()){
				String UserName_ = resultset.getString("UserName");
				String NamaLengkap_ = resultset.getString("NamaLengkap");
				String Alamat = resultset.getString("Alamat");
				String[] List_IsiTable = {UserName_,NamaLengkap_,Alamat};
				model_tabeluser.addRow(List_IsiTable);
			}
			resultset.close();
			connection.close();
		}catch(Exception e){
			
		}
	}
	
	void hapus_tabel(){
		int baris_tabel = model_tabeluser.getRowCount();
		for(int i=0;i<baris_tabel;i++){
			model_tabeluser.removeRow(0);
		}
	}
	
	void User_edit(){
		System.out.println(objek_isitabel);
	}
	
	void hapus_user(){
		try{
			String var="";
			Koneksi koneksi_objek = new Koneksi();
			Connection connection_objek = koneksi_objek.Buka();
			Statement statement_objek = connection_objek.createStatement();
			String sql = "Delete From User WHERE IdUser='"+var+"'";
		}catch(Exception e){
			
		}
	}
	//--akhir fungsi
	
	public static void main(String[] args){
		new User();
	}
}