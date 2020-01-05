package pl.nogacz.shop.util;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.stereotype.Component;

@Component
public class HtmlClean {
    public String cleanText(String input) {
        String replace = input.replace("&amp;", "");
        String html = StringEscapeUtils.unescapeHtml3(replace);
        String clean = Jsoup.clean(html, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
        return StringEscapeUtils.unescapeHtml4(clean);
    }
}
