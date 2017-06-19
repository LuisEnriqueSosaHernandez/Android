
<?php
$link = mysql_connect("localhost", "epinacom_maestro", "c4s4p1n4");
mysql_select_db("epinacom_Clases", $link);
@$nombre=$_POST['nombre'];
if($nombre!=null){
      $result = mysql_query("SELECT *FROM pokemon WHERE nombre='$nombre'", $link);
      if($result){
        $row2 = mysql_fetch_array($result);
        if($row2!=null){
        echo "Nombre: ".$row2['nombre']." ";
       echo "Tipo: ".$row2['tipo']." ";
        echo "Region: ".$row2['region']." ";
         echo "Generacion: ".$row2['generacion']."#";
       }else{
        echo 'Pokemon no encontrado';
       }
    }else{
      echo 'Error';
    }
      
  }else{


$result = mysql_query("SELECT * FROM pokemon", $link);
// comienza un bucle que leerá todos los registros existentes
while($row = mysql_fetch_array($result)) {
// $row es un array con todos los campos existentes en la tabla

echo "Nombre: ".$row['nombre']." ";
echo "Tipo: ".$row['tipo']." ";
echo "Region: ".$row['region']." ";
echo "Generacion: ".$row['generacion']."#";

} // fin del bucle de instrucciones
mysql_free_result($result); 
}// Liberamos los registros
mysql_close($link); // Cerramos la conexion con la base de datos
?>