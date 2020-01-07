#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <regex.h>

static char* text = "package http;\n"
					"\n"
					"import java.io.BufferedReader;\n"
					"import java.io.IOException;\n"
					"import java.util.HashMap;\n"
					"import java.util.Map;\n"
					"\n"
					"public class Response {\n"
					"\tpublic static final String CLRF = \"\\r\\n\";\n"
					"\tpublic static final String HTTP_VER = \"HTTP/1.1\";\n"
					"\tprivate String version;\n"
					"\tprivate int statusCode;\n"
					"\tprivate String reasonPhrase;\n"
					"\n"
					"\tprivate HashMap<String, String> headers;\n"
					"\tprivate String body;\n"
					"\n"
					"\tprivate Response(StatusCodes code) {\n"
					"\t\tthis.statusCode = HttpStatus.getStatusCode(code);\n"
					"\t\tthis.reasonPhrase = HttpStatus.getStatusText(code);\n"
					"\t\tthis.version = HTTP_VER;\n"
					"\t}\n"
					"\tprivate Response() {\n"
					"\t\tthis.headers = new HashMap<>();\n"
					"\t}\n"
					"\n"
					"\tpublic static Response generateResponse(StatusCodes code) {\n"
					"\t\tResponse response = new Response(code);\n"
					"\t\tresponse.headers = new HashMap<>();\n"
					"\t\treturn response;\n"
					"\t}\n"
					"\n"
					"\tpublic static Response generateResponse(StatusCodes code, String body) {\n"
					"\t\tResponse response = new Response(code);\n"
					"\t\tresponse.headers = new HashMap<>();\n"
					"\t\tresponse.setBody(body);\n"
					"\t\treturn response;\n"
					"\t}\n"
					"\t\n"
					"\tpublic static Response generateResponse(BufferedReader reader) throws IOException {\n"
					"\t\tResponse response = new Response();\n"
					"\t\tString line;\n"
					"\t\twhile ((line = reader.readLine()) != null) {\n"
					"\t\t\tif (line.isEmpty()) {\n"
					"\t\t\t\tStringBuilder buffer = new StringBuilder();\n"
					"\t\t\t\tMap.Entry<String, String> cl = response.getHeader(\"Content-length\");\n"
					"\t\t\t\tif (cl != null) {\n"
					"\t\t\t\t\tint length = Integer.parseInt(cl.getValue());\n"
					"\t\t\t\t\twhile (length > 0) {\n"
					"\t\t\t\t\t\tbuffer.append((char) (reader.read()));\n"
					"\t\t\t\t\t\tlength--;\n"
					"\t\t\t\t\t}\n"
					"\t\t\t\t\tresponse.body = buffer.toString();\n"
					"\t\t\t\t}\n"
					"\t\t\t\tbreak;\n"
					"\t\t\t} else {\n"
					"\t\t\t\tString[] parts = line.split(\": \");\n"
					"\t\t\t\tif (parts.length == 2) {\n"
					"\t\t\t\t\tresponse.headers.put(parts[0].toLowerCase(), parts[1]);\n"
					"\t\t\t\t} else {\n"
					"\t\t\t\t\tString[] requestLine = line.split(\" \", 3);\n"
					"\t\t\t\t\tif (requestLine.length == 3) {\n"
					"\t\t\t\t\t\tresponse.version = requestLine[0];\n"
					"\t\t\t\t\t\tresponse.setStatusCode(Integer.parseInt(requestLine[1]));\n"
					"\t\t\t\t\t\tresponse.reasonPhrase = requestLine[2];\n"
					"\t\t\t\t\t}\n"
					"\n"
					"\t\t\t\t}\n"
					"\t\t\t}\n"
					"\t\t}\n"
					"\t\treturn response;\n"
					"\t}\n"
					"\n"
					"\tpublic String getVersion() {\n"
					"\t\treturn version;\n"
					"\t}\n"
					"\n"
					"\tpublic void setVersion(String version) {\n"
					"\t\tthis.version = version;\n"
					"\t}\n"
					"\n"
					"\tpublic int getStatusCode() {\n"
					"\t\treturn statusCode;\n"
					"\t}\n"
					"\n"
					"\tpublic void setStatusCode(int statusCode) {\n"
					"\t\tthis.statusCode = statusCode;\n"
					"\t}\n"
					"\n"
					"\tpublic String getReasonPhrase() {\n"
					"\t\treturn reasonPhrase;\n"
					"\t}\n"
					"\n"
					"\tpublic void setReasonPhrase(String reasonPhrase) {\n"
					"\t\tthis.reasonPhrase = reasonPhrase;\n"
					"\t}\n"
					"\n"
					"\tpublic HashMap<String, String> getHeaders() {\n"
					"\t\treturn headers;\n"
					"\t}\n"
					"\n"
					"\tpublic void setHeaders(HashMap<String, String> headers) {\n"
					"\t\tthis.headers = headers;\n"
					"\t}\n"
					"\n"
					"\tpublic Map.Entry<String, String> getHeader(String header) {\n"
					"\t\tfor (Map.Entry<String, String> h : this.headers.entrySet()) {\n"
					"\t\t\tif (h.getKey().toUpperCase().equals(header.toUpperCase())) {\n"
					"\t\t\t\treturn h;\n"
					"\t\t\t}\n"
					"\t\t}\n"
					"\t\treturn null;\n"
					"\t}\n"
					"\n"
					"\tpublic String getBody() {\n"
					"\t\treturn body;\n"
					"\t}\n"
					"\n"
					"\tpublic void setBody(String body) {\n"
					"\t\tthis.headers.put(\"Content-length\", Integer.toString(body.length()));\n"
					"\t\tthis.body = body;\n"
					"\t}\n"
					"\n"
					"\tpublic void setBody(StringBuilder body) {\n"
					"\t\tthis.setBody(body.toString());\n"
					"\t}\n"
					"\n"
					"\tpublic byte[] getBytes() {\n"
					"\t\treturn this.toString().getBytes();\n"
					"\t}\n"
					"\n"
					"\t@Override\n"
					"\tpublic String toString() {\n"
					"\t\tSystem.out.println(\"Some text = other text!\");\n"
					"\t\tStringBuilder headersString = new StringBuilder();\n"
					"\t\tfor (Map.Entry<String, String> h : this.headers.entrySet()) {\n"
					"\t\t\theadersString.append(String.format(\"%s: %s\\r\\n\", h.getKey(), h.getValue()));\n"
					"\t\t}\n"
					"\t\treturn\n"
					"\t\t\t\tthis.version + \" \" + this.statusCode + \" \" + this.reasonPhrase  + Response.CLRF\n"
					"\t\t\t\t\t\t+ headersString.toString()\n"
					"\t\t\t\t\t\t+ Response.CLRF\n"
					"\t\t\t\t\t\t+ (this.body == null ? \"\" : this.body);\n"
					"\t}\n"
					"\n"
					"\tpublic void setHeader(String key, String value) {\n"
					"\t\tthis.headers.put(key, value);\n"
					"\t}\n"
					"}";

int main() {
	#define BUFSIZE 256
	regex_t regex;
	int reti;
	char* str = malloc(strlen(text));
	strcpy(str, text);
	char* curr = str;
	int match_count = 0;

	reti = regcomp(&regex, "(.+[^=!<>])=([^=].+)", REG_EXTENDED | REG_NEWLINE);
	if (reti) {
		fprintf(stderr, "Could not compile regex\n");
		exit(1);
	}

	while (curr) {
		char* next = strchr(curr, '\n');
		if (next) *next = '\0';
		reti = regexec(&regex, curr, 0, NULL, 0);
		if (!reti) {
			match_count++;
			puts(curr);
		}
		if (next) *next = '\n';  // then restore newline-char, just to be tidy
		curr = next ? (next + 1) : NULL;
	}

	fprintf(stderr, "Matches: %d", match_count);
	return 0;
	#undef BUFSIZE
}