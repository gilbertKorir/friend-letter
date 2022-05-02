import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;
import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 8800; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "hello.hbs");
        }, new HandlebarsTemplateEngine());
        get("/favorite_photos", (request, response) -> {
            Map<String,Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "favorite_photos.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
