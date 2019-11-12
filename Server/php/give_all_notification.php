<?php
	$conn =  mysqli_connect("localhost", "User Name", "Pass Word", "DB Name");
   $sql = "SELECT * from notification_table";
   $rs = mysqli_query($conn,$sql);
	header('Content-Type:application/json');
	
	$notification_data = array();
	while($info=mysqli_fetch_array($rs)){
		array_push($notification_data,
			array('id'=>$info['id'],
					'date'=>$info['date'],
					'title'=>$info['title'],
					'user'=>$info['user'],
					'description'=>$info['description']
				)
			);
	}
	echo json_encode(array("notification"=>$notification_data));
	mysqli_close($conn);
?>

