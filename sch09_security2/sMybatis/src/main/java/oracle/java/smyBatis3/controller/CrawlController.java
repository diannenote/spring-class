package oracle.java.smyBatis3.controller;

import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import oracle.java.smyBatis3.controller.VO.SampleVO;
import oracle.java.smyBatis3.model.Crawling;
import oracle.java.smyBatis3.model.Dept;
import oracle.java.smyBatis3.service.EmpService;

@Controller
public class CrawlController {
	@Autowired
	private EmpService es;

	@RequestMapping(value = "crawler", method = RequestMethod.GET)
	public String home(Model model) throws Exception {
		System.out.println("crawler Start");
    //    String articleURL = "http://blog.acronym.co.kr";   //blog URL -> eAnchor
        String articleURL = "http://www.imaeil.com/sub_news/sub_news_view.php?news_id=20000&yy=2015";   //신문기사 URL
        Document doc = Jsoup.connect(articleURL).get();   // document 객체 생성.
        
        // doc.getElementByTag("body") 와 동일 
        // Document doc = Jsoup.parseBodyFragment(articleURL);
        // Element body =  doc.body();       //   메소드는 문서의 body 요소를 추출
        // System.out.println("body->"+body);

        Elements ele1 = doc.select("div#_article");       // 아이디가 _article인 div 태그 선택
        String str1 = ele1.text();                        // 값 저장
        Elements eTitles = doc.select(".txt2");          // class가  txt2 태그 선택
        String titles = eTitles.text(); 
        // sysout
        // 값 저장
        for(Element e: eTitles ) {
        	System.out.println("Text:" +e.text());
        	System.out.println("html:" +e.html());
        }

        Elements eAnchor = doc.select("a[href]");         // Anchor Tag[href]로  링크 걸려있는 부분 선택
        String Anchor = eAnchor.text();                   // 값 저장
        for(Element a: eAnchor ) {
        	System.out.println("Link:" +a.attr("abs:href"));
        }
	  	Crawling tbl_Crawl = new Crawling();
	  	tbl_Crawl.setUrl(articleURL);
	  	tbl_Crawl.setTitle("신문");
	  	tbl_Crawl.setContent(str1);

//		int result = es.insert2(tbl_Crawl);  // tbl_Crawl Insert
//		if (result > 0) model.addAttribute("msg","입력 성공");
//		else 	model.addAttribute("msg","입력 실패 확인해 보세요");
// 
//        model.addAttribute("string", str1);
		return "crawler";
		
		
		
		
		
		
		
		
		
	}



}
