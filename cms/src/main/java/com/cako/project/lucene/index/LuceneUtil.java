package com.cako.project.lucene.index;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.cako.project.column.entity.News;

public class LuceneUtil {

	public static final String indexPath = "d:\\lucence\\index";
	private static List<String> newsIdStrings = new ArrayList<String>();
	private static IndexWriter indexWriter = null;

	private static DirectoryReader reader = null;

	/**
	 * 得到一个IndexWriter
	 * @return
	 * @throws IOException
	 */
	public static IndexWriter getIndexWriter() throws IOException {
		if (indexWriter == null) {
			Analyzer analyzer = new StandardAnalyzer();
			Directory directory = FSDirectory.open(new File(indexPath).toPath());
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			indexWriter = new IndexWriter(directory, config);
		}
		return indexWriter;
	}

	/**
	 * 得到一个 IndexReader对象
	 * @return
	 * @throws IOException
	 */
	public static IndexReader getIndexReader() throws IOException {
		if (reader == null) {
			Directory directory = FSDirectory.open(new File(indexPath).toPath());
			reader = DirectoryReader.open(directory);
		}
		return reader;
	}

	/**
	 * 创建索引
	 * @param newList
	 */
	public static void createIndex(List<News> newList) {
		IndexWriter iwriter = null;
		try {
			iwriter = LuceneUtil.getIndexWriter();
			Document doc = null;
			for (News news : newList) {
				doc = new Document();
				doc.add(new Field("id", news.getId(), TextField.TYPE_STORED));
				doc.add(new Field("content", news.getContent(), TextField.TYPE_NOT_STORED));
				doc.add(new Field("title", news.getTitle(), TextField.TYPE_NOT_STORED));
				doc.add(new Field("name", news.getColumn().getName(), TextField.TYPE_NOT_STORED));
				iwriter.addDocument(doc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询索引数据
	 * @param keyWork
	 */
	public static void query(String keyWork) {
		IndexReader reader = null;
		try {
			reader = LuceneUtil.getIndexReader();
			IndexSearcher searcher = new IndexSearcher(reader);
			String[] queries = { keyWork, keyWork };
			String[] fields = { "title", "content" };
			BooleanClause.Occur[] clauses = { BooleanClause.Occur.SHOULD, BooleanClause.Occur.SHOULD };
			Query query = MultiFieldQueryParser.parse(queries, fields, clauses, new StandardAnalyzer());
			TopDocs topDocs = searcher.search(query, 1000);
			ScoreDoc[] hits = topDocs.scoreDocs;
			System.out.println("共检索出 " + topDocs.totalHits + " 条记录");
			for (int i = 0; i < hits.length; i++) {
				Document hitDoc = searcher.doc(hits[i].doc);
				String id = hitDoc.get("id");
				float score = hits[i].score;
				newsIdStrings.add(id);
				System.out.println(String.format("id:%s,相关度:%s.", id, score));
			}
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

	public static final List<String> getStringIds() {
		return newsIdStrings;
	}

	public void delete() {
		try {
			Directory directory = FSDirectory.open(new File(indexPath).toPath());
			DirectoryReader reader = DirectoryReader.open(directory);
			Analyzer analyzer = new StandardAnalyzer();
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			IndexWriter writer = new IndexWriter(directory, config);
			Term terms = new Term("id", "1");
			writer.deleteDocuments(terms);
			writer.close();
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

	}
}
