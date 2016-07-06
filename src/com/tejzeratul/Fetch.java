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
 */

public class Fetch {

    /**
     * returns first paragraph as text from Wikipedia, if the article for the topic exist
     *
     * @param title name of topic to find first paragraph, if page exist
     * @return firstPara text from first paragraph of Wikipedia article
     */
    public String getFirstWikiPara(String title) {

        /**
         * Stores text of first paragraph of Wikipedia article
         */
        String firstPara = Setting.MSG_CONTENT_UNAVAILABLE;

        /**
         * Indicates whether we received HTTP response status 200
         */
        boolean fetchStatus = true;

        if (title != null) {

            title = title.trim();
            if (!title.isEmpty()) {

                // Replacing spaces in between topic name
                title = title.replaceAll(" ", Setting.WIKI_TOPIC_IN_SPACING);
                String currentTopicUrl;
                Document doc = null;

                // Building URL
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

                    // To remove meta data & non desired information
                    Elements spanWithId = doc.select("table#noarticletext");

                    if (spanWithId.size() == 0) {

                        for (Element element : doc.select("table")) {
                            element.remove();
                        }

                        // To find all <p> tags which are direct child of <body> tag
                        Elements paragraphs = doc.select(".mw-body-content p");
                        if (paragraphs != null) {

                            // To get first paragraphs from all paragraphs
                            Element firstParagraph = paragraphs.first();

                            if (firstParagraph != null) {

                                // To get text from first paragraph
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
