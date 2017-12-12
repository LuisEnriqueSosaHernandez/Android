
<?php
include_once 'conexion2.php';
@$fecha1=$_POST['fecha1'];
@$fecha2=$_POST['fecha2'];
  
  $data=mysqli_query($conexion,"SELECT * FROM datos WHERE fecha BETWEEN '$fecha1' AND '$fecha2'"); //Ejecuta la consulta
  $resultado=array();
  //echo "Campos=".mysqli_num_rows($data);

  $i=0;
      
    while($i<mysqli_num_rows($data)){
       $resultado[$i] = mysqli_fetch_array($data,MYSQLI_ASSOC);
        //echo "Id: ".$resultado[$i]['id']." ";
       echo "Temperatura: ".$resultado[$i]['temperatura']." ";
      echo "Nomenclatura: ".$resultado[$i]['nomenclatura']." ";
      echo "Fecha: ".$resultado[$i]['fecha']." ";
      echo "Hora: ".$resultado[$i]['hora']."#";


       $i++;
    }
 

  mysqli_close($conexion); //Cierra la conexión



?>