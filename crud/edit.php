<?php
	include "koneksi.php";
	
	$nim 	= $_POST['nim'];
	
	class emp{}
	
	if (empty($nim)) { 
		$response = new emp();
		$response->success = 0;
		$response->message = "Error Mengambil Data"; 
		die(json_encode($response));
	} else {
		$query 	= mysqli_query($connect,"SELECT * FROM data WHERE nim='$nim'");
		$row 	= mysqli_fetch_array($query);
		
		if (!empty($row)) {
			$response = new emp();
			$response->success = 1;
			$response->nim = $row["nim"];
			$response->nama = $row["nama"];
			$response->alamat = $row["alamat"];
			$response->email = $row["email"];
			die(json_encode($response));
		} else{ 
			$response = new emp();
			$response->success = 0;
			$response->message = "Error Mengambil Data";
			die(json_encode($response)); 
		}	
	}
?>