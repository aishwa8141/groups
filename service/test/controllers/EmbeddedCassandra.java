package controllers;

import com.datastax.driver.core.BoundStatement;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import java.io.IOException;
import java.net.URL;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.CQLDataLoader;
import org.cassandraunit.dataset.cql.ClassPathCQLDataSet;
import org.cassandraunit.dataset.cql.FileCQLDataSet;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import org.sunbird.util.JsonKey;
import play.Application;
import play.test.Helpers;

public class EmbeddedCassandra {
  private static Boolean dbStarted = false;
  static final String KEYSPACE = "sunbird";
  static final String GROUP_TABLE = "group";
  static Session session;
  static BoundStatement insertStatement;
  static BoundStatement selectStatement;
  private static CQLDataLoader dataLoader;
  private static EmbeddedCassandra thisObj;
  // One and only app
  private static Application app;

  private EmbeddedCassandra() {
    try {
      System.setProperty("cassandra.native_transport_port", "9142");
      System.setProperty("sunbird_cassandra_port", "9142");

      String folderPath = System.getProperty("user.dir");
      String fileSeparator = System.getProperty("file.separator");
      String path =
          folderPath
              + fileSeparator
              + "target"
              + fileSeparator
              + "httpconnector"
              + fileSeparator
              + "embeddedCassandra";
      URL url = EmbeddedCassandra.class.getResource("/cu-cassandra.yaml");
      System.setProperty("cassandra.config", url.toString());

      EmbeddedCassandraServerHelper.startEmbeddedCassandra("cu-cassandra.yaml", path, 100000L);
      session = EmbeddedCassandraServerHelper.getSession();
      dataLoader = new CQLDataLoader(session);
      dataLoader.load(new ClassPathCQLDataSet("createGroup.cql", KEYSPACE));
      System.out.println("Group-service started embedded cassandra successfully");

      app = Helpers.fakeApplication();
    } catch (IOException | TTransportException e) {
      System.out.println("ERROR - starting up Embedded cassandra");
      e.printStackTrace();
    }
  }

  public static EmbeddedCassandra getInstance() {
    if (!EmbeddedCassandra.dbStarted) {
      synchronized (EmbeddedCassandra.dbStarted) {
        if (!EmbeddedCassandra.dbStarted) {
          thisObj = new EmbeddedCassandra();
          dbStarted = true;
        }
      }
    }
    return thisObj;
  }

  public void start() {
    if (!EmbeddedCassandra.dbStarted) {
      throw new RuntimeException("Embedded cassandra is down!...");
    }
  }

  public Application getApp() {
    return app;
  }

  public void loadData(String cqlFile) {
    dataLoader.load(new FileCQLDataSet(cqlFile, false, false));
  }

  public static void createStatements() {

    // create prepared statements
    PreparedStatement insertGroupQuery =
        session.prepare(
            QueryBuilder.insertInto(KEYSPACE, GROUP_TABLE)
                .value(JsonKey.ID, QueryBuilder.bindMarker())
                .value(JsonKey.GROUP_NAME, QueryBuilder.bindMarker())
                .value(JsonKey.GROUP_DESC, QueryBuilder.bindMarker()));

    // link prepared statements to boundstatements
    PreparedStatement selectGroupQuery =
        session.prepare(QueryBuilder.select().all().from(KEYSPACE, GROUP_TABLE));

    // link prepared statements to boundstatements
    insertStatement = new BoundStatement(insertGroupQuery);
    selectStatement = new BoundStatement(selectGroupQuery);
  }

  public static void close() throws Exception {
    EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
  }
}
