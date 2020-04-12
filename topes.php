<?php
    include "config.php";
    include "utils.php";
    $dbConn =  connect($db);
    /*
      listar todos los codigos de los artefactos que tienen nombre generico
     */
    if ($_SERVER['REQUEST_METHOD'] == 'GET')
    {
       
	if (isset($_GET['tokenIplan'])&& $_GET['tokenIplan'] == $tokenIplan )
        {
		//Mostrar un Tecnico
		$sql = $dbConn->prepare("SELECT * FROM artefactosValoresTopes");
	      	$sql->execute();
		$sql->setFetchMode(PDO::FETCH_ASSOC);
		header("HTTP/1.1 200 OK");
		echo json_encode( $sql->fetchAll());
        
	}
	exit();
		 
    }
   
    
    //En caso de que ninguna de las opciones anteriores se haya ejecutado
    header("HTTP/1.1 400 Bad Request");
    ?>
