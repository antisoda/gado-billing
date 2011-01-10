package gado;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import java.util.Date;
import java.text.SimpleDateFormat;

public class User_Tambah extends JFrame implements ActionListener, FocusListener{
	
	private	JPanel panel_useradd = new JPanel();
	
	private JLabel label_id = new JLabel("User ID");
	private JLabel label_uname = new JLabel("Username");
	private JLabel label_namalengkap = new JLabel("Nama Lengkap");
	private JLabel label_jenkel = new JLabel("Jenis Kelamin");
	private JLabel label_alamat = new JLabel("Alamat");
	private JLabel label_tlp = new JLabel("No Telepon");
	private JLabel label_tgldaftar = new JLabel("Tanggal Daftar");
	
	private JTextField txtf_id = new JTextField();
	private JTextField txtf_uname = new JTextField();
	private JTextField txtf_namalengkap = new JTextField();
	private JTextArea txtf_alamat = new JTextArea();
	private JTextField txtf_tlp = new JTextField();
	private JTextField txtf_tgldaftar = new JTextField();
	
	private JRadioButton radiol = new JRadioButton("Laki-laki");
	private JRadioButton radiop = new JRadioButton("Perempuan");
	private ButtonGroup user_radio = new ButtonGroup();
		
	private JButton button_tambah = new JButton("Tambah");
	private JButton button_bersih = new JButton("Bersih");
	
	private Dimension x = Toolkit.getDefaultToolkit().getScreenSize();
	
	public User_Tambah(){
		//konfigurasi awal
		setSize(400,410);
		setTitle("Tambah User");
		setVisible(true);
		setLocation(x.width/2-getWidth()/2,x.height/2-getHeight()/2);
		panel_useradd.setLayout(null);
		getContentPane().add(panel_useradd);
		FungsiAwal();
		//--akhir konfigurasi awal
		
		//atur lokasi
		label_id.setBounds(20, 25, 110, 25);
		label_uname.setBounds(20, 60, 110, 25);
		label_namalengkap.setBounds(20, 95, 110, 25);
		label_jenkel.setBounds(20, 130, 110, 25);
		label_alamat.setBounds(20, 165, 110, 25);
		label_tlp.setBounds(20, 235, 110, 25);
		label_tgldaftar.setBounds(20, 270, 110, 25);
		
		txtf_id.setBounds(130, 25, 60, 25);
		txtf_uname.setBounds(130, 60, 120, 25);
		txtf_namalengkap.setBounds(130, 95, 170, 25);
		radiol.setBounds(130, 130, 100, 25);
		radiop.setBounds(225, 130, 100, 25);
		txtf_alamat.setBounds(130, 165, 250, 60);
		txtf_tlp.setBounds(130, 235, 125, 25);
		txtf_tgldaftar.setBounds(130, 270, 120, 25);
		
		button_tambah.setBounds(25, 320, 160, 35);
		button_bersih.setBounds(210, 320, 160, 35);
		//--akhir atur lokasi
		
		//tambah komponen
		panel_useradd.add(label_id);
		panel_useradd.add(label_uname);
		panel_useradd.add(label_namalengkap);
		panel_useradd.add(label_jenkel);
		panel_useradd.add(label_alamat);
		panel_useradd.add(label_tlp);
		panel_useradd.add(label_tgldaftar);
		
		panel_useradd.add(txtf_id);
		panel_useradd.add(txtf_uname);
		panel_useradd.add(txtf_namalengkap);
		panel_useradd.add(radiol);
		panel_useradd.add(radiop);
		panel_useradd.add(txtf_alamat);
		panel_useradd.add(txtf_tlp);
		panel_useradd.add(txtf_tgldaftar);
		
		user_radio.add(radiol);
		user_radio.add(radiop);
		
		panel_useradd.add(button_tambah);
		panel_useradd.add(button_bersih);
		
		//--akhir tambah komponen
		
		//tambah respon
		button_bersih.addActionListener(this);
		button_tambah.addActionListener(this);
		//--akhir tambah respon
	}
	
	//aksi terhadap respon
	public void actionPerformed(ActionEvent e){
		if(e.getSource()==button_bersih){
			FungsiAwal();
		}if(e.getSource()==button_tambah){
			Cek_Username();
		}
	}
	public void focusGained(FocusEvent e){
	}
	public void focusLost(FocusEvent e){
		if(e.getSource()==txtf_id){
			if(txtf_id.getText().length()!=0){
				//cari user
			}
		}
	}
	//--akhir aksi terhadap respon
	
	//fungsi
	void FungsiAwal(){
		txtf_id.setEnabled(false);
		txtf_uname.setText("");
		txtf_namalengkap.setText("");
		txtf_alamat.setText("");
		txtf_tlp.setText("");
		txtf_tgldaftar.setText("");
		user_radio.clearSelection();
		Cari_Record();
		ambil_TanggalSaatIni();
	}
	
	void Cari_Record(){
		try{
			Koneksi koneksi_objek = new Koneksi();
			Connection connection_objek = koneksi_objek.Buka();
			Statement statement_objek = connection_objek.createStatement();
			String sql = "SELECT * FROM User ORDER BY IdUser";
			ResultSet resultset_objek = statement_objek.executeQuery(sql);
			String var_hasil="";
			while (resultset_objek.next()){
				var_hasil = resultset_objek.getString("IdUser");
			}
			Kode_User(var_hasil);
			resultset_objek.close();
			connection_objek.close();
		}catch(Exception e){
		}
	}
	
	void Kode_User(String nomor){
		try{
			int angka= Integer.parseInt(nomor)+1;
			nomor="000"+angka;
			if(nomor.length()>=7){
				nomor=nomor.substring(3,7);
			}if(nomor.length()>=6){
				nomor=nomor.substring(2,6);
			}if(nomor.length()>=5){
				nomor=nomor.substring(1,5);
			}
			txtf_id.setText(nomor);
		}catch(Exception e){
		}
	}
	
	void ambil_TanggalSaatIni(){
		Date sekarang = new Date();
		SimpleDateFormat format_objek = new SimpleDateFormat("dd/MM/yyyy");
		txtf_tgldaftar.setText(format_objek.format(sekarang));
	}
	
	void Cek_Username(){
		String var1 = txtf_uname.getText();
		if(var1.length()==0){
			JOptionPane.showMessageDialog(this,"Username Harus Diisi");
		}else{
			Tambah_User();
		}
	}
	
	void Tambah_User(){
		try{
			//format jenis kelamin dan tanggal
			String jenis_kelamin = "";
			if(radiol.isSelected()){
				jenis_kelamin = "L";
			}else{
				jenis_kelamin = "P";
			}
			String belumFormat = txtf_tgldaftar.getText();
			String tgl = belumFormat.substring(0,2);
			String bln = belumFormat.substring(3,5);
			String thn = belumFormat.substring(6,10);
			String sudahFormat = thn+"/"+bln+"/"+tgl;
			
			//eksekusi terhadap basis data
			Koneksi koneksi_objek = new Koneksi();
			Connection connection_objek = koneksi_objek.Buka();
			Statement statement_objek = connection_objek.createStatement();
			String sql = "INSERT INTO User VALUES('"+txtf_id.getText()+"','"+txtf_uname.getText()+"','"+txtf_namalengkap.getText()+"','"+jenis_kelamin+"','"+txtf_alamat.getText()+"','"+txtf_tlp.getText()+"','"+sudahFormat+"','"+txtf_uname.getText()+"')";
			//identifikasi kesalahan dan eksekusi skrip sql
			int kesalahan = statement_objek.executeUpdate(sql);
			if(kesalahan==1){
				FungsiAwal();
				setVisible(false);
				JOptionPane.showMessageDialog(this,"User Telah Ditambah");
			}else{
				JOptionPane.showMessageDialog(this,"Proses Penambahan User Tidak Berhasil\nCek Kembali Pengaturan Basis Data Anda");
			}
			statement_objek.close();
			connection_objek.close();
		}catch(Exception e){
		}
	}
	//--akhir fungsi
	
	public static void main(String[] args){
		new User_Tambah();
	}

}