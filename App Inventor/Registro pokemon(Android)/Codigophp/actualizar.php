<?php
include_once 'conexion.php';
	$nombre=$_POST['nombre'];
	$tipo=$_POST['tipo'];
	$region=$_POST['region'];
	$generacion=$_POST['generacion'];
	$sql=$dbcon->query("SELECT *FROM pokemon WHERE nombre='$nombre'");
	if(mysqli_num_rows($sql)>0){
	if($tipo!=null){
       $sql1=$dbcon->query("UPDATE pokemon SET nombre='$nombre', tipo='$tipo' WHERE nombre='$nombre'");
	}
	if($region!=null){
		$sql1=$dbcon->query("UPDATE pokemon SET nombre='$nombre', region='$region' WHERE nombre='$nombre'");
	}
	if($generacion!=null){
		$sql1=$dbcon->query("UPDATE pokemon SET nombre='$nombre', generacion='$generacion' WHERE nombre='$nombre'");
	}
	if($sql1){
			echo "actualizado";
		}else{
			echo "problemas al eliminar";
		}
	}else{
		//echo "no existe";

}
		

?>