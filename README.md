# A demo that Vertx HA not working with zookeeper

## Vertx version
3.4.2

## How to reproduce

1. Bring up zookeeper servers
   ```sbtshell
   cd docker
   docker-compose -p demo up -d    #This will bring up zookeeper servers
   ```
2. Run `io.sgr.vertx.zkhafailure.DemoVerticle` twice.
3. Visit http://localhost:8000 to verify it's working.
3. Use jps to list all java processes.
4. Kill one of `DemoVerticle` process.
5. Visit http://localhost:8000 again.

## Expected
The site should be reachable.

## Actual
The site can't be reached.
