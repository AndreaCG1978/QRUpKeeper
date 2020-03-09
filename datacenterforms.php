<?php
    include "config.php";
    include "utils.php";
    $dbConn =  connect($db);
    /*
      listar todos los Inspectores o solo uno
     */
    if ($_SERVER['REQUEST_METHOD'] == 'GET')
    {
       
	if (isset($_GET['nroForm']))
        {
	  
	  $sql = $dbConn->prepare("SELECT * FROM forms where nroForm = '".$_GET['nroForm']."'");
          $sql->execute();
	  $sql->setFetchMode(PDO::FETCH_ASSOC);
	  header("HTTP/1.1 200 OK");
	  echo json_encode( $sql->fetchAll(),JSON_UNESCAPED_UNICODE);
	  exit();
        
        } else {
	        
          $sql = $dbConn->prepare("SELECT * FROM forms");
          $sql->execute();
          $sql->setFetchMode(PDO::FETCH_ASSOC);
          header("HTTP/1.1 200 OK");
          echo json_encode( $sql->fetchAll(),JSON_UNESCAPED_UNICODE);
          exit();
	}
		 
    }
   
   
    // Crear un nuevo dato
    if ($_SERVER['REQUEST_METHOD'] === 'POST')
    {
	$input = $_POST;
	$sql = "INSERT INTO forms(nroForm, inspectorId, datacenterId, description, fecha)
        VALUES(:nroForm, :inspectorId, :datacenterId, :description,:fecha)";
	$statement = $dbConn->prepare($sql);
	$statement->bindParam (":description",  $_POST['description'] , PDO::PARAM_STR);
	$statement->bindParam (":nroForm",  $_POST['nroForm'] , PDO::PARAM_STR);
	$statement->bindParam (":inspectorId",  $_POST['inspectorId'] , PDO::PARAM_INT);
	$statement->bindParam (":datacenterId",  $_POST['datacenterId'] , PDO::PARAM_INT);
	$statement->bindParam (":fecha",  $_POST['fecha'] , PDO::PARAM_STR);
	$statement->execute();
	$last_id = $dbConn->lastInsertId();
	$statement = $dbConn->prepare("SELECT * FROM forms where id = ".$last_id);
	$statement->execute();
	$statement->setFetchMode(PDO::FETCH_ASSOC);
	header("HTTP/1.1 200 OK");
	echo json_encode( $statement->fetch(),JSON_UNESCAPED_UNICODE);
	exit();
	
    }
    //Actualizar
    if ($_SERVER['REQUEST_METHOD'] == 'PUT')
    {
	parse_str(file_get_contents("php://input"), $_PUT);
	foreach ($_PUT as $key => $value)
	{
	    unset($_PUT[$key]);
	    $_PUT[str_replace('amp;', '', $key)] = $value;
	}
	$_REQUEST = array_merge($_REQUEST, $_PUT);
	$input = $_PUT;
	$itemId = $_PUT['id'] ;
	$fields = getParams($input);
	$sql = "UPDATE forms  SET $fields  WHERE id=$itemId ";
    	$statement = $dbConn->prepare($sql);
	bindAllValues($statement, $input);
	$result = $statement->execute();

        exit();
    }
    
    //En caso de que ninguna de las opciones anteriores se haya ejecutado
    header("HTTP/1.1 400 Bad Request");
    ?>
