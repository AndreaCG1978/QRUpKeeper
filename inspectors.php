<?php
    include "config.php";
    include "utils.php";
    $dbConn =  connect($db);
    /*
      listar todos los Inspectores o solo uno
     */
    if ($_SERVER['REQUEST_METHOD'] == 'GET')
    {
       
	if (isset($_GET['usr']) && isset($_GET['psw']) )
        {
		//Mostrar un Tecnico
		$sql = $dbConn->prepare("SELECT * FROM inspectors where usr = '".$_GET['usr']."' and psw = '".$_GET['psw']."'");
	      	$sql->execute();
		$sql->setFetchMode(PDO::FETCH_ASSOC);
		header("HTTP/1.1 200 OK");
		echo json_encode( $sql->fetchAll(),JSON_UNESCAPED_UNICODE);
        
	}
	exit();
		 
    }
   
    
    //En caso de que ninguna de las opciones anteriores se haya ejecutado
    header("HTTP/1.1 400 Bad Request");
    ?>
