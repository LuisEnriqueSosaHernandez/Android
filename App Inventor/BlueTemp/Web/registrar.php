<?php
	include_once 'conexion.php';
	$temperatura=$_POST['temperatura'];
	$nomenclatura=$_POST['nomenclatura'];
	$fecha=$_POST['fecha'];
	$hora=$_POST['hora'];
	$sql1=$dbcon->query("INSERT INTO 
		datos(temperatura,nomenclatura,fecha,hora)
		VALUES('$temperatura','$nomenclatura','$fecha','$hora')");
	if($sql1){
		echo "Registrado";
	}else{
		echo "NO registrado";
	}
?>