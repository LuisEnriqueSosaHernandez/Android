<?php
	$host='localhost';
	$usuario='id3923502_cavanzo';
	$password='12345';
	$basedatos='id3923502_temperaturabd';
	$dbcon=new MySQLi("$host","$usuario","$password","$basedatos");
	if($dbcon->connect_error){
		echo "conexion_error";
	}/*else{
		echo "conexion_ok";
	}*/
?>