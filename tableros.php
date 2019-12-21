<?php
    include "config.php";
    include "utils.php";
    $dbConn =  connect($db);
    echo "<script>EJECUTA ARCHIVO</script>"; 
    //En caso de que ninguna de las opciones anteriores se haya ejecutado
    if ($_SERVER['REQUEST_METHOD'] == 'GET')
    {
        if (isset($_GET['id']))
        {
          //Mostrar un registro
          $sql = $dbConn->prepare("SELECT * FROM tablero_tgbt where id=:id");
          $sql->bindValue(':id', $_GET['id']);
          $sql->execute();
          header("HTTP/1.1 200 OK");
          echo json_encode(  $sql->fetch(PDO::FETCH_ASSOC)  );
          exit();
        }
        else {
          //Mostrar lista de registros
          $sql = $dbConn->prepare("SELECT * FROM tablero_tgbt");
          $sql->execute();
          $sql->setFetchMode(PDO::FETCH_ASSOC);
          header("HTTP/1.1 200 OK");
          echo json_encode( $sql->fetchAll()  );
          exit();
      }
    }

    if ($_SERVER['REQUEST_METHOD'] == 'POST')
    {
		echo "<script>ENTRA AL FUCKING POST</script>"; 
		$tipoTablero = $_POST['codigo'];
		echo "<script> Tipo tablero: ".$tipoTablero."</script>"; 
		if($tipoTablero =='101'){
			$sql = "INSERT INTO tablero_tgbt(name, codigo,idForm, kwr, kws, kwt, par, pas, pat)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat)";
		}
		if($tipoTablero =='102'){
			$sql = "INSERT INTO tablero_airechiller(name, codigo,idForm, kwr, kws, kwt, par, pas, pat)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat)";
		}
		if($tipoTablero =='103'){
			$sql = "INSERT INTO tablero_crac(name, codigo,idForm, kwr, kws, kwt, par, pas, pat)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat)";
		}
		if($tipoTablero =='104'){
			$sql = "INSERT INTO tablero_inups(name, codigo,idForm, kwr, kws, kwt, par, pas, pat)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat)";
		}
		echo "<script> SQL: ".$sql."</script>"; 
    		$statement = $dbConn->prepare($sql);
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
		exit();			
			
	}
	
	
    echo "<script>LLEGA FIN</script>"; 
    header("HTTP/1.1 400 Bad Request");
    ?>
