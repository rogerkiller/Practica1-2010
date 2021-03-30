package Practica1.RUM_WVS;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class SuperheroesBBDD {
	
	private String BBDDid;
	
	private ArrayList<Heroes> heroes; 
	
	public SuperheroesBBDD()
	{
		this.heroes = new ArrayList<Heroes>();
	}
	
	public SuperheroesBBDD(String BBDDid, String filePath)
	{
		this.BBDDid = BBDDid;
		this.heroes = new ArrayList<Heroes>();
		loadFile(filePath);
	}
	
	public SuperheroesBBDD(String BBDDid)
	{
		this.BBDDid = BBDDid;
		this.heroes = new ArrayList<Heroes>();
	}
	
	
	public boolean saveFile(String path) {
		try {

			Element rootNode = new Element("SuperheroesBBDD_" + BBDDid);
			Document doc = new Document(rootNode);

			Element node1 = new Element("DatosSuperheroes");
			node1.addContent(new Element("BBDDid").setText(BBDDid));

			doc.getRootElement().addContent(node1);

			Element node2 = new Element("Heroes");
			for (Heroes cliente : heroes) {
				Element node21 = new Element("Heroe");
				node21.addContent(new Element("Nombre").setText(cliente.getNombreHeroe()));
				node21.addContent(new Element("Identidad").setText(cliente.getSecretID()));
				node21.addContent(new Element("Batallas").setText(cliente.getBatallas().toString()));
				node21.addContent(new Element("Habilidades").setText(cliente.getHabilidades().toString()));
				}

				node2.addContent(node2);
			
			XMLOutputter xmlOutput = new XMLOutputter();

			xmlOutput.setFormat(Format.getPrettyFormat().setEncoding("UTF-8"));
			xmlOutput.output(doc, new FileWriter(path));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}

		return true;
	}
	
	
	public boolean loadFile(String path) {
		SAXBuilder builder = new SAXBuilder();
		// Damos por valida la ruta absoluta al archivo a cargar
		File xmlFile = new File(path);

		try {

			Document document = (Document) builder.build(xmlFile);
			Element rootNode = document.getRootElement();

			Element node1 = rootNode.getChild("DatosBBDD");

			this.BBDDid = node1.getChildText("BBDDid");

			Element node2 = rootNode.getChild("Heroes");
			for (Element node21 : node2.getChildren("Heroe")) {
				for (Element node211 : node21.getChildren("Batallas")) {
					for (Element node2111 : node21.getChildren("Habilidades")) {
						for (int i = 0; i < heroes.size(); i++) {
							String s = node21.getChildText("HeroeID");
							heroes.add(new Heroes(Integer.parseInt(node21.getChildText("HeroeID")), node21.getChildText("NombreHeroe"), node21.getChildText("SecretID"), 
									node21.getChildText("Genero"), node21.getChildText("Procedencia"), new ArrayList<Batallas>(), new ArrayList<Habilidades>()));
							heroes.get(i).getBatallas().add(new Batallas(Integer.parseInt(node211.getChildText("IDBatalla")), node21.getChildText("Lugar"), 
									new SimpleDateFormat("dd/MM/yyyy").parse(node21.getChildText("Genero")), new SimpleDateFormat("dd/MM/yyyy").parse(node21.getChildText("Genero"))));
						}
					}	
				}
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

}
