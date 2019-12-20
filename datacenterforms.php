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
    
    //En caso de que ninguna de las opciones anteriores se haya ejecutado
    header("HTTP/1.1 400 Bad Request");
    ?>