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
 	  }else
	  if($tipoTablero =='102'){
		$sqlSearch = "SELECT * FROM tablero_airechiller where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else
	  if($tipoTablero =='103'){
		$sqlSearch = "SELECT * FROM tablero_crac where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else
	  if($tipoTablero =='104'){
		$sqlSearch = "SELECT * FROM tablero_inups where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else
	  if($tipoTablero =='105'){
		$sqlSearch = "SELECT * FROM load_ups  where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else
	  if($tipoTablero =='106'){
		$sqlSearch = "SELECT * FROM grupo_electrogeno where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else
	  if($tipoTablero =='107'){
		$sqlSearch = "SELECT * FROM aire_crac where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else  
	  if($tipoTablero =='108'){
		$sqlSearch = "SELECT * FROM aire_chiller where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else  
	  if($tipoTablero =='109'){
		$sqlSearch = "SELECT * FROM incendio where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else  
	  if($tipoTablero =='110'){
		$sqlSearch = "SELECT * FROM presostato where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else
	  if($tipoTablero =='111'){
		$sqlSearch = "SELECT * FROM aireAcond where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else
	  if($tipoTablero =='112'){
		$sqlSearch = "SELECT * FROM tableroPDR where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else
	  if($tipoTablero =='113'){
		$sqlSearch = "SELECT * FROM presurizacionEscalera where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else
	  if($tipoTablero =='114'){
		$sqlSearch = "SELECT * FROM estractorAire where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
	  }else
	  if($tipoTablero =='115'){
		$sqlSearch = "SELECT * FROM presurizacionCanieria where name='".$_GET['name']."' and idForm =".$_GET['idForm']."";
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
			$sqlInsert = "INSERT INTO tablero_tgbt(name, codigo,idForm, kwr, kws, kwt, par, pas, pat, description)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat, :description)";
		}else
		if($tipoTablero =='102'){
			$sqlInsert = "INSERT INTO tablero_airechiller(name, codigo,idForm, kwr, kws, kwt, par, pas, pat, description)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat, :description)";
		}else
		if($tipoTablero =='103'){
			$sqlInsert = "INSERT INTO tablero_crac(name, codigo,idForm, kwr, kws, kwt, par, pas, pat, description)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat, :description)";
		}else
		if($tipoTablero =='104'){
			$sqlInsert = "INSERT INTO tablero_inups(name, codigo,idForm, kwr, kws, kwt, par, pas, pat, description)
	              VALUES(:name, :codigo, :idForm, :kwr, :kws, :kwt, :par, :pas, :pat, :description)";
		}else
		if($tipoTablero =='105'){
				$sqlInsert = "INSERT INTO load_ups(name, codigo,idForm, percent_r, percent_s, percent_t, alarma, description)
	              VALUES(:name, :codigo, :idForm, :par, :pas, :pat, :alarm, :description)";
		}else
		if($tipoTablero =='106'){
				$sqlInsert = "INSERT INTO grupo_electrogeno(name, codigo,idForm, percent_comb, temperatura, nivelcomb75, auto, precalent, cargadorbat, alarma, description)
	              VALUES(:name, :codigo, :idForm, :percent_comb, :temperatura, :nivelcomb75, :auto, :precalent, :cargadorbat, :alarm, :description)";
		}else
		if($tipoTablero =='107'){
				$sqlInsert = "INSERT INTO aire_crac(name, codigo,idForm, funciona_ok, temperatura, description)
	              VALUES(:name, :codigo, :idForm, :funciona_ok, :temperatura, :description)";
		}else
		if($tipoTablero =='108'){
				$sqlInsert = "INSERT INTO aire_chiller(name, codigo,idForm, comp1_ok, comp1_load, comp2_ok, comp2_load, atr_out, description)
	              VALUES(:name, :codigo, :idForm, :comp1_ok, :comp1_load, :comp2_ok, :comp2_load, :atr_out, :description)";
		}else
		if($tipoTablero =='109'){
				$sqlInsert = "INSERT INTO incendio(name, codigo,idForm, energiaA_ok, energiaB_ok, funciona_ok, presion, description)
	              VALUES(:name, :codigo, :idForm, :energiaA_ok, :energiaB_ok, :funciona_ok, :presion, :description)";
		}else
		if($tipoTablero =='110'){
				$sqlInsert = "INSERT INTO presostato(name, codigo,idForm, agua_ok, aire_ok, agua_presion, aire_presion, description)
	              VALUES(:name, :codigo, :idForm, :agua_ok, :aire_ok, :agua_presion, :aire_presion, :description)";
		}else
		if($tipoTablero =='111'){
				$sqlInsert = "INSERT INTO aireAcond(name, codigo,idForm, funciona_ok, temperatura, description)
	              VALUES(:name, :codigo, :idForm, :funciona_ok, :temperatura, :description)";
		}else
		if($tipoTablero =='112'){
				$sqlInsert = "INSERT INTO tableroPDR(name, codigo,idForm, pottotRA, pottotRB, description)
	              VALUES(:name, :codigo, :idForm, :pottotRA, :pottotRB, :description)";
		}else
		if($tipoTablero =='113'){
				$sqlInsert = "INSERT INTO presurizacionEscalera(name, codigo,idForm, arranque, correas, engrase, funcionamiento, limpieza, tiemp, description)
	              VALUES(:name, :codigo, :idForm, :arranque, :correas, :engrase, :funcionamiento, :limpieza, :tiemp, :description)";
		}else
		if($tipoTablero =='114'){
				$sqlInsert = "INSERT INTO estractorAire(name, codigo,idForm, arranque, correas, engrase, funcionamiento, limpieza, description)
	              VALUES(:name, :codigo, :idForm, :arranque, :correas, :engrase, :funcionamiento, :limpieza, :description)";
		}else
		if($tipoTablero =='115'){
				$sqlInsert = "INSERT INTO presurizacionCanieria(name, codigo,idForm, alarma, encendido, description)
	              VALUES(:name, :codigo, :idForm, :alarma, :encendido, :description)";
		}
				
    		$statement = $dbConn->prepare($sqlInsert);
		echo "<script> SCRIPT POST".$sqlInsert." </script>"; 
		if($tipoTablero =='101' || $tipoTablero =='102' || $tipoTablero =='103' || $tipoTablero =='104'){
			$statement->bindParam (":kwr",  $_POST['kwr'] , PDO::PARAM_STR);
			$statement->bindParam (":kws",  $_POST['kws'] , PDO::PARAM_STR);
			$statement->bindParam (":kwt",  $_POST['kwt'] , PDO::PARAM_STR);
			$statement->bindParam (":par",  $_POST['par'] , PDO::PARAM_STR);
			$statement->bindParam (":pas",  $_POST['pas'] , PDO::PARAM_STR);
			$statement->bindParam (":pat",  $_POST['pat'] , PDO::PARAM_STR);
		}else
		if($tipoTablero =='105'){
			$statement->bindParam (":alarm",  $_POST['alarm'] , PDO::PARAM_STR);
			$statement->bindParam (":par",  $_POST['par'] , PDO::PARAM_STR);
			$statement->bindParam (":pas",  $_POST['pas'] , PDO::PARAM_STR);
			$statement->bindParam (":pat",  $_POST['pat'] , PDO::PARAM_STR);
			
		}else
		if($tipoTablero =='106'){
			$statement->bindParam (":alarm",  $_POST['alarm'] , PDO::PARAM_STR);
			$statement->bindParam (":percent_comb",  $_POST['percent_comb'] , PDO::PARAM_STR);
			$statement->bindParam (":temperatura",  $_POST['temperatura'] , PDO::PARAM_STR);
			$statement->bindParam (":nivelcomb75",  $_POST['nivelcomb75'] , PDO::PARAM_STR);
			$statement->bindParam (":auto",  $_POST['auto'] , PDO::PARAM_STR);
			$statement->bindParam (":precalent",  $_POST['precalent'] , PDO::PARAM_STR);
			$statement->bindParam (":cargadorbat",  $_POST['cargadorbat'] , PDO::PARAM_STR);
	
		}else	
		if($tipoTablero =='107'){
			$statement->bindParam (":funciona_ok",  $_POST['funciona_ok'] , PDO::PARAM_STR);
			$statement->bindParam (":temperatura",  $_POST['temperatura'] , PDO::PARAM_STR);
		}else	
		if($tipoTablero =='108'){
			$statement->bindParam (":comp1_ok",  $_POST['comp1_ok'] , PDO::PARAM_STR);
			$statement->bindParam (":comp1_load",  $_POST['comp1_load'] , PDO::PARAM_STR);
			$statement->bindParam (":comp2_ok",  $_POST['comp2_ok'] , PDO::PARAM_STR);
			$statement->bindParam (":comp2_load",  $_POST['comp2_load'] , PDO::PARAM_STR);
			$statement->bindParam (":atr_out",  $_POST['atr_out'] , PDO::PARAM_STR);
		}else	
		if($tipoTablero =='109'){
			$statement->bindParam (":energiaA_ok",  $_POST['energiaA_ok'] , PDO::PARAM_STR);
			$statement->bindParam (":energiaB_ok",  $_POST['energiaB_ok'] , PDO::PARAM_STR);
			$statement->bindParam (":funciona_ok",  $_POST['funciona_ok'] , PDO::PARAM_STR);
			$statement->bindParam (":presion",  $_POST['presion'] , PDO::PARAM_STR);
		}else	
		if($tipoTablero =='110'){
			$statement->bindParam (":agua_ok",  $_POST['agua_ok'] , PDO::PARAM_STR);
			$statement->bindParam (":aire_ok",  $_POST['aire_ok'] , PDO::PARAM_STR);
			$statement->bindParam (":agua_presion",  $_POST['agua_presion'] , PDO::PARAM_STR);
			$statement->bindParam (":aire_presion",  $_POST['aire_presion'] , PDO::PARAM_STR);
		}else	
		if($tipoTablero =='111'){
			$statement->bindParam (":funciona_ok",  $_POST['funciona_ok'] , PDO::PARAM_STR);
			$statement->bindParam (":temperatura",  $_POST['temperatura'] , PDO::PARAM_STR);
		}else	
		if($tipoTablero =='112'){
			$statement->bindParam (":pottotRA",  $_POST['pottotRA'] , PDO::PARAM_STR);
			$statement->bindParam (":pottotRB",  $_POST['pottotRB'] , PDO::PARAM_STR);
		}else	
		if($tipoTablero =='113'){
			$statement->bindParam (":arranque",  $_POST['arranque'] , PDO::PARAM_STR);
			$statement->bindParam (":correas",  $_POST['correas'] , PDO::PARAM_STR);
			$statement->bindParam (":engrase",  $_POST['engrase'] , PDO::PARAM_STR);
			$statement->bindParam (":funcionamiento",  $_POST['funcionamiento'] , PDO::PARAM_STR);
			$statement->bindParam (":limpieza",  $_POST['limpieza'] , PDO::PARAM_STR);
			$statement->bindParam (":tiemp",  $_POST['tiemp'] , PDO::PARAM_STR);
		}else	
		if($tipoTablero =='114'){
			$statement->bindParam (":arranque",  $_POST['arranque'] , PDO::PARAM_STR);
			$statement->bindParam (":correas",  $_POST['correas'] , PDO::PARAM_STR);
			$statement->bindParam (":engrase",  $_POST['engrase'] , PDO::PARAM_STR);
			$statement->bindParam (":funcionamiento",  $_POST['funcionamiento'] , PDO::PARAM_STR);
			$statement->bindParam (":limpieza",  $_POST['limpieza'] , PDO::PARAM_STR);
		}else	
		if($tipoTablero =='115'){
			$statement->bindParam (":alarma",  $_POST['alarma'] , PDO::PARAM_STR);
			$statement->bindParam (":encendido",  $_POST['encendido'] , PDO::PARAM_STR);
		}	
		$statement->bindParam (":name", $_POST['name'] , PDO::PARAM_STR);
		$statement->bindParam (":codigo", $_POST['codigo'] , PDO::PARAM_STR);
    		$statement->bindParam (":idForm",  $_POST['idForm'] , PDO::PARAM_STR);
    		$statement->bindParam (":description",  $_POST['description'] , PDO::PARAM_STR);		
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
	}else
	if($tipoTablero =='102'){
		$sqlUpdate = "UPDATE tablero_airechiller SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='103'){
		$sqlUpdate = "UPDATE tablero_crac SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='104'){
		$sqlUpdate = "UPDATE tablero_inups SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='105'){
		$sqlUpdate = "UPDATE load_ups SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='106'){
		$sqlUpdate = "UPDATE grupo_electrogeno SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='107'){
		$sqlUpdate = "UPDATE aire_crac SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='108'){
		$sqlUpdate = "UPDATE aire_chiller SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='109'){
		$sqlUpdate = "UPDATE incendio SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='110'){
		$sqlUpdate = "UPDATE presostato SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='111'){
		$sqlUpdate = "UPDATE aireAcond SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='112'){
		$sqlUpdate = "UPDATE tableroPDR SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='113'){
		$sqlUpdate = "UPDATE presurizacionEscalera SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='114'){
		$sqlUpdate = "UPDATE estractorAire SET $fields  WHERE id=$itemId ";
	}else
	if($tipoTablero =='115'){
		$sqlUpdate = "UPDATE presurizacionCanieria SET $fields  WHERE id=$itemId ";
	}	
	echo "<script> SCRIPT PUT ".$sqlUpdate." </script>"; 	
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
	}else
	if($tipoTablero =='102'){
		$sqlDelete = "DELETE FROM tablero_airechiller where id=:id";
	}else
	if($tipoTablero =='103'){
		$sqlDelete = "DELETE FROM tablero_crac where id=:id";
	}else
	if($tipoTablero =='104'){
		$sqlDelete = "DELETE FROM tablero_inups where id=:id";
	}else
	if($tipoTablero =='105'){
		$sqlDelete = "DELETE FROM load_ups where id=:id";
	}else
	if($tipoTablero =='106'){
		$sqlDelete = "DELETE FROM grupo_electrogeno where id=:id";
	}else
	if($tipoTablero =='107'){
		$sqlDelete = "DELETE FROM aire_crac where id=:id";
	}else
	if($tipoTablero =='108'){
		$sqlDelete = "DELETE FROM aire_chiller where id=:id";
	}else
	if($tipoTablero =='109'){
		$sqlDelete = "DELETE FROM incendio where id=:id";
	}else
	if($tipoTablero =='110'){
		$sqlDelete = "DELETE FROM presostato where id=:id";
	}else
	if($tipoTablero =='111'){
		$sqlDelete = "DELETE FROM aireAcond where id=:id";
	}else
	if($tipoTablero =='112'){
		$sqlDelete = "DELETE FROM tableroPDR where id=:id";
	}else
	if($tipoTablero =='113'){
		$sqlDelete = "DELETE FROM presurizacionEscalera where id=:id";
	}else
	if($tipoTablero =='114'){
		$sqlDelete = "DELETE FROM estractorAire where id=:id";
	}else
	if($tipoTablero =='115'){
		$sqlDelete = "DELETE FROM presurizacionCanieria where id=:id";
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
