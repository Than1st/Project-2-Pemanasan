<?php 
	include "koneksi.php";
	
	$query = mysqli_query($connect,"SELECT * FROM `data` ORDER BY nama ASC");
	
	$json = array();
	
	while($row = mysqli_fetch_assoc($query)){
		$json[] = $row;
	}
	
	echo json_encode($json);
	
	mysqli_close($connect);
	
?>