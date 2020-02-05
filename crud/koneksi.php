<?php
	
	$server		= "localhost"; // sesuaikan alamat server anda
	$user		= "root"; // sesuaikan user web server anda
	$password	= ""; // sesuaikan password web server anda
	$database	= "coba"; // sesuaikan database web server anda
	
	$connect = mysqli_connect($server, $user, $password) or die ("Koneksi gagal!");
	mysqli_select_db($connect,$database) or die ("Database belum siap!");
?>