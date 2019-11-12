<?php
	$day = date("Y/m/d H:i:s");
	$conn = mysqli_connect("localhost", "User Name", "Pass Word", "DB Name");
	$sql = "
		INSERT INTO notification_table(
		id,
		date,
		title,
		user,
		description
		) VALUES(
		0,
		'$day',
		'$_POST[title]',
		'$_POST[user]',
		'$_POST[description]')
	";
	$result = mysqli_query($conn,$sql);
	if($result==false){
		echo mysqli_error($conn);
	}
	Header("Location:index.php");
?>
