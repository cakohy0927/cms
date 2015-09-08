package com.cako.project.lucene.index;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class IndexUtils {

	/**
	 * @param indexPath
	 *            索引文件存放的路径
	 * @param filePath
	 *            目标文件存放的路径
	 */
	public void createIndex(String indexPath, String filePath) {
		IndexWriter iwriter = null;
		try {
			Analyzer analyzer = new StandardAnalyzer();
			Directory directory = FSDirectory.open(new File(indexPath).toPath());
			IndexWriterConfig config = new IndexWriterConfig(analyzer);
			iwriter = new IndexWriter(directory, config);
			Document doc = null;
			File f = new File(filePath);
			for (File file : f.listFiles()) {
				doc = new Document();
				doc.add(new TextField("content", new FileReader(file)));
				doc.add(new StringField("filename", file.getName(), TextField.Store.YES));
				doc.add(new TextField("path", file.getAbsolutePath(), TextField.Store.YES));
				iwriter.addDocument(doc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (iwriter != null) {
					iwriter.close();
				}
				iwriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
