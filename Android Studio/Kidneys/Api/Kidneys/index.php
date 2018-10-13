<?php

require_once 'vendor/autoload.php';
$app = new \Slim\Slim();
$db = new mysqli("localhost", "u534973267_kidne", "P4ssw0rd", "u534973267_kidne");
if ($db->connect_error) {
    die('Error de conexión: ' . $db->connect_error);
}
mysqli_set_charset($db, "utf8");
$app->post("/CreateUser", function()use($db, $app) {
    $querycheck = "SELECT email FROM users WHERE email='" . $app->request->post("email") . "'";
    $resultcheck = $db->query($querycheck);
    if ($resultcheck->num_rows === 0) {
        $query = "INSERT INTO users VALUES("
                . "'{$app->request->post("email")}',"
                . "SHA('{$app->request->post("password")}'),"
                . "'{$app->request->post("name")}',"
                . "'{$app->request->post("gender")}',"
                . "'{$app->request->post("image")}',"
                . "'{$app->request->post("dateofbirth")}',"
                . "{$app->request->post("weight")},"
                . "{$app->request->post("height")},"
                . "'{$app->request->post("datecatheter")}',"
                . "{$app->request->post("typeofsolution")},"
                . "{$app->request->post("imc")},"
                . "'{$app->request->post("typeoftreatment")}',"
                . "'{$app->request->post("emergencycontact")}')";
        $insert = $db->query($query);
        //echo mysqli_error($db);
        if ($insert) {
            $result = array("STATUS" => "true", "message" => "Registro insertado");
        } else {
            $result = array("STATUS" => "false", "message" => "Registro no insertado");
        }
    } else {
        $result = array("STATUS" => "already", "message" => "Registro ya insertado");
    }
    echo json_encode($result);
});
$app->post("/ValidateUser", function()use($db, $app) {
    $querycheck = "SELECT email FROM users WHERE email='" . $app->request->post("email") . "' AND password=SHA('" . $app->request->post("password") . "')";
    $resultcheck = $db->query($querycheck);
    if ($resultcheck->num_rows === 1) {
        $result = array("STATUS" => "true", "message" => "Registro encontrado");
    } else {
        $result = array("STATUS" => "false", "message" => "Registro no encontrado");
    }
    echo json_encode($result);
});
$app->get("/User/:Email", function($email)use($db, $app) {
    $query = "SELECT email,name,gender,image,dateofbirth,weight,height,datecatheter,typeofsolution,imc,typeoftreatment,emergencycontact"
            . " FROM users WHERE email='" . $email . "'";
    $select = $db->query($query);
    if ($select) {
        echo json_encode($select->fetch_assoc());
    } else {
        
    }
});
$app->get("/Categories", function()use($db, $app) {
    $query = "SELECT *FROM categories ORDER BY name ASC";
    $select = $db->query($query);
    if ($select) {
        while ($fila = $select->fetch_assoc()) {
            $categories[] = $fila;
        }
        echo json_encode(array('categories' => $categories));
    } else {
        
    }
});
$app->get("/HealthRanges", function()use($db, $app) {
    $query = "SELECT *FROM healthrange ORDER BY name ASC";
    $select = $db->query($query);
    if ($select) {
        while ($fila = $select->fetch_assoc()) {
            $healthrange[] = $fila;
        }
        echo json_encode(array('healthranges' => $healthrange));
    } else {
        
    }
});
$app->get("/Foods", function()use($db, $app) {
    $query = "SELECT *FROM foods ORDER BY name ASC";
    $select = $db->query($query);
    if ($select) {
        while ($fila = $select->fetch_assoc()) {
            $foods[] = $fila;
        }
        echo json_encode(array('foods' => $foods));
    } else {
        
    }
});
$app->get("/Recipes", function()use($db, $app) {
    $query = "SELECT *FROM recipes ORDER BY title ASC";
    $select = $db->query($query);
    if ($select) {
        while ($fila = $select->fetch_assoc()) {
            $recipes[] = $fila;
        }
        echo json_encode(array('recipes' => $recipes));
    } else {
        
    }
});
$app->get("/Follows/:Email", function($email)use($db, $app) {
    $query = "SELECT *FROM follows WHERE email='" . $email . "' ORDER BY date DESC";
    $select = $db->query($query);
    if ($select) {
        while ($fila = $select->fetch_assoc()) {
            $follows[] = $fila;
        }
        echo json_encode(array('follows' => $follows));
    } else {
        
    }
});
$app->post("/CreateFollow", function()use($db, $app) {

    $query = "INSERT INTO follows VALUES("
            . "null,"
            . "'{$app->request->post("date")}',"
            . "'{$app->request->post("email")}',"
            . "'{$app->request->post("idhealthrange")}')";
    $insert = $db->query($query);
    if ($insert) {
        $result = array("STATUS" => "true", "message" => "Registro insertado");
    } else {
        $result = array("STATUS" => "false", "message" => "Registro no insertado");
    }
    echo json_encode($result);
});

$app->post("/CreateFollowDay", function()use($db, $app) {

    $query = "INSERT INTO followsday VALUES("
            . "null,"
            . "'{$app->request->post("typeofsolution")}',"
            . "'{$app->request->post("start")}',"
            . "'{$app->request->post("end")}',"
            . "'{$app->request->post("drainage")}',"
            . "'{$app->request->post("uf")}',"
            . "'{$app->request->post("ingestedliquid")}',"
           . "'{$app->request->post("idfollow")}',"
            . "'{$app->request->post("email")}')";
    $insert = $db->query($query);
    if ($insert) {
        $result = array("STATUS" => "true", "message" => "Registro insertado");
    } else {
        $result = array("STATUS" => "false", "message" => "Registro no insertado");
    }
    echo json_encode($result);
});
$app->get("/FollowsDay/:Email", function($email)use($db, $app) {
    $query = "SELECT *FROM followsday WHERE email='" . $email . "' ORDER BY start DESC";
    $select = $db->query($query);
    if ($select) {
        while ($fila = $select->fetch_assoc()) {
            $followsday[] = $fila;
        }
        echo json_encode(array('followsday' => $followsday));
    } else {
        
    }
});

$app->delete("/DeleteFollowDay/:IdFollowsDay", function($idfollowday)use($db, $app) {
    $query = "DELETE FROM followsday WHERE idfollowday=" . $idfollowday;
    $delete = $db->query($query);
    if ($delete) {
        $result = array("STATUS" => "true", "message" => "Registro eliminado");
    } else {
        $result = array("STATUS" => "false", "message" => "Registro no eliminado");
    }
    echo json_encode($result);
});

$app->delete("/DeleteFollow/:IdFollow", function($idfollow)use($db, $app) {
    $query = "DELETE FROM follows WHERE idfollow=" . $idfollow;
    $delete = $db->query($query);
    if ($delete) {
        $result = array("STATUS" => "true", "message" => "Registro eliminado");
    } else {
        $result = array("STATUS" => "false", "message" => "Registro no eliminado");
    }
    echo json_encode($result);
});
$app->get("/Questions", function()use($db, $app) {
    $query = "SELECT *FROM questions ORDER BY question DESC";
    $select = $db->query($query);
    if ($select) {
        while ($fila = $select->fetch_assoc()) {
            $questions[] = $fila;
        }
        echo json_encode(array('questions' => $questions));
    } else {
        
    }
});
$app->get("/Locations", function()use($db, $app) {
    $query = "SELECT *FROM locations ORDER BY name ASC";
    $select = $db->query($query);
    if ($select) {
        while ($fila = $select->fetch_assoc()) {
            $locations[] = $fila;
        }
        echo json_encode(array('locations' => $locations));
    } else {
        
    }
});
$app->get("/Guides", function()use($db, $app) {
    $query = "SELECT *FROM guides ORDER BY name ASC";
    $select = $db->query($query);
    if ($select) {
        while ($fila = $select->fetch_assoc()) {
            $guides[] = $fila;
        }
        echo json_encode(array('guides' => $guides));
    } else {
        
    }
});
$app->put("/UpdatePassword/:Email", function($email)use($db, $app) {
    $querycheck = "SELECT email FROM users WHERE email='" . $email . "' AND password=SHA('" . $app->request->post("password") . "')";
    $resultcheck = $db->query($querycheck);
    if ($resultcheck->num_rows === 1) {
        $query = "UPDATE users SET password=SHA('" . $app->request->post("newpassword") . "') WHERE email='" . $email . "'";
        $put = $db->query($query);
        if ($put) {
            $result = array("STATUS" => "true", "message" => "Registro actualizado");
        } else {
            $result = array("STATUS" => "false", "message" => "Registro no actualizado");
        }
    } else {
        $result = array("STATUS" => "incorrect", "message" => "Contraseña incorrecta");
    }
    echo json_encode($result);
});
$app->put("/UpdateProfile/:Email", function($email)use($db, $app) {
    $querycheck = "SELECT email FROM users WHERE email='" . $email . "'";
    $resultcheck = $db->query($querycheck);
    if ($resultcheck->num_rows === 1) {
        $query = "UPDATE users SET name='" . $app->request->post("name") . "',gender='" . $app->request->post("gender") . "'"
                . ",dateofbirth='" . $app->request->post("dateofbirth") . "',weight=" . $app->request->post("weight") . ""
                . ",height=" . $app->request->post("height") . ",datecatheter='" . $app->request->post("datecatheter") . "'"
                . ",typeofsolution=" . $app->request->post("typeofsolution") . "" . ",imc=" . $app->request->post("imc") . ""
                . ",typeoftreatment='" . $app->request->post("typeoftreatment") . "'" 
                . ",emergencycontact='" . $app->request->post("emergencycontact") . "'" ." WHERE email='" . $email . "'";
        $put = $db->query($query);
        if ($put) {
            $result = array("STATUS" => "true", "message" => "Registro actualizado");
        } else {
            $result = array("STATUS" => "false", "message" => "Registro no actualizado");
        }
    } else {
        $result = array("STATUS" => "incorrect", "message" => "El usuario no existe");
    }

    echo json_encode($result);
});
$app->run();


