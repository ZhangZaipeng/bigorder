import java.util.Map;
import java.util.concurrent.TimeUnit;
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
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.ShardSearchFailure;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Description ：
 * @Tauthor ZhangZaipeng
 * @Tdata 2020/10/28   21:32
 */
public class EsSearchTest {

  private static Logger logger = LoggerFactory.getLogger(EsSearchTest.class);

  @Test
  public void createIndexDemo() throws Exception {
    RestHighLevelClient client = new RestHighLevelClient(
        RestClient.builder(new HttpHost("192.172.1.222", 9200, "http")));

    // 1、创建search请求
    //SearchRequest searchRequest = new SearchRequest();
    SearchRequest searchRequest = new SearchRequest("tb_movie");
    searchRequest.types("tb_movie");

    // 2、用SearchSourceBuilder来构造查询请求体 ,请仔细查看它的方法，构造各种查询的方法都在这。
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

    // .termQuery("type", "爱情")
    sourceBuilder.query(QueryBuilders.matchQuery("author","刘德华"));
    sourceBuilder.from(0);
    sourceBuilder.size(10);
    sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

    //将请求体加入到请求中
    searchRequest.source(sourceBuilder);

    //3、发送请求
    SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);

    //4、处理响应
    //搜索结果状态信息
    RestStatus status = searchResponse.status();
    TimeValue took = searchResponse.getTook();
    Boolean terminatedEarly = searchResponse.isTerminatedEarly();
    boolean timedOut = searchResponse.isTimedOut();

    //分片搜索情况
    int totalShards = searchResponse.getTotalShards();
    int successfulShards = searchResponse.getSuccessfulShards();
    int failedShards = searchResponse.getFailedShards();
    for (ShardSearchFailure failure : searchResponse.getShardFailures()) {
      // failures should be handled here
    }

    //处理搜索命中文档结果
    SearchHits hits = searchResponse.getHits();

    long totalHits = hits.getTotalHits();
    float maxScore = hits.getMaxScore();

    SearchHit[] searchHits = hits.getHits();
    for (SearchHit hit : searchHits) {
      // do something with the SearchHit

      String index = hit.getIndex();
      String type = hit.getType();
      String id = hit.getId();
      float score = hit.getScore();

      //取_source字段值
      String sourceAsString = hit.getSourceAsString(); //取成json串
      Map<String, Object> sourceAsMap = hit.getSourceAsMap(); // 取成map对象
      //从map中取字段值
				/*
				String documentTitle = (String) sourceAsMap.get("title");
				List<Object> users = (List<Object>) sourceAsMap.get("user");
				Map<String, Object> innerObject = (Map<String, Object>) sourceAsMap.get("innerObject");
				*/
      logger.info("index:" + index + "  type:" + type + "  id:" + id);
      logger.info(sourceAsString);

      //取高亮结果
				/*Map<String, HighlightField> highlightFields = hit.getHighlightFields();
			    HighlightField highlight = highlightFields.get("title");
			    Text[] fragments = highlight.fragments();
			    String fragmentString = fragments[0].string();*/
    }

    // 获取聚合结果
			/*
			Aggregations aggregations = searchResponse.getAggregations();
			Terms byCompanyAggregation = aggregations.get("by_company");
			Bucket elasticBucket = byCompanyAggregation.getBucketByKey("Elastic");
			Avg averageAge = elasticBucket.getAggregations().get("average_age");
			double avg = averageAge.getValue();
			*/

    // 获取建议结果
			/*Suggest suggest = searchResponse.getSuggest();
			TermSuggestion termSuggestion = suggest.getSuggestion("suggest_user");
			for (TermSuggestion.Entry entry : termSuggestion.getEntries()) {
			    for (TermSuggestion.Entry.Option option : entry) {
			        String suggestText = option.getText().string();
			    }
			}
			*/

    //异步方式发送获查询请求
			/*
			ActionListener<SearchResponse> listener = new ActionListener<SearchResponse>() {
			    @Override
			    public void onResponse(SearchResponse getResponse) {
			        //结果获取
			    }

			    @Override
			    public void onFailure(Exception e) {
			        //失败处理
			    }
			};
			client.searchAsync(searchRequest, listener);
			*/

  }

}
