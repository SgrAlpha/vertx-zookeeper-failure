package io.sgr.vertx.zkhafailure;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.zookeeper.ZookeeperClusterManager;

import java.lang.management.ManagementFactory;

public class DemoVerticle extends AbstractVerticle {

    public static void main(String... args) {
        ClusterManager mgr = new ZookeeperClusterManager();
        VertxOptions options = new VertxOptions().setClusterManager(mgr).setHAEnabled(Boolean.TRUE);
        Vertx.clusteredVertx(options, vertx -> {
            vertx.result().deployVerticle(DemoVerticle.class.getName(), new DeploymentOptions().setHa(Boolean.TRUE));
        });
    }


    @Override public void start() throws Exception {
        vertx.createHttpServer().requestHandler(req -> {
            req.response().end(String.format("Request served by %s", ManagementFactory.getRuntimeMXBean().getName()));
        }).listen(8000);
    }

}
