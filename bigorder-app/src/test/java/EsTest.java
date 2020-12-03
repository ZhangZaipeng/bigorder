import java.util.Map;
import org.apache.http.HttpHost;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description ：
 * @Tauthor ZhangZaipeng
 * @Tdata 2020/10/28   21:32
 */
public class EsTest {

  private static Logger logger = LoggerFactory.getLogger(EsTest.class);

  @Test
  public void createIndexDemo() throws Exception {
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(new HttpHost("192.172.1.222", 9200, "http")));

    // 1、创建 创建索引request
    CreateIndexRequest request = new CreateIndexRequest("twitter");

    // 2、设置索引的settings
    request.settings(
        Settings.builder()
            .put("index.number_of_shards", 3) // 分片数
            .put("index.number_of_replicas", 2) // 副本数
        //.put("analysis.analyzer.default.tokenizer", "ik_smart") // 默认分词器
    );

    // 3、设置索引的mappings
    request.mapping("_doc",
        "  {\n" +
            "    \"_doc\": {\n" +
            "      \"properties\": {\n" +
            "        \"message\": {\n" +
            "          \"type\": \"text\"\n" +
            "        }\n" +
            "      }\n" +
            "    }\n" +
            "  }",
        XContentType.JSON);

    // 4、 设置索引的别名
    request.alias(new Alias("mmm"));

    // 5、 发送请求
    // 5.1 同步方式发送请求
    CreateIndexResponse createIndexResponse = client.indices()
        .create(request, RequestOptions.DEFAULT);

    // 6、处理响应
    boolean acknowledged = createIndexResponse.isAcknowledged();
    System.out.println("acknowledged = " + acknowledged);

    // 5.1 异步方式发送请求
			/*ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
				@Override
				public void onResponse(
						CreateIndexResponse createIndexResponse) {
					// 6、处理响应
					boolean acknowledged = createIndexResponse.isAcknowledged();
					boolean shardsAcknowledged = createIndexResponse
							.isShardsAcknowledged();
					System.out.println("acknowledged = " + acknowledged);
					System.out.println(
							"shardsAcknowledged = " + shardsAcknowledged);
				}

				@Override
				public void onFailure(Exception e) {
					System.out.println("创建索引异常：" + e.getMessage());
				}
			};

			client.indices().createAsync(request, listener);
			*/
  }

  @Test
  public void indexDocDemo() throws Exception {
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(new HttpHost("192.172.1.222", 9200, "http")));

    // 1、创建索引请求
    IndexRequest request = new IndexRequest("posts", "_doc", "1");

    // 2、准备文档数据
    // 方式一：直接给JSON串
    String jsonString = "{" +
        "\"user\":\"kimchy\"," +
        "\"postDate\":\"2013-01-30\"," +
        "\"message\":\"trying out Elasticsearch\"" +
        "}";
    request.source(jsonString, XContentType.JSON);

    // 方式二：以map对象来表示文档
			/*
			Map<String, Object> jsonMap = new HashMap<>();
			jsonMap.put("user", "kimchy");
			jsonMap.put("postDate", new Date());
			jsonMap.put("message", "trying out Elasticsearch");
			request.source(jsonMap);
			*/

    // 方式三：用XContentBuilder来构建文档
			/*
			XContentBuilder builder = XContentFactory.jsonBuilder();
			builder.startObject();
			{
			    builder.field("user", "kimchy");
			    builder.field("postDate", new Date());
			    builder.field("message", "trying out Elasticsearch");
			}
			builder.endObject();
			request.source(builder);
			*/

    // 方式四：直接用key-value对给出
			/*
			request.source("user", "kimchy",
			                "postDate", new Date(),
			                "message", "trying out Elasticsearch");
			*/

    //3、其他的一些可选设置
			/*
			request.routing("routing");  //设置routing值
			request.timeout(TimeValue.timeValueSeconds(1));  //设置主分片等待时长
			request.setRefreshPolicy("wait_for");  //设置重刷新策略
			request.version(2);  //设置版本号
			request.opType(DocWriteRequest.OpType.CREATE);  //操作类别
			*/

    //4、发送请求
    IndexResponse indexResponse = null;
    try {
      // 同步方式
      indexResponse = client.index(request, RequestOptions.DEFAULT);
    } catch(ElasticsearchException e) {
      // 捕获，并处理异常
      //判断是否版本冲突、create但文档已存在冲突
      if (e.status() == RestStatus.CONFLICT) {
        logger.error("冲突了，请在此写冲突处理逻辑！\n" + e.getDetailedMessage());
      }

      logger.error("索引异常", e);
    }

    //5、处理响应
    if(indexResponse != null) {
      String index = indexResponse.getIndex();
      String type = indexResponse.getType();
      String id = indexResponse.getId();
      long version = indexResponse.getVersion();
      if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
        System.out.println("新增文档成功，处理逻辑代码写到这里。");
      } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
        System.out.println("修改文档成功，处理逻辑代码写到这里。");
      }
      // 分片处理信息
      ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
      if (shardInfo.getTotal() != shardInfo.getSuccessful()) {

      }
      // 如果有分片副本失败，可以获得失败原因信息
      if (shardInfo.getFailed() > 0) {
        for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
          String reason = failure.reason();
          System.out.println("副本失败原因：" + reason);
        }
      }
    }


    //异步方式发送索引请求
    /*ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>() {
        @Override
        public void onResponse(IndexResponse indexResponse) {

        }

        @Override
        public void onFailure(Exception e) {

        }
    };
    client.indexAsync(request, listener);
    */
  }

  @Test
  public void getDocDemo() throws Exception {
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(new HttpHost("192.172.1.222", 9200, "http")));

    // 1、创建获取文档请求
    GetRequest request = new GetRequest("posts", "_doc", "1");

    // 2、可选的设置
    //request.routing("routing");
    //request.version(2);

    //request.fetchSourceContext(new FetchSourceContext(false)); //是否获取_source字段
    //选择返回的字段
    String[] includes = new String[]{"message", "*Date"};
    String[] excludes = Strings.EMPTY_ARRAY;
    FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
    request.fetchSourceContext(fetchSourceContext);

    //也可写成这样
			/*String[] includes = Strings.EMPTY_ARRAY;
			String[] excludes = new String[]{"message"};
			FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
			request.fetchSourceContext(fetchSourceContext);*/

    // 取stored字段
			/*request.storedFields("message");
			GetResponse getResponse = client.get(request);
			String message = getResponse.getField("message").getValue();*/

    //3、发送请求
    GetResponse getResponse = null;
    try {
      // 同步请求
      getResponse = client.get(request, RequestOptions.DEFAULT);
    } catch (ElasticsearchException e) {
      if (e.status() == RestStatus.NOT_FOUND) {
        logger.error("没有找到该id的文档");
      }
      if (e.status() == RestStatus.CONFLICT) {
        logger.error("获取时版本冲突了，请在此写冲突处理逻辑！");
      }
      logger.error("获取文档异常", e);
    }

    //4、处理响应
    if (getResponse != null) {
      String index = getResponse.getIndex();
      String type = getResponse.getType();
      String id = getResponse.getId();
      if (getResponse.isExists()) { // 文档存在
        long version = getResponse.getVersion();
        String sourceAsString = getResponse.getSourceAsString(); //结果取成 String
        Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();  // 结果取成Map
        byte[] sourceAsBytes = getResponse.getSourceAsBytes();    //结果取成字节数组

        logger.info("index:" + index + "  type:" + type + "  id:" + id);
        logger.info(sourceAsString);

      } else {
        logger.error("没有找到该id的文档");
      }
    }

    //异步方式发送获取文档请求
			/*
			ActionListener<GetResponse> listener = new ActionListener<GetResponse>() {
			    @Override
			    public void onResponse(GetResponse getResponse) {

			    }

			    @Override
			    public void onFailure(Exception e) {

			    }
			};
			client.getAsync(request, listener);
			*/
  }

  @Test
  public void bulkDemo() throws Exception{
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(new HttpHost("192.172.1.222", 9200, "http")));


    // 1、创建批量操作请求
    BulkRequest request = new BulkRequest();
    request.add(new IndexRequest("mess", "_doc", "1")
        .source(XContentType.JSON,"field", "foo"));
    request.add(new IndexRequest("mess", "_doc", "2")
        .source(XContentType.JSON,"field", "bar"));
    request.add(new IndexRequest("mess", "_doc", "3")
        .source(XContentType.JSON,"field", "baz"));

			/*
			request.add(new DeleteRequest("mess", "_doc", "3"));
			request.add(new UpdateRequest("mess", "_doc", "2")
			        .doc(XContentType.JSON,"other", "test"));
			request.add(new IndexRequest("mess", "_doc", "4")
			        .source(XContentType.JSON,"field", "baz"));
			*/

    // 2、可选的设置
			/*
			request.timeout("2m");
			request.setRefreshPolicy("wait_for");
			request.waitForActiveShards(2);
			*/


    //3、发送请求

    // 同步请求
    BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);

    //4、处理响应
    if(bulkResponse != null) {
      for (BulkItemResponse bulkItemResponse : bulkResponse) {
        DocWriteResponse itemResponse = bulkItemResponse.getResponse();

        if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
            || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
          IndexResponse indexResponse = (IndexResponse) itemResponse;
          //TODO 新增成功的处理

        } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
          UpdateResponse updateResponse = (UpdateResponse) itemResponse;
          //TODO 修改成功的处理

        } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
          DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
          //TODO 删除成功的处理
        }
      }
    }
  }

}
