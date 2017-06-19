<?php
include_once 'conexion.php';
	$nombre=$_POST['nombre'];
	$sql=$dbcon->query("SELECT *FROM pokemon WHERE nombre='$nombre'");
	if(mysqli_num_rows($sql)>0){
		//echo "encontrado";
		$sql1=$dbcon->query("DELETE FROM pokemon WHERE nombre='$nombre'");
		if($sql1){
			echo "borrado";
		}else{
			echo "problemas al eliminar";
		}
	}else{
		//echo "no existe";
	}

?>