<?php
	include "koneksi.php";
	$nim	= $_POST['nim'];
	$nama 	= $_POST['nama'];
	$alamat	= $_POST['alamat'];
	$email	= $_POST['email'];
	
	class emp{}
	
	if (empty($nama)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysqli_query($connect,"INSERT INTO `data` (nim,nama,alamat,email) VALUES('".$nim."','".$nama."','".$alamat."','".$email."')");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di simpan";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error simpan Data";
			die(json_encode($response)); 
		}	
	}
?>