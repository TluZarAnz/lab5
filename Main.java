import tools.Reader;
import commands.ConsoleManager;
import org.xml.sax.SAXException;
import utils.CollectionManager;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        CollectionManager.initializeStack();//инициализируем коллекцию
        Reader serv = new Reader();
        serv.xmlRead(CollectionManager.getCollection(),"f.xml");//загружаем элементы для коллекции из файла формата Xml
        ConsoleManager consoleManager = new ConsoleManager();
        consoleManager.InteractiveMode();//запускаем интерактив для юзера
    }
}
