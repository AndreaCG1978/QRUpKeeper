<?php
    include "config.php";
    include "utils.php";
    $dbConn =  connect($db);
    //En caso de que ninguna de las opciones anteriores se haya ejecutado
    if ($_SERVER['REQUEST_METHOD'] == 'GET')
    {
        if (isset($_GET['codigo']) && isset($_GET['name']) && isset($_GET['idForm']))
        {
          //Mostrar un registro
	  $tipoTablero = $_GET['codigo'];
	  if($tipoTablero =='101'){
		$sqlSearch = "SELECT * FROM tablero_tgbt where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
 	  }
	  if($tipoTablero =='102'){
		$sqlSearch = "SELECT * FROM tablero_airechiller where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }
	  if($tipoTablero =='103'){
		$sqlSearch = "SELECT * FROM tablero_crac where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }
	  if($tipoTablero =='104'){
		$sqlSearch = "SELECT * FROM tablero_inups where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }
	  if($tipoTablero =='105'){
		$sqlSearch = "SELECT * FROM load_ups  where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }	  
	  if($tipoTablero =='106'){
		$sqlSearch = "SELECT * FROM grupo_electrogeno where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }	  
          $sql = $dbConn->prepare($sqlSearch);
          $sql->execute();
	  $sql->setFetchMode(PDO::FETCH_ASSOC);
          header("HTTP/1.1 200 OK");
          echo json_encode( $sql->fetchAll());
          exit();
        }else{
	  //Mostrar lista de registros
		$sql = $dbConn->prepare("SELECT * FROM tablero_tgbt");
		$sql->execute();
		$sql->setFetchMode(PDO::FETCH_ASSOC);
		header("HTTP/1.1 200 OK");
		echo json_encode( $sql->fetchAll());
		exit();
	}
    }
    if ($_SERVER['REQUEST_METHOD'] === 'POST')
    {
		
		$tipoTablero = $_POST['codigo'];
		if($tipoTablero =='101'){
			$sqlInsert = "INSERT INTO tablero_tgbt(name, codigo,idForm, kwr, kws, kwt, par, pas, pat)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat)";
		}
		if($tipoTablero =='102'){
			$sqlInsert = "INSERT INTO tablero_airechiller(name, codigo,idForm, kwr, kws, kwt, par, pas, pat)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat)";
		}
		if($tipoTablero =='103'){
			$sqlInsert = "INSERT INTO tablero_crac(name, codigo,idForm, kwr, kws, kwt, par, pas, pat)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat)";
		}
		if($tipoTablero =='104'){
			$sqlInsert = "INSERT INTO tablero_inups(name, codigo,idForm, kwr, kws, kwt, par, pas, pat)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat)";
		}
		if($tipoTablero =='105'){
				$sqlInsert = "INSERT INTO load_ups(name, codigo,idForm, percent_r, percent_s, percent_t, alarma)
	              VALUES(:name, :codigo, :idForm, :par, :pas, :pat, :alarm)";
		}
		if($tipoTablero =='106'){
				$sqlInsert = "INSERT INTO grupo_electrogeno(name, codigo,idForm, percent_comb, temperatura, nivelcomb75, auto, precalent, cargadorbat, alarma)
	              VALUES(:name, :codigo, :idForm, :percent_comb, :temperatura, :nivelcomb75, :auto, :precalent, :cargadorbat, :alarm)";
		}		
    		$statement = $dbConn->prepare($sqlInsert);
		echo "<script> SCRIPT INSERT GRUPO ".$sqlInsert." </script>"; 
		if($tipoTablero =='101' || $tipoTablero =='102' || $tipoTablero =='103' || $tipoTablero =='104'){
			$statement->bindParam (":kwr",  $_POST['kwr'] , PDO::PARAM_STR);
			$statement->bindParam (":kws",  $_POST['kws'] , PDO::PARAM_STR);
			$statement->bindParam (":kwt",  $_POST['kwt'] , PDO::PARAM_STR);
			$statement->bindParam (":par",  $_POST['par'] , PDO::PARAM_STR);
			$statement->bindParam (":pas",  $_POST['pas'] , PDO::PARAM_STR);
			$statement->bindParam (":pat",  $_POST['pat'] , PDO::PARAM_STR);
		}
		if($tipoTablero =='105'){
			$statement->bindParam (":alarm",  $_POST['alarm'] , PDO::PARAM_STR);
			$statement->bindParam (":par",  $_POST['par'] , PDO::PARAM_STR);
			$statement->bindParam (":pas",  $_POST['pas'] , PDO::PARAM_STR);
			$statement->bindParam (":pat",  $_POST['pat'] , PDO::PARAM_STR);
			
		}
		if($tipoTablero =='106'){
			$statement->bindParam (":alarm",  $_POST['alarm'] , PDO::PARAM_STR);
			$statement->bindParam (":percent_comb",  $_POST['percent_comb'] , PDO::PARAM_STR);
			$statement->bindParam (":temperatura",  $_POST['temperatura'] , PDO::PARAM_STR);
			$statement->bindParam (":nivelcomb75",  $_POST['nivelcomb75'] , PDO::PARAM_STR);
			$statement->bindParam (":auto",  $_POST['auto'] , PDO::PARAM_STR);
			$statement->bindParam (":precalent",  $_POST['precalent'] , PDO::PARAM_STR);
			$statement->bindParam (":cargadorbat",  $_POST['cargadorbat'] , PDO::PARAM_STR);
	
		}		
			
		$statement->bindParam (":name", $_POST['name'] , PDO::PARAM_STR);
		$statement->bindParam (":codigo", $_POST['codigo'] , PDO::PARAM_STR);
    		$statement->bindParam (":idForm",  $_POST['idForm'] , PDO::PARAM_STR);
		
		$statement->execute();
		exit();			
			
    }
    if ($_SERVER['REQUEST_METHOD'] == 'PUT')
    {
	//$input = $_GET;
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
	$tipoTablero = $_PUT['codigo'];
	if($tipoTablero =='101'){
		$sqlUpdate = "UPDATE tablero_tgbt SET $fields  WHERE id=$itemId ";
	}
	if($tipoTablero =='102'){
		$sqlUpdate = "UPDATE tablero_airechiller SET $fields  WHERE id=$itemId ";
	}
	if($tipoTablero =='103'){
		$sqlUpdate = "UPDATE tablero_crac SET $fields  WHERE id=$itemId ";
	}
	if($tipoTablero =='104'){
		$sqlUpdate = "UPDATE tablero_inups SET $fields  WHERE id=$itemId ";
	}
	if($tipoTablero =='105'){
		$sqlUpdate = "UPDATE load_ups SET $fields  WHERE id=$itemId ";
	}	
	if($tipoTablero =='106'){
		$sqlUpdate = "UPDATE grupo_electrogeno SET $fields  WHERE id=$itemId ";
	}		
    	$statement = $dbConn->prepare($sqlUpdate);
	bindAllValues($statement, $input);
	$statement->execute();
        exit();
    }
    //Borrar
    if ($_SERVER['REQUEST_METHOD'] == 'DELETE')
    {
	echo "<script> ENTRO DELETE </script>"; 
	$itemId =$_GET['id'] ;
	$tipoTablero = $_GET['codigo'];
	if($tipoTablero =='101'){
		$sqlDelete = "DELETE FROM tablero_tgbt where id=:id";
	}
	if($tipoTablero =='102'){
		$sqlDelete = "DELETE FROM tablero_airechiller where id=:id";
	}
	if($tipoTablero =='103'){
		$sqlDelete = "DELETE FROM tablero_crac where id=:id";
	}
	if($tipoTablero =='104'){
		$sqlDelete = "DELETE FROM tablero_inups where id=:id";
	}
	if($tipoTablero =='105'){
		$sqlDelete = "DELETE FROM load_ups where id=:id";
	}	
	if($tipoTablero =='106'){
		$sqlDelete = "DELETE FROM grupo_electrogeno where id=:id";
	}	
	echo "<script> SQL: ".$sqlDelete."</script>"; 
      	$statement = $dbConn->prepare($sqlDelete);
      	$statement->bindValue(':id',$itemId);
        $statement->execute();
	header("HTTP/1.1 200 OK");
	exit();
    }
    header("HTTP/1.1 400 Bad Request");
    ?>
