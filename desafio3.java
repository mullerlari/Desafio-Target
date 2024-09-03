import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Faturamento {

    public static void main(String[] args) {
        String filePath = "faturamento.xml";

        try {
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(filePath));
            document.getDocumentElement().normalize();

            
            NodeList nodeList = document.getElementsByTagName("row");

            List<Double> faturamentos = new ArrayList<>();
            double menorFaturamento = Double.MAX_VALUE;
            double maiorFaturamento = Double.MIN_VALUE;
            double somaFaturamento = 0;
            int diasComFaturamento = 0;

            
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element = (Element) nodeList.item(i);
                double valor = Double.parseDouble(element.getElementsByTagName("valor").item(0).getTextContent());

                if (valor > 0) {
                    faturamentos.add(valor);
                    if (valor < menorFaturamento) {
                        menorFaturamento = valor;
                    }
                    if (valor > maiorFaturamento) {
                        maiorFaturamento = valor;
                    }
                    somaFaturamento += valor;
                    diasComFaturamento++;
                }
            }

            double mediaMensal = diasComFaturamento > 0 ? somaFaturamento / diasComFaturamento : 0;
            int diasAcimaDaMedia = 0;

            for (double faturamento : faturamentos) {
                if (faturamento > mediaMensal) {
                    diasAcimaDaMedia++;
                }
            }

            
            System.out.println("Menor valor de faturamento: " + menorFaturamento);
            System.out.println("Maior valor de faturamento: " + maiorFaturamento);
            System.out.println("Numero de dias com faturamento acima da media mensal: " + diasAcimaDaMedia);

        } catch (Exception e) {
            System.out.println("Erro ao processar o arquivo XML: " + e.getMessage());
        }
    }
}