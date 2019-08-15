package com.cloud.include.utils;

import lombok.extern.slf4j.Slf4j;
import org.frameworkset.elasticsearch.ElasticSearchException;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * es工具类
 */
@Component
@Slf4j
public class ESUtil {


	/**
	 * 验证索引
	 *
	 * @param indexName 待验证索引名称
	 * @return
	 */
	public boolean sendIndice(String indexName) {
		ClientInterface clientUtil = ESClient.restClient();
		boolean exist = clientUtil.existIndice(indexName);
		log.info("验证索引[" + indexName + "] exist[" + exist + "]");
		return exist;
	}

	/**
	 * 创建索引
	 *
	 * @param indexName       索引名称
	 * @param indexMapperName mapping名称,即配置文件里面名称
	 */
	public void createIndice(String indexName, String indexMapperName) {
		//创建加载配置文件的客户端工具，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient(indexName);
		try {
			//判读索引表indexName是否存在，存在返回true，不存在返回false
			boolean exist = clientUtil.existIndice(indexName);

			//如果索引表demo已经存在先删除mapping
			if (exist) {
				String rs = clientUtil.dropIndice(indexName);
				log.info("删除索引[" + indexName + "] 返回rs[" + rs + "]");
			}
			//创建索引表demo
			clientUtil.createIndiceMapping(indexName,//索引表名称
					indexMapperName);//索引表mapping dsl脚本名称，在esmapper/search.xml中定义indexMapperName

			String indice = clientUtil.getIndice(indexName);//获取最新建立的索引表结构
			log.info("创建索引[" + indexName + "] indice[" + indice + "]");
		} catch (ElasticSearchException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 删除索引
	 *
	 * @param indexName 待删除索引
	 */
	public void deleteIndice(String indexName) {
		ClientInterface clientUtil = ESClient.restClient();
		String rs = clientUtil.dropIndice(indexName);
		log.info("删除索引[" + indexName + "] 返回rs[" + rs + "]");
	}

	/**
	 * 添加或修改文档
	 */
	public String addOrUpdateDocument(String index,String type,Object obj){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		//向固定index添加或者修改文档,如果id已经存在做修改操作，否则做添加文档操作，返回处理结果
		//索引表 索引类型
		return clientUtil.addDocument(index,type,obj);
	}

	/**
	 * 批量添加或修改文档
	 */
	public <T> void addOrUpdateDocuments(String index,String type,List<T> list){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		//向固定index添加或者修改文档,id，否则做添加文档操作，返回处理结果
		//索引表 索引类型
		clientUtil.addDocuments(index,type,list);
	}
	/**
	 * 获取文档
	 */
	public String getDocumentById(String index,String id){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		//索引表 索引类型 id
		String res = clientUtil.getDocument(index,index,id);
		return res;
	}
	/**
	 * 获取文档
	 */
	public <T> T getDocumentById(String index,String id,Class<T> t){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		//索引表 索引类型 id 对象
		return clientUtil.getDocument(index,index,id,t);
	}
	/**
	 * 删除文档
	 */
	public void deleteDocumentById(String index,String id){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		//索引表 索引类型 id 对象
		clientUtil.deleteDocument(index,index,id);
	}
	/**
	 * 删除文档
	 */
	public void deleteDocumentByIds(String index,String[] ids){
		//获取文档的客户端对象，单实例多线程安全
		ClientInterface clientUtil = ESClient.restClient();
		StringBuilder builder = new StringBuilder();
		String[] var5 = ids;
		for(int i = 0; i < ids.length; ++i) {
			String id = ids[i];
			builder.append("{ \"delete\" : { \"_index\" : \"").append(index).append("\", \"_type\" : \"").append(index).append("\", \"_id\" : \"").append(id).append("\" } }\n");
		}
		//索引表 索引类型 id 对象
		clientUtil.executeHttp("_bulk",builder.toString(),"post");
	}

	/**
	 * 通过mapper文件执行查询语句
	 * @param index 索引
	 * @param params 传递的参数
	 * @param dsl 对应mapper文件的name
	 */
	public <T> List<T> search(String index, String type,String dsl, Map<String, Object> params, Class<T> clazz) {
		//获取索引对应的客户端
		ClientInterface clientUtil = ESClient.restClient(index);
		//设定查询条件,通过map传递变量参数值,key对于dsl中的变量名称
		//执行查询，index为索引表，_search为检索操作action
		//使用反射获取Class<T>
		ESDatas<T> esDatas = esDatas = clientUtil.searchList(index + "/" + type + "/_search",//indexName为索引表，_search为检索操作action
				dsl,//esmapper/search.xml中定义的dsl语句
				params,//变量参数
				clazz);//返回的文档封装对象类型
		//获取结果对象列表，最多返回1000条记录
		List<T> list = esDatas.getDatas();
		//获取总记录数
		long totalSize = esDatas.getTotalSize();
		return list;
	}

	public <T> List<T> searchAll(String index, String type,String dsl, Map<String, Object> params, Class<T> clazz) {
		//获取索引对应的客户端
		ClientInterface clientUtil = ESClient.restClient(index);
		//设定查询条件,通过map传递变量参数值,key对于dsl中的变量名称
		//执行查询，index为索引表，_search为检索操作action
		//使用反射获取Class<T>
		ESDatas<T> esDatas = esDatas = clientUtil.searchAll(index ,clazz);
		//获取结果对象列表，最多返回1000条记录
		List<T> list = esDatas.getDatas();
		//获取总记录数
		long totalSize = esDatas.getTotalSize();
		return list;
	}
}
