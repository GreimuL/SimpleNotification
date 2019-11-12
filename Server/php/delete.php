<?php
	$conn = mysqli_connect("localhost", "User Name", "Pass Word", "DB Name");
	$delete_id = $_POST['delete_id'];
	$sql = "DELETE from notification_table WHERE id = '$delete_id';";
	$result = mysqli_query($conn,$sql);
	if($result==false){
		echo mysqli_error($conn);
	}
	Header("Location:index.php");
?>
