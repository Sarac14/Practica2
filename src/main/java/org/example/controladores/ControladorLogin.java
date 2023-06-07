package org.example.controladores;

import io.javalin.Javalin;
import org.example.Colecciones.Articulos;
import org.example.Colecciones.Usuario;
import org.example.servicios.ServicioArticulo;
import org.example.servicios.ServicioUsuario;
import org.example.util.BaseControlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.path;

public class ControladorLogin extends BaseControlador {
    private static ServicioUsuario servicio_usuario = ServicioUsuario.getInstancia();
    private static ServicioArticulo servicio_art = ServicioArticulo.getInstancia();

    public ControladorLogin(Javalin app){super (app);}

    public void aplicarRutas() {
        app.routes(()->{
            path("/inicio/", () -> {
            /*app.get("/", ctx ->{
                Map<String, Object> modelo = new HashMap<>();
                modelo.put("accion", isUserLoggedIn(ctx) ? "LOG OUT" : "LOG IN");
                ctx.render("/index.html", modelo);

            });*/


                app.get("/", ctx ->{

                    ctx.redirect("/inicio/listar");
                });

                app.get("/listar/", ctx ->{
                    List<Articulos> lista = servicio_art.getListaArticulos();
                    Articulos articulo = new Articulos();
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "Listado de Articulos");
                    modelo.put("lista", lista);
                    modelo.put("articulo", articulo);
                    ctx.render("/templates/index.html", modelo);

                });


                app.get("/login", ctx -> {
                    ctx.redirect("/login.html");
                });

                app.post("/login", ctx -> {
                    String username = ctx.formParam("username");
                    String password = ctx.formParam("password");

                    if (servicio_usuario.autenticarUsuario(username,password) != null) {
                        Usuario usuario = servicio_usuario.getUsuarioPorUsuario(username);
                        servicio_usuario.setUsuarioLogeado(usuario);
                        ctx.redirect("/");
                    } else {
                        ctx.html("Credenciales incorrectas. <a href='/login'>Volver a intentar</a>");
                    }
                });
            });
        });


        /*app.before("/", ctx -> {
            /*Map<String, Object> modelo = new HashMap<>();
            modelo.put("accion", isUserLoggedIn(ctx) ? "LOG OUT" : "LOG IN");
            ctx.render("/templates/index.html", modelo);*/

            /*try {
                Map<String, Object> modelo = new HashMap<>();
                modelo.put("accion", isUserLoggedIn(ctx) ? "LOG OUT" : "LOG IN");
                ctx.render("/templates/thymeleaf/index.html", modelo);
            } catch (Exception e) {
                // Manejo de la excepción, por ejemplo, imprimir información de depuración o mostrar una página de error
                e.printStackTrace();
                ctx.html("Error en la aplicación. Por favor, intenta de nuevo más tarde.");
            }
        });*/


    }


}
