<?php
	$conn =  mysqli_connect("localhost", "User Name", "Pass Word", "DB Name");
	$id = $_GET[id];
	$sql = "SELECT * from notification_table WHERE id=$id";
	$rs = mysqli_query($conn,$sql);
	$info = mysqli_fetch_array($rs);
	header('Content-Type:application/json');
	$notification_data = array(
		'id'=>$info['id'],
		'date'=>$info['date'],
		'title'=>$info['title'],
		'user'=>$info['user'],
		'description'=>$info['description']);
	echo json_encode($notification_data);
	mysqli_close($conn);
?>
