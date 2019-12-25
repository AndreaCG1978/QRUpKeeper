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
		  echo json_encode( $sql->fetchAll());
		  exit();
		  
		  
        
        } else {
		         //Mostrar lista de post
		         $sql = $dbConn->prepare("SELECT * FROM forms");
		         $sql->execute();
		         $sql->setFetchMode(PDO::FETCH_ASSOC);
		         header("HTTP/1.1 200 OK");
		         echo json_encode( $sql->fetchAll()  );
		         exit();
		  }
		 
    }
   
   
    // Crear un nuevo dato
    if ($_SERVER['REQUEST_METHOD'] === 'POST')
    {
			$input = $_POST;
			$sql = "INSERT INTO forms(nroForm, inspectorId, datacenterId, description, fecha)
	              VALUES(:nroForm, :inspectorId, :datacenterId, :description,:fecha)";
			echo "<script> SQL: ".$sql."</script>"; 
	    		$statement = $dbConn->prepare($sql);
	    		$statement->bindParam (":description",  $_POST['description'] , PDO::PARAM_STR);
			$statement->bindParam (":nroForm",  $_POST['nroForm'] , PDO::PARAM_STR);
			$statement->bindParam (":inspectorId",  $_POST['inspectorId'] , PDO::PARAM_INT);
			$statement->bindParam (":datacenterId",  $_POST['datacenterId'] , PDO::PARAM_INT);
			$statement->bindParam (":fecha",  $_POST['fecha'] , PDO::PARAM_STR);
		    /*$itemId = $dbConn->lastInsertId();
			$sql = $dbConn->prepare("SELECT * FROM forms where id = '".$itemId."'");
			$sql->setFetchMode(PDO::FETCH_ASSOC);
	      	$sql->execute();
		 
		    header("HTTP/1.1 200 OK");
		    echo json_encode( $sql->fetchAll());
			*/
			
			echo json_encode( $statement->execute());
			exit();
			
    }
//Actualizar
    if ($_SERVER['REQUEST_METHOD'] == 'PUT')
    {
	//$input = $_GET;
	echo "<script type='text/javascript'>
                alert('ENTRA PUT!');
            </script>";
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
	echo "<script type='text/javascript'>
                alert('SQL ACTUALIZAR:".$sql."');
            </script>";
    	$statement = $dbConn->prepare($sql);
	bindAllValues($statement, $input);
	$result = $statement->execute();


       
	
	
	
	/*
	$sql = "UPDATE item SET (name=:name, description=:description) WHERE id=$itemId ";
	
	echo  "<script>SQL FINAL: ". $sql."</script>"; 
    	$statement = $dbConn->prepare($sql);
    //$statement->bindParam (":id",  $_PUT['id'] , PDO::PARAM_INT);
	$statement->bindParam (":name",  $_PUT['name'] , PDO::PARAM_STR);
    $statement->bindParam (":description",   $_PUT['description'] , PDO::PARAM_STR);
	$statement->execute();*/
	   
	   
	   
	   
      //  header("HTTP/1.1 200 OK");
        exit();
    }
    
    //En caso de que ninguna de las opciones anteriores se haya ejecutado
    header("HTTP/1.1 400 Bad Request");
    ?>
