package coreservlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/** Servlet that handles previewing and storing resumes
 *  submitted by job applicants.
 *  <p>
 *  From <a href="http://courses.coreservlets.com/Course-Materials/">the
 *  coreservlets.com tutorials on servlets, JSP, Struts, JSF, Ajax, GWT, and Java</a>.
 */

@WebServlet("/show-resume-preview")
public class ShowResumePreview extends HttpServlet {
  @Override
  public void doPost(HttpServletRequest request,
                     HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    if (request.getParameter("previewButton") != null) {
      showPreview(request, out);
    }
  }

  /** Shows a preview of the submitted resume. Takes
   *  the font information and builds an HTML
   *  style sheet out of it, then takes the real
   *  resume information and presents it formatted with
   *  that style sheet.
   */
  
  private void showPreview(HttpServletRequest request, PrintWriter out) throws IOException {
  
    // this is the important text-getting line
    String text1; //= request.getParameter("textbox1");
    String text2; //= request.getParameter("textbox2");
    String file1 = request.getParameter("dropdown1");
    String file2 = request.getParameter("dropdown2");	
    int numQuotes = 10; // number of quotes to generate
    
    // load user's chosen speakers from txt files
    text1 = readFile(file1);
    text2 = readFile(file2);
    
    TextGenerator TG = new TextGenerator();
    String output = "";
    String quotes[];
    try{
    quotes = TG.generate(text1, text2, numQuotes);
    }
    catch (Exception e){ quotes = TG.generate(text1, text2, numQuotes);} // just try it again... :'(
    
    
    //String output = harvest("vader.txt","Darth Vader");//TG.readTextFile("forms/WebContent/jfkquotes.txt");
   
    // this is the important text-giving part
  
    out.println("<HTML><HEAD><TITLE>Text Output</TITLE><LINK REL=STYLESHEET HREF=\"jobs-site-styles.css\"TYPE=\"text/css\"></HEAD><h1><h1b>Output</h1b> Text</h1>\n<p>");
    for(int i = 0; i < numQuotes; i++) {
    	//quotes[i].replaceAll("*", ""); // remove speaker-switch markers
    	//quotes[i].replaceAll("\}", "<p>"); // separate new quotes with <p>
    	out.print(quotes[i] + "<p>");
    	//output+=quotes[i];
    }
    out.println(/*"<HTML><HEAD><TITLE>Text Output</TITLE>\n" + output + */"</BODY></HTML>");
  }

  /** Show a confirmation page when they press the
   *  "Submit" button.
   */
  /*	private String sum(String a, String b){
  		return String.valueOf(Integer.parseInt(a) + Integer.parseInt(b));
  	}*/
 /* private void showConfirmation(HttpServletRequest request,
                                PrintWriter out) {
    String title = "Submission Confirmed.";
    out.println(ServletUtilities.headWithTitle(title) +
                "<BODY>\n" +
                "<H1>" + title + "</H1>\n" +
                "Your resume should appear online within\n" +
                "24 hours. If it doesn't, try submitting\n" +
                "again with a different email address.\n" +
                "</BODY></HTML>");
  }*/

  private String readFile(String file) {
	  //response.setContentType("text/html");
		
		//
		// We are going to read a file called configuration.properties. This
		// file is placed under the WEB-INF directory.
		//
		String output = "";
		
		InputStream in = getServletContext().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String text;
        try {//System.out.println("trying!");
			while ((text = reader.readLine()) != null) {
			    output += text;
			}
		} catch (IOException e) {
			
		}
		//System.out.println(output);
		return output;
  }
  
  private String harvest(String file, String name) {
	  //response.setContentType("text/html");
		
		//
		// We are going to read a file called configuration.properties. This
		// file is placed under the WEB-INF directory.
		//
		String output = "";
		
		InputStream in = getServletContext().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String l1,l2="";
        try {//System.out.println("trying!");
			while ((l1 = reader.readLine()) != null) {
				if((l2 = reader.readLine()) != null) // current and next line exist
					if(l2.equalsIgnoreCase(name)) {// next line is "Abraham Lincoln"
						output += l1; // l1 is the quote- add it.
						//output += "}"; //individual quotes delimited by '}'
					}
			}
		} catch (IOException e) {
			
		}
		//System.out.println(output);
		return output;
  }
  
}
