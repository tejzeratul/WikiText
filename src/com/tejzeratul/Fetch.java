package com.tejzeratul;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/*
 * Fetch.java
 *
 * The class contain methods to get contents from Wikipedia
 *
 * @author Tejas Padliya
 *
 */

public class Fetch {

    /**
     * returns first paragraph as text from Wikipedia, if the article for the topic exist
     *
     * @param  title
     *         name of topic to find first paragraph, if page exist
     *
     */
    public String getFirstWikiPara(String title) {

        String firstPara=Setting.MSG_CONTENT_UNAVAILABLE;
        boolean fetchStatus=true;

        if(title != null) {

            title = title.trim();
            if (!title.isEmpty()) {
                title = title.replaceAll(" ", "_");
                String currentTopicUrl;
                Document doc = null;

                currentTopicUrl = Setting.WIKI_URL + title;

                try {
                    doc = Jsoup.connect(currentTopicUrl).timeout(100000)
                            .userAgent("Mozilla")
                            .ignoreHttpErrors(true)
                            .get();
                } catch (IOException e) {
                    // e.printStackTrace();
                    fetchStatus = false;
                }

                if (fetchStatus) {

                    Elements spanWithId = doc.select("table#noarticletext");

                    if(spanWithId.size()==0) {


                        for (Element element : doc.select("table")) {
                            element.remove();
                        }

                        Elements paragraphs = doc.select(".mw-body-content p");
                        if (paragraphs != null) {


                                Element firstParagraph = paragraphs.first();

                                if (firstParagraph != null) {

                                    firstPara = firstParagraph.text();
                                }

                        }
                    }
                }
            }
        }
        return firstPara;
    }
}
