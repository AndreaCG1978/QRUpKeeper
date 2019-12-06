    <?php
    include "config.php";
    include "utils.php";
    $dbConn =  connect($db);
    /*
      listar todos los posts o solo uno
     */
    if ($_SERVER['REQUEST_METHOD'] == 'GET')
    {


        if (isset($_GET['id']))
        {
          //Mostrar un registro
          $sql = $dbConn->prepare("SELECT * FROM tablerotgbt where id=:id");
          $sql->bindValue(':id', $_GET['id']);
          $sql->execute();
          header("HTTP/1.1 200 OK");
          echo json_encode(  $sql->fetch(PDO::FETCH_ASSOC)  );
          exit();
        }
        else {
          //Mostrar lista de registros
          $sql = $dbConn->prepare("SELECT * FROM tablerotgbt");
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
	echo "<script type='text/javascript'>
                alert('ENTRA POST !');
            </script>";
	echo "<script>".$input['id']."</script>"; 
	echo "<script>".$input['name']."</script>"; 
	echo "<script>".$input['description']."</script>"; 
	$tipoTablero = $input['type'];
	echo "<script> Tipo tablero: ".$tipoTablero."</script>"; 
	if($tipoTablero ==1){
		$sql = "INSERT INTO tablerotgbt(name, description,nroForm, kwr, kws, kwt, ar, as, at, inspectorId, datacenterId, datetime)
              VALUES(:name, :description, :nroForm, :kwr, :kws, :kwt, :ar, :as, :at, :inspectorId, :datacenterId, :datetime)";
    		$statement = $dbConn->prepare($sql);
		$statement->bindParam (":name", $_POST['name'] , PDO::PARAM_STR);
    		$statement->bindParam (":description",  $_POST['description'] , PDO::PARAM_STR);
		$statement->bindParam (":nroForm",  $_POST['nroForm'] , PDO::PARAM_STR);
		$statement->bindParam (":kwr",  $_POST['kwr'] , PDO::PARAM_STR);
		$statement->bindParam (":kws",  $_POST['kws'] , PDO::PARAM_STR);
		$statement->bindParam (":kwt",  $_POST['kwt'] , PDO::PARAM_STR);
		$statement->bindParam (":ar",  $_POST['ar'] , PDO::PARAM_STR);
		$statement->bindParam (":as",  $_POST['as'] , PDO::PARAM_STR);
		$statement->bindParam (":at",  $_POST['at'] , PDO::PARAM_STR);
		$statement->bindParam (":inspectorId",  $_POST['inspectorId'] , PDO::PARAM_STR);
		$statement->bindParam (":datacenterId",  $_POST['datacenterId'] , PDO::PARAM_STR);
    		$statement->bindParam (":datetime",  $_POST['datetime'] , PDO::PARAM_STR);
		$statement->execute();
	}
	
	
		   
  //     $itemId = $dbConn->lastInsertId();
       /*if($itemId)
        {
          $input['id'] = $itemId;
          header("HTTP/1.1 200 OK");
          echo json_encode( $_GET);
          exit();
       }*/
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
	
	$sql = "UPDATE item  SET $fields  WHERE id=$itemId ";
    $statement = $dbConn->prepare($sql);
    bindAllValues($statement, $input);
    $statement->execute();
       
	
	
	
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


    //Borrar
    if ($_SERVER['REQUEST_METHOD'] == 'DELETE')
    {
		echo "<script type='text/javascript'>alert('ENTRA DELETE!'); </script>";
	//	echo "<script type='text/javascript'> EN GET:".$_GET['id']."</script>";
	//	echo "<script type='text/javascript'> EN POST:".$_POST['id']."</script>";
	/*	
		parse_str(file_get_contents("php://input"), $_VARIABLE);

		foreach ($_VARIABLE as $key => $value)
		{
			unset($_VARIABLE[$key]);
			$_VARIABLE[str_replace('amp;', '', $key)] = $value;
		}

		$_REQUEST = array_merge($_REQUEST, $_VARIABLE);*/
	//	$input = $_VARIABLE;
		$itemId =$_GET['id'] ;
		

	/*	foreach ($_POST as $key => $value)
		{
			unset($_POST[$key]);
			 echo "<script type='text/javascript'> El KEY:".$key."</script>";
			 echo "<script type='text/javascript'> El VALUE:".$value."</script>";
			$_POST[str_replace('amp;', '', $key)] = $value;
		}

		$_REQUEST = array_merge($_REQUEST, $_POST);
		//$input = $_DELETE;
		$itemId = $_POST['id'] ;		*/
		 
		
  //    $id = $_GET['id'];
      	$statement = $dbConn->prepare("DELETE FROM item where id=:id");
      	$statement->bindValue(':id',$itemId);
        $statement->execute();
	  
	
	  
      header("HTTP/1.1 200 OK");
      exit();
    }
    
    //En caso de que ninguna de las opciones anteriores se haya ejecutado
    header("HTTP/1.1 400 Bad Request");
    ?>
