package ru.mydesignstudio.javalin.start;

import io.javalin.Javalin;

public class JavalinStartApplication {
    public static void main(String[] args) {
        final Javalin app = Javalin.create().start(7000);
        app.get("/", ctx -> {
            ctx.html("Hello, world!");
        });
        app.get("/person", ctx -> {
            final Model model = Model.builder()
                .id(10L)
                .firstName("First Name")
                .lastName("Last Name")
                .build();
            ctx.json(model);
        });
    }
}
