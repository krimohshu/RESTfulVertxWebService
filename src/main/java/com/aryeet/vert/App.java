package com.aryeet.vert;

import com.sun.net.httpserver.HttpsServer;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class App {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        HttpServer httpsServer = vertx.createHttpServer();
        Router router = Router.router(vertx);

        Route handler1 = router
                .route("/hi")  // we can
                .handler(routingContext -> {
                    System.out.println("Come to hello");
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("Hi Aryeet");
                    routingContext
                            .vertx()
                            .setTimer(5000, tid -> routingContext.next());
                    //response.putHeader("content-type", "text/plan");
                    //response.end("Hi Aryeet");
                });

        Route handler2 = router
                .route("/hi")
                .handler(routingContext -> {
                    System.out.println("Come to hi");
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("Hi Aryeet from hi 2");
                    routingContext
                            .vertx()
                            .setTimer(5000, tid -> routingContext.next());
                    //response.putHeader("content-type", "text/plan");
                    //response.end("Hi Aryeet");
                });

        Route handler3 = router
                .route("/hi")
                .handler(routingContext -> {
                    System.out.println("Come to hi3");
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("Hi Aryeet from hi 3");
                   // response.putHeader("content-type", "text/plan");
                    response.end("Hi Aryeet - Ended");
                });


        Route handler4 = router
                .get("/hello")  // we can
                .handler(routingContext -> {
                    System.out.println("Come to hello");
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("Hi Aryeet from GET Api \n");

                    //response.putHeader("content-type", "text/plan");
                    response.end("Hi Aryeet");
                });

        Route handler5 = router
                .post("/hello")
                .handler(routingContext -> {
                    System.out.println("Come to hello  5");
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("Hi Aryeet from POST Api \n");
                    /*routingContext
                            .vertx()
                            .setTimer(5000, tid -> routingContext.next());*/
                    //response.putHeader("content-type", "text/plan");
                    response.end("Hi Aryeet");
                });

        //path param
        Route handler6 = router
                .get("/customer/:name")  // we can
                .handler(routingContext -> {
                    String name = routingContext.request().getParam("name");
                    System.out.println("Come to customer in Get customer api" + name);
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("GET Customer API : Hi : " + name +"\n");

                    //response.putHeader("content-type", "text/plan");
                    response.end("Hi Aryeet");
                });



        Route handler7 = router
                .post("/jsonpostdemo")
                .consumes("*/json")
                .handler(routingContext -> {
                    System.out.println("Come to hello  5");
                    HttpServerResponse response = routingContext.response();
                    response.setChunked(true);
                    response.write("Hi Aryeet from POST Api \n");
                    /*routingContext
                            .vertx()
                            .setTimer(5000, tid -> routingContext.next());*/
                    //response.putHeader("content-type", "text/plan");
                    response.end("Hi Aryeet");
                });


        httpsServer.requestHandler(router::accept).listen(8091);
    }
}
