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
		echo "<script> Tipo tablero: ".$tipoTablero."</script>"; 
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
		echo "<script> SQL: ".$sqlInsert."</script>"; 
    		$statement = $dbConn->prepare($sqlInsert);
		$statement->bindParam (":name", $_POST['name'] , PDO::PARAM_STR);
		$statement->bindParam (":codigo", $_POST['codigo'] , PDO::PARAM_STR);
    		$statement->bindParam (":idForm",  $_POST['idForm'] , PDO::PARAM_STR);
		$statement->bindParam (":kwr",  $_POST['kwr'] , PDO::PARAM_STR);
		$statement->bindParam (":kws",  $_POST['kws'] , PDO::PARAM_STR);
		$statement->bindParam (":kwt",  $_POST['kwt'] , PDO::PARAM_STR);
		$statement->bindParam (":par",  $_POST['par'] , PDO::PARAM_STR);
		$statement->bindParam (":pas",  $_POST['pas'] , PDO::PARAM_STR);
		$statement->bindParam (":pat",  $_POST['pat'] , PDO::PARAM_STR);
		$statement->execute();
	/*	$lastId = $dbConn->lastInsertId();
		echo "<script> SQL: ".$sqlSearch."</script>"; 
		//realizo la busqueda del ultimo insertado
		$sql = $dbConn->prepare($sqlSearch);
	        $sql->bindValue(':id', $lastId);
	        $sql->execute();
		$sql->setFetchMode(PDO::FETCH_ASSOC);
         	header("HTTP/1.1 200 OK");
          	echo json_encode( $sql->fetchAll());*/
		exit();			
			
	}
    header("HTTP/1.1 400 Bad Request");
    ?>
