<?php
/**
*@author Linfeng;
*/

class DB_Functions {
   private $conn;
   //contructor;
   function _construct(){
      require_once 'DB_Connect.php';
      $db = new DB_Connect();
      //calling the connect method of the object of $db;
      $this ->conn = $db->connect();
      echo "success";
   }

   //desctructor;
   function _destruct(){

   }
   /**
   *storing new user;returns user details;
   */
   public function storeUser($name,$email,$password){
      $uuid = uniqid('',true);
      $hash = $this -> hashSSHA($password);
      $encrypted_password = $hash["encrypted"];
      $salt = $hash["salt"];

      $stmt = $this ->conn ->prepare ("INSERT INTO users(unique_id,name,email,encrypted_password,salt,created_at) VALUES(?,?,?,?,?,NOW())");
      $stmt ->bind_param("sssss",$uuid,$name,$email,$encrypted_password,$salt);

      $result = $stmt->execute();
      $stmt ->close();

      //check for successful store;
      if($result){
         $stmt = $this->conn->preprare ("SELECT * FROM users WHERE email = ?");
         $stmt -> bind_param("s",$email);
         $stmt -> execute();
         $user = $stmt->get_result()->fetch_assoc();
         $stmt->close();
         return $user;
      }else {
         return false;
      }
}
   /**
   *get user by email and password;
   */
   public function getUserByEmailAndPassword($email,$password){
      $stmt = $this->conn->prepare("SELECT * FROM users WHERE email = ?");
      $stmt->bind_param("s",$email);
      if($stmt->execute()){
         $user = $stmt ->get_result()->fetch_assoc();
         $stmt->close();
         //verifying user $password
         $salt = $user['salt'];
         $encrypted_password = $user['encrypted_password'];
         $hash = $this-> checkhashSSHA($salt,$password);
         //check for password equality
         if($encrypted_password == $hash){
            return $user;
         }else{
            return NULL;
         }
      }
   /**
   *check user is existed or not;
   */
   public function isUserExisted($email){
      $stmt = $this->conn->prepare("SELECT email FROM users WHERE email = ?");
      $stmt->bind_param("s",$email);
      $stmt->execute();
      $stmt->store_result();
      if($stmt->num_rows>0){
         //user existed;
         $stmt->close();
         return true;
      }else{
         //user not existed;
         $stmt->close();
         return false;
      }
   }

   /**
   *encrypting password;
   */
   public funciton hashSSHA($password){
      $salt=sha1(rand());
      $salt=substr($salt,0,10);
      $encrypted = base64_encode(sha1($password.$salt,ture).$salt);
      $hash = array("salt"=>$salt,"encrypted"=>$encrypted);
      return $hash;
   }

   /**
   *decrypting password;
   */
   public function checkhashSSHA($salt,$password){
      $hash=base64_encode(sha1($password.$salt,true).$salt);
      return $hash;
   }
}
?>
