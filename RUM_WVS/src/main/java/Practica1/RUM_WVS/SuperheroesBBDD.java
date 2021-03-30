package Practica1.RUM_WVS;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
			for (Heroes heroe : heroes) {
				Element node21 = new Element("Heroe");
				node21.addContent(new Element("Nombre").setText(heroe.getNombreHeroe()));
				node21.addContent(new Element("Identidad").setText(heroe.getSecretID()));
				node21.addContent(new Element("Batallas").setText(heroe.getBatallas().toString()));
				node21.addContent(new Element("Habilidades").setText(heroe.getHabilidades().toString()));
				
				for (Batallas batalla : heroe.getBatallas()) {
					Element node211 = new Element("Batallas");
					node211.addContent(new Element("IDBatalla").setText(Integer.toString(batalla.getIDBatalla())));
					node211.addContent(new Element("Lugar").setText(batalla.getLugar()));
					node211.addContent(new Element("FechaIni").setText(batalla.getFechaIni().toString()));
					node211.addContent(new Element("FechaFin").setText(batalla.getFechaFin().toString()));
										
					node21.addContent(node211);
				}
				for (Habilidades habilidades : heroe.getHabilidades()) {
					Element node212 = new Element("Habilidades");
					node212.addContent(new Element("Tipo").setText(habilidades.getTipo()));
					node212.addContent(new Element("Definicion").setText(habilidades.getDefinicion()));
										
					node21.addContent(node212);
				}
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
							heroes.add(new Heroes(Integer.parseInt(node21.getChildText("HeroeID")), node21.getChildText("NombreHeroe"), node21.getChildText("SecretID"), 
									node21.getChildText("Genero"), node21.getChildText("Procedencia"), null,null));
							heroes.get(i).getBatallas().add(new Batallas(Integer.parseInt(node211.getChildText("IDBatalla")), node21.getChildText("Lugar"), 
									new SimpleDateFormat("dd/MM/yyyy").parse(node21.getChildText("Genero")), new SimpleDateFormat("dd/MM/yyyy").parse(node21.getChildText("Genero"))));
							heroes.get(i).getHabilidades().add(new Habilidades(node2111.getChildText("Tipo"), node21.getChildText("Definicion")));
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
	
	public void printBBDD(String encabezado, boolean mostrarInfoBDD, boolean mostrarHeroe,
			boolean mostrarBatallas) {
			String output = "=================[ " + encabezado + " ]=================\n";
	
			if (mostrarInfoBDD) {
				output += "- Datos de la base\n";
				output += "\tBBDDid: " + BBDDid + "\n";
			}
	
			if (mostrarHeroe) {
				output += "\n- Heroes\n";
				if (heroes.size() > 0) {
					for (Heroes heroe : heroes) {
						output += "\t[Nombre: " + heroe.getNombreHeroe()
						+ "\n\tHeroeID: " + heroe.getHeroeID()
						+ "\n\tSecretID: " + heroe.getSecretID()
						+ "\n\tGenero: " + heroe.getGenero()
						+ "\n\tProcedencia: " + heroe.getProcedencia()
						+ "\n\tHabilidades: " + heroe.getHabilidades();
						
						for (int i = 0; i < heroe.getBatallas().size(); i++) {
							if(heroes.get(i).getBatallas().size() > 0) {
								output += "\n\t\tTipo: " + heroe.getHabilidades().get(i).getTipo()
										+ "\n\t\tDefinicion: " + heroe.getHabilidades().get(i).getDefinicion() + "]\n";	
							}
							else {
								output += "\tSin Habilidades...\n";
							}
						}
					}
				}
			}
				
			if (mostrarBatallas) {
				output += "\n- Heroes\n";
				if (heroes.size() > 0) {
					for (Heroes heroe : heroes) {
						output += "\t[Nombre: " + heroe.getNombreHeroe()
						+ "\n\tHeroeID: " + heroe.getHeroeID();
												
						for (int i = 0; i < heroe.getBatallas().size(); i++) {
							if(heroes.get(i).getBatallas().size() > 0) {
								output += "\n\t\tIDBatalla: " + heroe.getBatallas().get(i).getIDBatalla()
										+ "\n\t\tLugar: " + heroe.getBatallas().get(i).getLugar()
										+ "\n\t\tFecha de Inicio: " + heroe.getBatallas().get(i).getFechaIni()
										+ "\n\t\tFecha de finalizacion: " + heroe.getBatallas().get(i).getFechaFin() + "]\n";	
							}
							else {
								output += "\tSin Batallas...\n";
							}
						}
					}
				}
			else {
				output += "\tSin clientes...\n";
			}
			System.out.println(output);
		}
		
	}
	
	public void addHeroes(Heroes heroe) {
		this.heroes.add(heroe);
	}
	
	public String toJSON() {		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();		
		return gson.toJson(this);
	}
	
	public String getBBDDid() {
		return BBDDid;
	}

	public void setBBDDid(String BBDDid) {
		this.BBDDid = BBDDid;
	}
	
	public ArrayList<Heroes> getHeroes() {
		return heroes;
	}

}
