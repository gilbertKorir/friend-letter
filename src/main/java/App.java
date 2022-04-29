import java.util.HashMap;

import spark.ModelAndView;

import spark.template.handlebars.HandlebarsTemplateEngine;

import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
//    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (request, response) -> {

            return new ModelAndView(new HashMap(), "hello.hbs");
        }, new HandlebarsTemplateEngine());
        get("/favorite_photos", (request, response) -> {
            return new ModelAndView(new HashMap(), "favorite_photos.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
