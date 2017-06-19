<?php
	include_once 'conexion.php';
	$nombre=$_POST['nombre'];
	$tipo=$_POST['tipo'];
	$region=$_POST['region'];
	$generacion=$_POST['generacion'];
	

	$sql1=$dbcon->query("INSERT INTO 
		pokemon(nombre,tipo,region,generacion)
		VALUES('$nombre','$tipo','$region','$generacion')");
	if($sql1){
		echo "Registrado";
	}else{
		echo "NO registrado";
	}
?>