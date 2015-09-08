package com.cako.project.lucene.index;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class SearcherUtil {

	private static Directory directory = null;
	private static IndexReader reader = null;
	
	static {
		try {
			if (directory == null) {
				directory = FSDirectory.open(new File(LuceneUtil.indexPath).toPath());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获得一个搜索对象
	 * 
	 * @return
	 */
	public static IndexSearcher getIndexSearcher() {
		try {
			if (reader == null) {
				reader = DirectoryReader.open(directory);
			} else {
				IndexReader tr = DirectoryReader.openIfChanged((DirectoryReader) reader);
				if (tr != null) {
					reader.close();
					reader = tr;
				}
			}
			return new IndexSearcher(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
