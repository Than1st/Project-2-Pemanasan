<?php
	include "koneksi.php";
	
	$nim 	= $_POST['nim'];
	$nama 	= $_POST['nama'];
	$alamat	= $_POST['alamat'];
	$email	= $_POST['email'];
	
	class emp{}
	
	if (empty($nim) || empty($nama)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Kolom isian tidak boleh kosong"; 
		die(json_encode($response));
	} else {
		$query = mysqli_query($connect,"UPDATE `data` SET nama='".$nama."', alamat='".$alamat."', email='".$email."' WHERE nim='".$nim."'");
		
		if ($query) {
			$response = new emp();
			$response->success = 1;
			$response->message = "Data berhasil di update";
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error update Data";
			die(json_encode($response)); 
		}	
	}
?>