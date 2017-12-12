<?php 
//Parámetros para conectar a la bdd
$server = 'localhost';
$username = 'id3923502_cavanzo';
$password = '12345';
$database = 'id3923502_temperaturabd';

$conexion = mysqli_connect($server,$username,$password);
mysqli_set_charset($conexion,"utf8");

  if(!$conexion){
    die("Conexión fallida: ".mysqli_connect_error());
    echo 'error al conectar';
    exit;
  }

  if (!mysqli_select_db($conexion,$database)) {
    die("Seleccción de Base de Datos fallida: ".mysqli_connect_error());
    exit;
  }

 ?>