package org.example;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import org.example.controladores.ControladorLogin;
import org.example.controladores.PlantillasControlador;

public class Main {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config ->{
            //configurando los documentos estaticos.
            config.staticFiles.add(staticFileConfig -> {
                staticFileConfig.hostedPath = "/";
                staticFileConfig.directory = "/publico";
                staticFileConfig.location = Location.CLASSPATH;
                staticFileConfig.precompress=false;
                staticFileConfig.aliasCheck=null;
            });

        });
        app.start(8000);


        new ControladorLogin(app).aplicarRutas();
        new PlantillasControlador(app).aplicarRutas();

    }
}