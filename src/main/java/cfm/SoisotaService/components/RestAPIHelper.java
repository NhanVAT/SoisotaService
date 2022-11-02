package cfm.SoisotaService.components;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.stereotype.Component;

@Component
public class RestAPIHelper {

  public String requestAPI(String serviceName, String apiName, String method, String param) {
    String output = "";
    String result = "";

    try {
      String urlApi = serviceName + apiName;
      URL url = new URL(urlApi);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();

      switch (method.toUpperCase()) {
        case "GET": {
          conn.setRequestMethod("GET");
          conn.setRequestProperty("Accept", "application/json");
          InputStream in = conn.getInputStream();

          for (BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
              (output = br.readLine()) != null; result = output) {
          }

          break;
        }
        case "POST": {
          conn.setDoOutput(true);
          conn.setRequestMethod("POST");
          conn.setRequestProperty("Content-Type", "application/json");
          OutputStream os = conn.getOutputStream();
          os.write(param.getBytes("UTF-8"));
          os.flush();
          InputStream in = conn.getInputStream();

          for (BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
              (output = br.readLine()) != null; result = result + output) {
          }

          break;
        }
        default:
          break;
      }

      conn.disconnect();

      return result;
    } catch (Exception ex) {
      return ex.getMessage();
    }
  }
}
