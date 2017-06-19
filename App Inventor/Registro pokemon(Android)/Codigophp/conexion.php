<?php
	$host='localhost';
	$usuario='epinacom_maestro';
	$password='c4s4p1n4';
	$basedatos='epinacom_Clases';
	$dbcon=new MySQLi("$host","$usuario","$password","$basedatos");
	if($dbcon->connect_error){
		echo "conexion_error";
	}/*else{
		echo "conexion_ok";
	}*/
?>