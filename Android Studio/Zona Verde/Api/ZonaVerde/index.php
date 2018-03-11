<?php
require_once 'vendor/autoload.php';
$app=new \Slim\Slim();
$db=new mysqli("localhost","root","","Zona Verde");
mysqli_set_charset($db, "utf8");

$app->get("/estados",function () use($db,$app){
    $query=$db->query("SELECT * FROM estados;");
    $estados= array();
    while($fila=$query->fetch_assoc()){
       $estados[]=$fila;
    }
    echo json_encode($estados);
});
$app->run();