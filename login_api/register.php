<?php
require_once 'include/DB_Funcitons.php';
   $db = new DB_Funtions();
   //json reponse array
   $response = array ("error"=>FALSE);

   if(isset($_POST['name']) && isset($_POST['email']) && isset($_POST['password'])){
      //receiving the post params;
      $name=$_POST['name'];
      $email=$_POST['email'];
      $password=$_POST['password'];
      //if users is already existed?
      if($db->isUserExisted($email)){
         $response["error"]=TRUE;
         $response["error_msg"]="User already existed with ".$email;
         echo json_encode($response);
      }else{
         //create a new users
            $user = $db->storeUser($name, $email, $password);
         if($user){
            $response["error"]=FALSE;
            $response["uid"]=$user["unique_id"];
            $response["user"]["name"] = $user["name"];
            $response["user"]["email"] = $user["email"];
            $response["user"]["created_at"] = $user["created_at"];
            $response["user"]["updated_at"] = $user["updated_at"];
            echo json_encode($response);
         }else{
            $response["error"] = TRUE;
            $response["error_msg"] =  "Unknown error occurred in registrations!";
            echo json_encode($response);
         }
      }
   }else{
      $response["error"] = TRUE;
      $response["error_msg"] = "Required parameters (name, email or password) is missing!";
      echo json_encode($response);
   }
?>
