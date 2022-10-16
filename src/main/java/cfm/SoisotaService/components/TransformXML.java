package cfm.SoisotaService.components;

import cfm.SoisotaService.dto.DataMBankDTO;
import cfm.SoisotaService.entities.AppInvoiceTemplate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.Base64;
import java.util.Iterator;

@Component
public class TransformXML {

    public String modelToJsonObject(DataMBankDTO dataMBankDTO) {
        Gson gson = new GsonBuilder().serializeNulls().create();

        JSONObject jsonData;
        JSONObject json = new JSONObject();
        try {
            jsonData = new JSONObject(gson.toJson(dataMBankDTO));
            json.put("TTDCNGCS", jsonData);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        return json.toString();
    }

    public Document JSonToXmlXSL(String json) {
        try {
            JSONObject jsonParse = new JSONObject(json);
            JSONObject jsonRoot = new JSONObject();
            JSONObject jsonElement = new JSONObject();
            jsonElement.put("element", jsonParse);
            jsonRoot.put("root", jsonElement);
            System.out.println(jsonRoot);
            StringBuilder xml = new StringBuilder();
            StringBuilder result = CreateXMLFromJSon(jsonRoot.toString(), xml);
            return convertStringToXMLDocument(result.toString());
        } catch (Exception var5) {
            return null;
        }
    }

    public byte[] JSonToXmlXSLByte(String json) {
        try {
            JSONObject jsonParse = new JSONObject(json);
            JSONObject jsonRoot = new JSONObject();
            JSONObject jsonElement = new JSONObject();
            jsonElement.put("element", jsonParse);
            jsonRoot.put("root", jsonElement);
            System.out.println(jsonRoot);
            StringBuilder xml = new StringBuilder();
            StringBuilder result = CreateXMLFromJSon(jsonRoot.toString(), xml);
            String base64 = Base64.getEncoder().encodeToString(result.toString().getBytes());

            return Base64.getDecoder().decode(base64);
        } catch (Exception var5) {
            return null;
        }
    }

    public static StringBuilder CreateXMLFromJSon(String pInput, StringBuilder sb) throws JSONException {
        JSONObject input = new JSONObject(pInput);
        Iterator iter = input.keys();

        while(iter.hasNext()) {
            String key = (String)iter.next();
            System.out.println(key);
            Object value = input.get(key);
            if(key.equalsIgnoreCase("templateData")){
                continue;
            }

            if (value instanceof JSONObject) {
                String start;
                if (!value.toString().equals("")) {
                    start = createXmlBeginJsonObject(key);
                    sb.append(start);
                    StringBuilder result = CreateXMLFromJSon(value.toString(), new StringBuilder());
                    //StringBuilder sbNew = removeRedundantCharacters(result, key);
                    sb.append(result);
                    String end = createXmlEndJsonObject(key);
                    sb.append(end);
                } else {
                    start = createXmlObject(key, "");
                    sb.append(start);
                }
            } else {
                String start;
                if (!(value instanceof JSONArray)) {
                    int a = 10;
                    char c = (char)a;
                    //value = value.toString().replace(c, '^');
                    start = createXmlObject(key, value.toString());
                    sb.append(start);
                } else {
                    JSONArray array = new JSONArray(value.toString());
                    int index = 0;
                    if (array.length() <= 0) {
                        start = createXmlObject(key, "");
                        sb.append(start);
                    } else {
                        start = createXmlBeginJsonObject(key);
                        sb.append(start);

                        String end;
                        for( index = 0; index < array.length(); ++index) {
                            end = createXmlBeginJsonObject("element");
                            sb.append(end);
                            StringBuilder result;
                            if(array.get(index) != null) {
                                result = CreateXMLFromJSon(array.get(index).toString(), new StringBuilder());
                                //StringBuilder sbNew = removeRedundantCharacters(result, end);
                                sb.append(result);
                            }else{
                                String content = createXmlObject(key, "");
                                sb.append(content);
                            }


                            String endElement = createXmlEndJsonObject("element");
                            sb.append(endElement);
                        }

                        end = createXmlEndJsonObject(key);
                        sb.append(end);
                    }
                }
            }
        }
        return sb;
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

    private static Document convertStringToXMLDocument(String xmlString) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;

        try {
            builder = factory.newDocumentBuilder();
            InputSource source = new InputSource(new StringReader(xmlString.replaceAll("&", "&amp;")));
            source.setEncoding("UTF-8");
            Document doc = builder.parse(source);
            return doc;
        } catch (Exception var5) {
            var5.printStackTrace();
            return null;
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

    public String getTransformedHtmlBase64(byte[] xml, byte[] xsl){
        try {
            //declare
            Source srcXml = new StreamSource(new ByteArrayInputStream(xml));
            Source srcXsl = new StreamSource(new ByteArrayInputStream(xsl));

            // transform to html
            StringWriter sw = new StringWriter();
            Result result = new StreamResult(sw);

            TransformerFactory tFactory = TransformerFactory.newInstance();
            Transformer transformer = tFactory.newTransformer(srcXsl);
            transformer.transform(srcXml, result);
            System.out.println(removeRedundantCharacters(sw.toString()));

            return Base64.getEncoder().encodeToString(sw.toString().getBytes()); // html base 64
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    private static String removeRedundantCharacters(String result) {
        String tagFind = "?>";
        int index = result.indexOf(tagFind) + tagFind.length();
        return result.substring(index, result.length());
    }
}
