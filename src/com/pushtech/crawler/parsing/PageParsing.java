package com.pushtech.crawler.parsing;

import static org.apache.http.protocol.HTTP.UTF_8;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.pushtech.crawler.beans.Page;

import liquibase.util.StreamUtil;

public class PageParsing implements ParsingTemplate {
   protected PageParsing() {

   }

   @Override
   public Page parse(Object entry, Object object, HashMap<String, String> parameters) {
      if ((object instanceof HttpResponse)) { // refa httpResponse n miditra d bean page n mivoaka

         int statusCode = 0;
         String url = null;
         String redirectionUrl = null;
         String content = null;
         Document doc = null;
         statusCode = ((HttpResponse) object).getStatusLine().getStatusCode();

         if (statusCode == 200) {
            try {
               content = getContent((HttpResponse) object);
            } catch (Exception e) {
               e.printStackTrace();
            }
            doc = getDocument(content);
            return new Page(statusCode, content, doc);
         }

         return null;

      }
      return null;
   }

   // TODO enlever le UTF-8
   private String getContent(HttpResponse response) throws UnsupportedEncodingException, IllegalStateException, IOException {
      Header contentEncodingHeader = response.getFirstHeader("Content-Encoding");
      if (contentEncodingHeader != null) {
         String encoding = contentEncodingHeader.getValue();
         if (encoding.contains("gzip")) {
            InputStreamReader reader = new InputStreamReader(new GzipDecompressingEntity(response.getEntity()).getContent(), UTF_8);
            String content = StreamUtil.getReaderContents(reader);
            return StringUtils.trim(content);
         }
      }
      return StringUtils.trim(EntityUtils.toString(response.getEntity(), UTF_8));
   }

   private Document getDocument(String content) {
      return Jsoup.parse(content);
   }

}
