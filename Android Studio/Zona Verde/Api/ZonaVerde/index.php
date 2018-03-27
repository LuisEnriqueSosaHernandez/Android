<?php
require_once 'vendor/autoload.php';
$app=new \Slim\Slim();
$db=new mysqli("localhost","root","","Zona Verde");
mysqli_set_charset($db, "utf8");

$app->get("/Estados",function () use($db,$app){
    $query=$db->query("SELECT * FROM Estados;");
    $estados= array();
    while($fila=$query->fetch_assoc()){
       $estados[]=$fila;
    }
    echo json_encode($estados);
});

$app->get("/Municipios/:IdEstado",function ($IdEstado) use($db,$app){
    $query=$db->query("SELECT * FROM Municipios WHERE IdEstado=".$IdEstado.";");
    $municipios= array();
    while($fila=$query->fetch_assoc()){
       $municipios[]=$fila;
    }
    echo json_encode($municipios);
});

$app->run();