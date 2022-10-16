package cfm.SoisotaService.components;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

@Component
public class TransformXSLTAndXML {

  public static Document jSonToXmlXSL(JSONObject jsonParse) {
    try {
      JSONObject jsonRoot = new JSONObject();
      JSONObject jsonElement = new JSONObject();
      jsonElement.put("element", jsonParse);
      jsonRoot.put("root", jsonElement);
      StringBuilder xml = new StringBuilder();
      createXMLFromJSon(jsonRoot.toString(), xml);
      return convertStringToXMLDocument(xml.toString());
    } catch (Exception var5) {
      return null;
    }
  }

  private static Document convertStringToXMLDocument(String xmlString) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;

    try {
      builder = factory.newDocumentBuilder();
      InputSource source = new InputSource(new StringReader(xmlString));
      source.setEncoding("UTF-8");
      Document doc = builder.parse(source);
      return doc;
    } catch (SAXParseException var5) {
      var5.printStackTrace();
      throw new Exception(var5.getMessage());
    } catch (Exception var6) {
      var6.printStackTrace();
      throw new Exception(var6.getMessage());
    }
  }

  public static void createXMLFromJSon(String pInput, StringBuilder sb)
      throws JSONException, Exception {
    JSONObject input = new JSONObject(pInput);
    Iterator iter = input.keys();

    while (true) {
      while (iter.hasNext()) {
        String key = (String) iter.next();
        Object value = input.get(key);
        System.out.println(value);
        if (value instanceof JSONObject) {
          String start;
          if (!value.toString().equals("")) {
            start = createXmlBeginJsonObject(key.toString());
            sb.append(start);
            createXMLFromJSon(value.toString(), sb);
            String end = createXmlEndJsonObject(key.toString());
            sb.append(end);
          } else {
            start = createXmlObject(key.toString(), "");
            sb.append(start);
          }
        } else {
          String objectName;
          if (!(value instanceof JSONArray)) {
            int a = 10;
            char c = (char) a;
            value = value.toString().replace(c, '^');
            value = stripIllegalCharacter5((Object) value);
            objectName = createXmlObject(key.toString(), value.toString());

            try {
              checkvalidStringToXMLDocument(objectName);
            } catch (Exception var11) {
              throw new Exception("Ký tự lạ: " + objectName.toString() + " ." + var11.getMessage());
            }

            sb.append(objectName);
          } else {
            JSONArray array = new JSONArray(value.toString());
            if (array.length() <= 0) {
              objectName = createXmlObject(key.toString(), "");
              sb.append(objectName);
            } else {
              objectName = createXmlBeginJsonObject(key.toString());
              sb.append(objectName);

              String end;
              for (int index = 0; index < array.length(); ++index) {
                end = createXmlBeginJsonObject("element");
                sb.append(end);
                createXMLFromJSon(array.get(index).toString(), sb);
                String endElement = createXmlEndJsonObject("element");
                sb.append(endElement);
              }

              end = createXmlEndJsonObject(key.toString());
              sb.append(end);
            }
          }
        }
      }

      return;
    }
  }

  private static String createXmlBeginJsonObject(String tagName) {
    StringBuilder sb = new StringBuilder();
    sb.append("<");
    sb.append(tagName);
    sb.append(">");
    return sb.toString();
  }

  private static String createXmlEndJsonObject(String tagName) {
    StringBuilder sb = new StringBuilder();
    sb.append("</");
    sb.append(tagName);
    sb.append(">");
    return sb.toString();
  }

  private static String createXmlObject(String tagName, String value) {
    StringBuilder sb = new StringBuilder();
    sb.append("<");
    sb.append(tagName);
    if (value.equals("")) {
      sb.append("/>");
    } else {
      sb.append(">");
      sb.append(value);
      sb.append("</");
      sb.append(tagName);
      sb.append(">");
    }

    return sb.toString();
  }

  private static Object stripIllegalCharacter5(Object ot) {
    if (StringUtils.isNumeric(ot.toString())) {
      return ot;
    } else {
      String t = ot.toString();
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < t.length(); ++i) {
        int codePoint = t.codePointAt(i);
        char c = t.charAt(i);
        if (codePoint == 18) {
          System.out.println(codePoint + ":" + c);
        }

        switch (c) {
          case '"':
            sb.append("&quot;");
            continue;
          case '&':
            sb.append("&amp;");
            continue;
          case '\'':
            sb.append("&apos;");
            continue;
          case '<':
            sb.append("&lt;");
            continue;
          case '>':
            sb.append("&gt;");
            continue;
        }

        if (c == '\t' || c == '\n' || c == '\r' || c >= ' ' && c <= '\ud7ff'
            || c >= '\ue000' && c <= '�' || c >= 65536 && c <= 1114111) {
          sb.append(c);
        }
      }

      return sb;
    }
  }

  private static Document checkvalidStringToXMLDocument(String xmlString) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = null;

    try {
      builder = factory.newDocumentBuilder();
      Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
      return doc;
    } catch (SAXParseException var4) {
      var4.printStackTrace();
      throw new Exception(var4.getMessage());
    } catch (Exception var5) {
      var5.printStackTrace();
      throw new Exception("Lỗi khi convert sang xml: " + var5.getMessage());
    }
  }

  public static String transform(Document dataXML, byte[] inputXSL) {
    TransformerFactory factory = TransformerFactory.newInstance();

    Transformer transformer;
    try {
      transformer = factory.newTransformer();
    } catch (Exception var18) {
      return "ERROR:" + var18.getMessage();
    }

    dataXML.setXmlStandalone(true);
    DOMSource source = new DOMSource(dataXML);
    ByteArrayOutputStream xmmInputStream = new ByteArrayOutputStream();
    StreamResult result = new StreamResult(xmmInputStream);

    try {
      transformer.transform(source, result);
    } catch (TransformerException var17) {
      return "ERROR:" + var17.getMessage();
    }

    byte[] byteDataXML = xmmInputStream.toByteArray();
    InputStream inputStreamXSL = new ByteArrayInputStream(inputXSL);
    InputStream inputStreamXML = new ByteArrayInputStream(byteDataXML);
    StringWriter writer = new StringWriter();
    StreamSource xslStream = new StreamSource(inputStreamXSL);

    try {
      transformer = factory.newTransformer(xslStream);
    } catch (TransformerConfigurationException var16) {
      return "ERROR:" + var16.getMessage();
    }

    StreamSource in = new StreamSource(inputStreamXML);
    StreamResult out = new StreamResult(writer);

    try {
      transformer.transform(in, out);
      return writer.toString();
    } catch (TransformerException var15) {
      return "ERROR:" + var15.getMessage();
    }
  }
}
