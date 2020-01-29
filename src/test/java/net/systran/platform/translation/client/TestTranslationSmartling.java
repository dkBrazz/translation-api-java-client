package net.systran.platform.translation.client;

import net.systran.platform.translation.client.api.TranslationApi;
import net.systran.platform.translation.client.auth.ApiKeyAuth;
import net.systran.platform.translation.client.model.TranslationResponse;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TestTranslationSmartling
{
    private static final String LF = "\n";
    private static final String CR = "\r";
    private static final String CRLF = CR + LF;

    public static TranslationApi getTranslationApi() throws IOException
    {
        ApiClient apc = new ApiClient();
        ApiKeyAuth apiKeyAuth = (ApiKeyAuth) apc.getAuthentication("apiKey");
        String apiKey = ApiClient.LoadAPIKey(".//apiKey.txt");
        apiKeyAuth.setApiKey(apiKey);
        apc.setBasePath("https://translationpartners-spn9.mysystran.com:8904");
        return new TranslationApi(apc);
    }

    @Test
    public void testTranslationTranslateGetAutoFrHtmlWithFormat() throws ApiException, IOException
    {
        for (String sourceStr : this.sources)
        {
            System.out.println(sourceStr);

            TranslationApi api = getTranslationApi();
            List<String> inputs = new ArrayList<String>();
            inputs.add(sourceStr);
            String source = "en";
            String target = "fr";
            String format = "text/html";
            TranslationResponse translationResponse =
                    api.translationTextTranslateGet(inputs, source, target, format, null, null, null, null, null, null, null, null, null);
            System.out.println(translationResponse.getOutputs().get(0).getOutput().replace("\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n", ""));
            System.out.println("");
        }
    }

    private String[] sources = {
            // === HTML tags ===
            "<div class=\"test\">Regular <span>html text</span>. Also <a href=\"{ph:www.google.com?param1}\">link here</a></div>",
            "HTML without</span> open span tag. Also <a href=\"{ph:www.google.com?param1}\">link here</a>",
            "<div class=\"test\">HTML <span>without</span> closing div and link tag. Also <a href=\"{ph:www.google.com?param1}\">link here",
            "<span smId=\"{ph:\\{0\\}}\">Letter case</span>of<br /><span smId={ph:\\\"\\{1\\}\\\"}>tag attributes</span> must be the same (smId)",
            "BR tag in the <span>end of string</span> should stay in the end<br/>",
            "<div class=\"test\">BR tags <span id={ph:12}>HTML text</span><br>should be preserved<br/> regardless of form</div>",
            "<div class=\"test\"><span id={ph:12}></span><br><a href=\"\"><string><strong></a></div>",
            // Don't forget to replace CRLF with 0x0D0x0A
            "<div class=\"test\">New lines in <span id={ph:12}>HTML text</span>" + CRLF
                    + "should be preserved</div>" + CRLF
                    + "<p>This is third line</p>",
            // Don't forget to replace LF with 0x0A
            "<div class=\"test\">New lines in <span id={ph:12}>HTML text</span>" + LF
                    + "should be preserved</div>" + LF
                    + "<p>This is third line</p>",

            // --- HTML entities in HTML ---
            "<div class=\"test\">Regular <span>html text</span> with &quot; valid &amp;&lt;html entities&gt;</div>",
            "<div class=\"1&gt;2\">Regular <span>html text</span>. Also <a href=\"{ph:www.google.com?param1&amp;param2}\">html entity in tag attribute</a></div>",
            "<div class=\"test\">Regular <span>html text</span> with \" invalid & >html entities<.</div>",
            "<div class=\"1>2\">Regular <span>html text</span>. Also <a href=\"{ph:www.google.com?param1&param2}\">unescaped html entity in tag attribute</a></div>",
            "<div class=\"test\">Regular&nbsp;html text&nbsp; with non-breaking spaces.</div>&nbsp;They should be preserved&nbsp;\"",
            "<div>Escaped HTML&lt;span&gt;inside regular HTML &lt;opt&gt;must&lt;/opt&gt; stay&lt;/span&gt; </div><span>escaped, otherwise import will fail</span>",
            "<div>Double escaped &lt;span&gt;HTML&amp;lt;span&amp;gt;inside regular HTML &amp;lt;opt&amp;gt;must&amp;lt;/opt&amp;gt; stay&amp;lt;/span&amp;gt; &lt;/span&gt;</div><span>escaped, otherwise import will fail</span>",

            // --- Nested, sibling tags ---
            "Image marker <span>inside<img src=\"\"></span> span tag",
            "Multiple image marker <span>inside<img id=\"1\"><img id=\"2\"><img id=\"3\"><img id=\"4\"></span> span tag",
            "Image marker <span>outside</span><img src=\"\"> span tag",
            "Multiple image markers <span>outside</span><img id=\"1\"><img id=\"2\"><img id=\"3\"><img id=\"4\"> span tags",
            "Image marker <span>at the end</span> of the strings<img src=\"\">",
            "Hello string 1 <a href=\"test\">hello string 2<span></span></a>.",
            "<div>Hello string 1 <a href=\"test\">hello string 2<span></span></a></div>",
            "<a href=\"test\">hello string 1</a><br><span>hello string 2</span>",
            "<a href=\"test\">hello string 1<br></a><span>hello string 2</span>",
            "<a href=\"test\">hello string 1</a><span><br>hello string 2</span>",
            "<div><a href=\"test\">hello string 1</a><br><img src=\"test\"><hr><span>hello string 2</span></div>",
            "<div><a href=\"test\">hello string 1</a><br><span><img src=\"test\"><hr>hello string 2</span></div>",
            "<t1>This</t1>.<t2>is</t2>.a.<t3>test</t3>",
            "<t1>This</t1>. <t2>is</t2>. a. <t3>test</t3>",

            // === Smartling placeholders ===
            // --- Placeholders in HTML ---
            "<span id={0}>Smartling <a href=\"{1}\" target=\"blank\">placeholders</a> in tag attributes</span> should not be broken",
            "<span id={ph:\\{0\\}}>Smartling <a href=\"{ph:\\{1\\}}\" target=\"{ph:blank}\">placeholders</a> in tag attributes</span> should not be broken",
            "Smartling <span class=\"{ph:test}\">placeholder {0}</span> in text {1} and <a href=\"{ph:\\{2\\}}\" target=\"{ph:blank}\" alt=\"{ph:alt text here}\" id={ph:12}>tag</a>.",
            "Mix <span id={ph:\\\\{0\\\\}}>of <a href=\"{ph:\\\\{1\\\\}}\" target=\"{ph:blank}\">smartling</a> <span id=\"{1}\">classic {2}</span> and {ph: begin format}modern{ph:end format} placeholders in HTML {3}text{4}</span>",
            "<span id={0}>Mix of <a href=\"{ph:\\{1\\}}\" target=\"{ph:blank}\">smartling {{2}}placeholders{3}</a> and strings that {hello} looks {0-count} like placeholder {ph:{stop parse here}} in <strong id=\"{4}\">HTML {{ph: begin format}}text{ph:end format}</strong></span>",
            "<div class=\"test\"><span id={ph:12}>{0}{1}</span><br><a href=\"\"><strong>{2}{3}<strong></a></div>",

            // --- Placeholders in plain text
            "Smartling placeholder {0} in plain text. {1} should be easy. There are {2}{3}{4} placeholders",
            "Mix of smartling {0}classic{ph:__1__} {2} and {ph: begin format}modern{ph:end format} placeholders in plain {3}text{4}",
            "Mix of smartling {{0}}placeholders{1} and strings that {hello} looks {0-count} like placeholder {ph:{stop parse here}} in plain {{ph: begin format}}text{ph:end format}",
            "{0}{1}{2}{3}",
            "{0}{1}{ph:stop parse here}{2}{3}",

            // === Plain text ===
            "Text1. This is simple plain text. It has coma, and consist of 2 sentences.",
            "Text2. NOTE: Regarding between image and richtext, on mobile resolution image will be always first",
            "Text3. Research shows organizations that demonstrate a higher purpose are in a better position to innovate...",
            "Text4. Content Feature (Person) - Image to the left",
            // Don't forget to replace CRLF with 0x0D0x0A
            "Text5. New lines in text" + CRLF
                    + "should be preserved." + CRLF
                    + "Third line",
            // Don't forget to replace LF with 0x0A
            "Text6. New lines in text" + LF
                    + "should be preserved." + LF
                    + "Third line",

            // --- HTML entities in plain text ---
            "Regular plain text with &quot; valid &amp;&lt;html entities&gt;",
            "Regular plain text with \" and other & $ characters that looks like >html entities<.",
            "Regular plain text&nbsp;with&nbsp;non-breaking spaces. Can be translated as space"
    };
}
