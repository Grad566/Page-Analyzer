package gg.jte.generated.ondemand.sites;
public final class JtemainPageGenerated {
	public static final String JTE_NAME = "sites/mainPage.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,2,2,10,10,10,10,10,10,10,10};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor) {
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <div class=\"container text-center\">\r\n            <div class=\"row\">\r\n                <div class=\"col\">\r\n                    Hello, world!\r\n                </div>\r\n            </div>\r\n        </div>\r\n    ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		render(jteOutput, jteHtmlInterceptor);
	}
}
